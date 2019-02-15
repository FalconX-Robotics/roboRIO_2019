package frc.robot.subsystems;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.Trajectory.FitMethod;
import jaci.pathfinder.followers.EncoderFollower;


import java.util.Arrays;

public class Vision {

    private static final double TAPE_WIDTH = 8;
    private static double theta, B, d, alpha, x, y;

    private static NetworkTableInstance networkTableInstance = NetworkTableInstance.getDefault();
    private static NetworkTable obiWan;

    // private static double knownDistance = 1;
    private static double knownHeight = 5.75;
    private static NetworkTableValue rectangleOneValue;
    private static double focalLength = 200; // change later //218; 202; 191
    private static EncoderFollower leftFollower;
    private static EncoderFollower rightFollower;
    private static Notifier followerNotifier;

    private static Trajectory.Config config;
    private static Waypoint[] waypoint;
    private static Trajectory path;

    private static final double VELOCITY = 0, ACCELERATION = 0, JERK = 0; //get these values
    private static final double xpos = 0, ypos = 0, angle = 0; //get these values

    public static void initialize() {
        obiWan = networkTableInstance.getTable("ObiWan");
        smartDashboard = networkTableInstance.getTable("SmartDashboard");
        NetworkTableEntry horizontal = smartDashboard.getEntry("horizontal");
        NetworkTableEntry depth = smartDashboard.getEntry("depth");
        NetworkTableEntry hatchAngle = smartDashboard.getEntry("hatchAngle");

        config = new Trajectory.Config(FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, VELOCITY, ACCELERATION, JERK);
        
        /*
         * ObiWan values: rectangle1: arrays with 8 intergers rectangle2: arrys with 8
         * intergers
         */
        obiWan.addEntryListener("rectangle1", (table, key, entry, value, flags) -> {
            rectangleOneValue = value;
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate | EntryListenerFlags.kLocal);

        obiWan.addEntryListener("rectangle2", (table, key, entry, rectangleTwoValue, flags) -> {
            // Values are:
            // Left to right; and if x value is the same, it's up to down
            double[] doubleArrayOne = rectangleOneValue.getDoubleArray(),
                    doubleArrayTwo = rectangleTwoValue.getDoubleArray();

            if (doubleArrayOne.length + doubleArrayTwo.length < 16) {
                SmartDashboard.putBoolean("Found rectangles", false);
                return;
            rectangleTwoAngles = rectangleTwoValue;
            
            if (rectangleOneAngles.getDoubleArray().length == 2) {
                vectorUno = vectorize(rectangleOneAngles.getDoubleArray()[0], rectangleOneAngles.getDoubleArray()[1]);
                vectorDos = vectorize(rectangleTwoAngles.getDoubleArray()[0], rectangleTwoAngles.getDoubleArray()[1]);
                horizontal.setDouble((vectorUno[0] + vectorDos[0]) / 2);
                depth.setDouble((vectorUno[2] + vectorDos[2]) / 2);
                pathfinderAngle = findHatchAngle(rectangleOneAngles.getDoubleArray()[0], rectangleTwoAngles.getDoubleArray()[0]);
                hatchAngle.setDouble(pathfinderAngle);

                waypoint = new Waypoint[] {
                    new Waypoint(xpos, ypos, angle)
                };
                //path = new Pathfinder.generate(waypoint, path);
            }
            SmartDashboard.putBoolean("Found rectangles", true);

            SmartDashboard.putNumber("Calculated X", x);
            SmartDashboard.putNumber("Calculated y", y);

            //getTrajectory(waypoints, Trajectory.FitMethod.HERMITE_CUBIC, );

        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate | EntryListenerFlags.kLocal);
    }

    public static double findDistanceToCamera(double objectHeight, double focalLength, double imageHeight) {
        double realHeight = 73.5; // in cm because imperial is ew
        double camHeight = 95;
        return (double) (imageHeight * realHeight * focalLength) / (objectHeight * camHeight);
    }
}
