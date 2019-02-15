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

    public static void initialize() {
        obiWan = networkTableInstance.getTable("ObiWan");

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

    public static Trajectory getTrajectory(Waypoint[] waypoints, Trajectory.FitMethod fit, int samples, double dt, double max_velocity, double max_acceleration, double max_jerk){
        return getTrajectory(waypoints, fit, samples, dt, max_velocity, max_acceleration, max_jerk);
    }
}
