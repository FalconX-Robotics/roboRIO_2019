#!/usr/bin/env python3
#----------------------------------------------------------------------------
# Copyright (c) 2018 FIRST. All Rights Reserved.
# Open Source Software - may be modified and shared by FRC teams. The code
# must be accompanied by the FIRST BSD license file in the root directory of
# the project.
#----------------------------------------------------------------------------

import json
import time
import sys
import logging
import numpy as np
import math
import cv2

from grip import GripPipeline

from cscore import CameraServer, VideoSource, UsbCamera, MjpegServer, CvSink
from networktables import NetworkTablesInstance
from networktables import NetworkTables as nt

#   JSON format:
#   {
#       "team": <team number>,
#       "ntmode": <"client" or "server", "client" if unspecified>
#       "cameras": [
#           {
#               "name": <camera name>
#               "path": <path, e.g. "/dev/video0">
#               "pixel format": <"MJPEG", "YUYV", etc>   // optional
#               "width": <video mode width>              // optional
#               "height": <video mode height>            // optional
#               "fps": <video mode fps>                  // optional
#               "brightness": <percentage brightness>    // optional
#               "white balance": <"auto", "hold", value> // optional
#               "exposure": <"auto", "hold", value>      // optional
#               "properties": [                          // optional
#                   {
#                       "name": <property name>
#                       "value": <property value>
#                   }
#               ],
#               "stream": {                              // optional
#                   "properties": [
#                       {
#                           "name": <stream property name>
#                           "value": <stream property value>
#                       }
#                   ]
#               }
#           }
#       ]
#   }

configFile = "/boot/frc.json"

class CameraConfig: pass

team = None
server = False
cameraConfigs = []

"""Report parse error."""
def parseError(str):
    print("config error in '" + configFile + "': " + str, file=sys.stderr)

"""Read single camera configuration."""
def readCameraConfig(config):
    cam = CameraConfig()

    # name
    try:
        cam.name = config["name"]
    except KeyError:
        parseError("could not read camera name")
        return False

    # path
    try:
        cam.path = config["path"]
    except KeyError:
        parseError("camera '{}': could not read path".format(cam.name))
        return False

    # stream properties
    cam.streamConfig = config.get("stream")

    cam.config = config

    cameraConfigs.append(cam)
    return True

"""Read configuration file."""
def readConfig():
    global team
    global server

    # parse file
    try:
        with open(configFile, "rt") as f:
            j = json.load(f)
    except OSError as err:
        print("could not open '{}': {}".format(configFile, err), file=sys.stderr)
        return False

    # top level must be an object
    if not isinstance(j, dict):
        parseError("must be JSON object")
        return False

    # team number
    try:
        team = j["team"]
    except KeyError:
        parseError("could not read team number")
        return False

    # ntmode (optional)
    if "ntmode" in j:
        str = j["ntmode"]
        if str.lower() == "client":
            server = False
        elif str.lower() == "server":
            server = True
        else:
            parseError("could not understand ntmode value '{}'".format(str))

    # cameras
    try:
        cameras = j["cameras"]
    except KeyError:
        parseError("could not read cameras")
        return False
    for camera in cameras:
        if not readCameraConfig(camera):
            return False

    return True

"""listen to networktable"""
def valueChanged(table, key, value, isNew):
    print("valueChanged: key: '%s'; value: %s; isNew: %s" % (key, value, isNew))


def connectionListener(connected, info):
    print(info, "; Connected=%s" % connected)

nt.addConnectionListener(connectionListener, immediateNotify=True)

"""Start running the camera."""
def startCamera(config):
    print("Starting camera '{}' on {}".format(config.name, config.path))
    inst = CameraServer.getInstance()
    camera = UsbCamera(config.name, config.path)
    server = inst.startAutomaticCapture(camera=camera, return_server=True)

    camera.setConfigJson(json.dumps(config.config))
    camera.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen)

    if config.streamConfig is not None:
        server.setConfigJson(json.dumps(config.streamConfig))

    return camera
        
if __name__ == "__main__":
    if len(sys.argv) >= 2:
        configFile = sys.argv[1]

    # read configuration
    if not readConfig():
        sys.exit(1)

    # start NetworkTables
    ntinst = NetworkTablesInstance.getDefault()
    if server:
        print("Setting up NetworkTables server")
        ntinst.startServer()
    else:
        print("Setting up NetworkTables client for team {}".format(team))
        ntinst.startClientTeam(team)
        
    #### Initiate ####
    pipeline = GripPipeline()
    # start up cameras
    cameras = []
    for cameraConfig in cameraConfigs:
        cameras.append(startCamera(cameraConfig))
    # start up the cv2 stream
    img = np.zeros(shape=(360, 240, 3), dtype=np.uint8)
    cvSink = CameraServer.getInstance().getVideo()
    cvStream = MjpegServer("Grip Stream", "", 8080)
    gripVideo = CameraServer.getInstance().putVideo("GRIP stream", 360, 240)
    cvStream.setSource(gripVideo)
    # set up networktables
    sd = nt.getTable("SmartDashboard")
    ow = nt.getTable("ObiWan")
    sd.addEntryListener(valueChanged)
    sd.putBoolean("testLines", False)
    sd.putBoolean("testContours", False)
    sd.putNumber("lowPoint", 0)
    sd.putNumber("ctrArea", 0)

    laps = 0
    lowPoint = 0

    ### find some sexy rectangles (and contours) where format is either "color" or "binary"
    def findRectanglePair(format):
        img = None
        if format == "color":
            # make it c O l O r F u L
            img = cv2.cvtColor(pipeline.hsv_threshold_output, cv2.COLOR_GRAY2RGB)
        if format == "binary":
            # made it d E p R e S s I n G
            img = pipeline.hsv_threshold_output
        # draw contours
        contourImg = cv2.drawContours(img, pipeline.find_contours_output, -1, (100, 255, 100), 1)
        ow.putNumberArray("rectangle1", [])
        ow.putNumberArray("rectangle2", [])
        rectImg = contourImg
        # split everything into different lists of components
        rectList = []
        filteredRectList = []
        rectSizeList = []
        rectAngles = []
        xAngles = []
        yAngles = []
        shunned = []
        # add rectangles to image (one per matching contour)
        # and filter out all but the most relevent hatch pair
        for ctr in pipeline.filter_contours_output:
            rect = cv2.minAreaRect(ctr)
            box = np.int0(cv2.boxPoints(rect))
            cv2.drawContours(rectImg,[box],0,(0,0,255),2)
            '''find the difference between our observing angle and the object center'''
            #find the ratio of angles to pixels (through the camera angle)
            objxCenter = (box[0][0] + box[1][0] + box[2][0] + box[3][0])/4
            objyCenter = (box[0][1] + box[1][1] + box[2][1] + box[3][1])/4
            #quick maths
            objXAngle = (objxCenter-160)/320*60
            xAngles.append(objXAngle)
            objYAngle = (objyCenter-120)/240*40
            yAngles.append(objYAngle)
            '''Filter out all but the two largest matching points'''
            # create a list of similar rectangles
            filteredRectList.append(box)
            rectAngles.append(rect[2])
            # add the values to the total
            rectList.append(box)
        # (try to) make sure we aren't scanning two different hatches
        for i in filteredRectList:
            objSize = math.sqrt((i[0][0]-i[2][0])**2+(i[0][1]-i[2][1])**2)
            rectSizeList.append(objSize)
        if len(rectSizeList) > 2:
            minBox = min(rectSizeList)
            maxBox = max(rectSizeList)
            print(rectAngles)
            for i in range(len(rectSizeList)):
                if not 82 <= abs(rectAngles[i]) + abs(rectAngles[rectSizeList.index(maxBox)]) <= 98 and not rectAngles[i] == rectAngles[rectSizeList.index(maxBox)]:
                    shunned.append(len(rectSizeList)-i-1)
                elif minBox < rectSizeList[i] < maxBox:
                    minBox = rectSizeList[i]
                    shunned.append(len(rectSizeList)-i-1)
            for i in shunned:
                    filteredRectList.pop(i)
                    rect
        if len(filteredRectList) == 2:
            cv2.drawContours(rectImg,[filteredRectList[0]],0,(200,0,200),2)
            cv2.drawContours(rectImg,[filteredRectList[1]],0,(200,0,200),2)

        # # builds a sendable list of points [x1, y1, x2, y2, x3, y3, x4, y4]
        # def rawRect(box):
        #     points = []
        #     for point in box:
        #         points.append(point[0])
        #         points.append(point[1])
        #     return points
        # publish up to 2 network table entries
        if len(filteredRectList) == 2:
            ow.putNumberArray("rectangle1", [xAngles[0],yAngles[0]])
            ow.putNumberArray("rectangle2", [xAngles[1],yAngles[1]])
        return rectImg
    
    ## Things to do with output grip information ###
    def communicate(pipeline):
        # Check" for lines
        if pipeline.filter_contours_output != []:
            sd.putBoolean("testContours", True)
        else:
            sd.putBoolean("testContours", False)
    
    #### loop forever ####
    while True:
        # For console output
        cDetection = sd.getBoolean("testContours", False)
        lDetection = sd.getBoolean("testLines", False)
        
        # Process images
        frameTime, frame = cvSink.grabFrame(img)
        pipeline.process(frame)
        gripVideo.putFrame(findRectanglePair("color"))
        communicate(pipeline)
        #Output
        if cDetection == True or lDetection == True:
            print("I see something!")

        else:
            print("No object detected.")
        laps += 1
        sd.putNumber("Laps", laps)
        time.sleep(0.1)
