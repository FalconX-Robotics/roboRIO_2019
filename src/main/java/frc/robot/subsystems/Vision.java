package frc.robot.subsystems;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.followers.EncoderFollower;

import java.util.Arrays;

public class Vision {

    private static final double TAPE_WIDTH = 8;
    private static final double CAMERA_HEIGHT = 95;
    private static double theta, B, d, alpha, x, y;
    private static double[] vectorUno, vectorDos;

    private static NetworkTableInstance networkTableInstance = NetworkTableInstance.getDefault();
    private static NetworkTable obiWan;
    private static NetworkTable smartDashboard;

    // private static double knownDistance = 1;
    private static double knownHeight = 5.75;
    private static NetworkTableValue rectangleOneAngles;
    private static NetworkTableValue rectangleTwoAngles;
    private static double focalLength = 200; // change later //218; 202; 191
    private static EncoderFollower leftFollower;
    private static EncoderFollower rightFollower;
    private static Notifier followerNotifier;

    public static void initialize() {
        obiWan = networkTableInstance.getTable("ObiWan");
        smartDashboard = networkTableInstance.getTable("SmartDashboard");
        NetworkTableEntry horizontal = smartDashboard.getEntry("horizontal");
        NetworkTableEntry depth = smartDashboard.getEntry("depth");
        /*
         * ObiWan values: rectangle1: arrays with 8 intergers rectangle2: arrys with 8
         * intergers
         */
        obiWan.addEntryListener("rectangle1", (table, key, entry, value, flags) -> {
            rectangleOneAngles = value;
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate | EntryListenerFlags.kLocal);

        obiWan.addEntryListener("rectangle2", (table, key, entry, rectangleTwoValue, flags) -> {
            rectangleTwoAngles = rectangleTwoValue;
            // Values are:
            // Left to right; and if x value is the same, it's up to down
            // double[] doubleArrayOne = rectangleOneValue.getDoubleArray(),
            //         doubleArrayTwo = rectangleTwoValue.getDoubleArray();

            // if (doubleArrayOne.length + doubleArrayTwo.length < 16) {
            //     SmartDashboard.putBoolean("Found rectangles", false);
            //     return;
            // }
            // SmartDashboard.putBoolean("Found rectangles", true);

            // double[][] rectLeft = organize(doubleArrayOne), rectRight = organize(doubleArrayTwo);

            // // find rect side
            // // average x value of rect 1 is more rect 2 then rect 1 is right
            // if (average(getDimensionArray(rectLeft, 0)) > average(getDimensionArray(rectRight, 0))) {
            //     double[][] copy = rectLeft.clone();
            //     rectLeft = rectRight;
            //     rectRight = copy;
            // }

            // SmartDashboard.putString("Left rect", Arrays.deepToString(rectLeft));
            // SmartDashboard.putString("Right rect", Arrays.deepToString(rectRight));

            // double perLeftHeight = getPerWidth(rectLeft);
            // double rectangleLeft = findDistanceToCamera(knownHeight, focalLength, perLeftHeight);

            // double perRightHeight = getPerWidth(rectRight);
            // double rectangleRight = findDistanceToCamera(knownHeight, focalLength, perRightHeight);
            SmartDashboard.putString("Length of rectangles", "One: " + rectangleOneAngles.getDoubleArray().length +  " Two: " + rectangleTwoValue.getDoubleArray().length);
            if(rectangleOneAngles.getDoubleArray().length == 2) {
                vectorUno = vectorize(rectangleOneAngles.getDoubleArray()[0], rectangleOneAngles.getDoubleArray()[1]);
                vectorDos = vectorize(rectangleTwoAngles.getDoubleArray()[0], rectangleTwoAngles.getDoubleArray()[1]);
                horizontal.setDouble((vectorUno[0]+vectorDos[0])/2);
                depth.setDouble((vectorUno[2]+vectorDos[2])/2);
            }
            // x = calculatedValues[0];
            // y = calculatedValues[1];

            // SmartDashboard.putNumber("Calculated X", x);
            // SmartDashboard.putNumber("Calculated y", y);

        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate | EntryListenerFlags.kLocal);
    }

    public static void followPath() {
        if (leftFollower.isFinished() || rightFollower.isFinished()) {
            followerNotifier.stop();
        } else {
            double leftSpeed = leftFollower.calculate((int) Robot.drivetrain.getLeftEncoderCount());
            double rightSpeed = rightFollower.calculate((int) Robot.drivetrain.getRightEncoderCount());
            double heading = Robot.drivetrain.getGyroAngle();
            double desiredHeading = Pathfinder.r2d(leftFollower.getHeading());
            double headingDifference = Pathfinder.boundHalfDegrees(desiredHeading - heading);
            double turn = 0.8 * (-1.0 / 80.0) * headingDifference;
            Robot.drivetrain.setLeftSide(leftSpeed + turn);
            Robot.drivetrain.setRightSide(rightSpeed - turn);
        }
    }
    public static double[] vectorize(double xAngle, double yAngle) {
        double[] position = new double[3];
        position[0] = Math.tan(Math.toRadians(xAngle))/Math.tan(Math.toRadians(yAngle));
        position[1] = CAMERA_HEIGHT - 72.5;
        position[2] = 1/Math.tan(Math.toRadians(yAngle));
        return position;
    }
    public static double[] calculate(double a, double b) {

        theta = Math.acos(-1 * (Math.pow(TAPE_WIDTH, 2) - Math.pow(a, 2) - Math.pow(b, 2)) / (2 * a * b));
        B = Math.asin(b * Math.sin(theta) / TAPE_WIDTH);

        // check for and fix multiple Law of Sine solutions
        if (B < 90 && a < b) {
            B = Math.PI - B;
        }
        if (B > 90 && a > b) {
            B = Math.PI - B;
        }
        d = Math.sqrt(Math.pow(a, 2) + Math.pow(TAPE_WIDTH / 2, 2) - TAPE_WIDTH * a * Math.cos(B));
        alpha = Math.PI - B - Math.asin(TAPE_WIDTH / 2 * Math.sin(B) / d);

        x = -1 * d * Math.cos(alpha);
        y = d * Math.sin(alpha);

        // test output
        SmartDashboard.putNumber("Distance to hatch", d);
        SmartDashboard.putNumber("Angle to hatch", alpha);
        SmartDashboard.putNumber("Horizontal distance", x);
        SmartDashboard.putNumber("Vertical distance", y);

        return new double[] { x, y };
    }

    public static double findHeight(double width, double height) {
        return (width * Math.cos(76)) + (height * Math.cos(14));
    }

    public static double findDistanceToCamera(double objectHeight, double focalLength, double imageHeight) {
        double realHeight = 73.5; // in cm because imperial is ew
        double camHeight = 95;
        return (double) (imageHeight * realHeight * focalLength) / (objectHeight * camHeight);
    }

    public static double[][] organizeToTuples(double[] array) {
        double[][] tuple = new double[4][2];
        for (int i = 0; i < array.length; i++) {
            if (i % 2 == 0) {
                tuple[i / 2][0] = (double) array[i];
            } else {
                tuple[(i - 1) / 2][1] = (double) array[i];
            }
        }

        System.out.println(Arrays.deepToString(tuple));

        return tuple;
    }

    public static double[][] organizeDimension(double[][] array) { // 0 is y; 1 is x
        Arrays.sort(array, new java.util.Comparator<double[]>() { // MODIFIED:
                                                                  // https://stackoverflow.com/questions/15452429/java-arrays-sort-2d-array
            public int compare(double[] a, double[] b) {
                if (b[0] != a[0])
                    return Double.compare(a[0], b[0]);
                else
                    return Double.compare(a[1], b[1]);
            }
        });
        return array;
    }

    public static double[][] organize(double[] array) {
        return organizeDimension(organizeToTuples(array));
    }

    public static double getPerWidth(double[][] array) {
        // use 2th, and 3th value
        double height = Math.abs(array[1][1] - array[2][1]);
        SmartDashboard.putNumber("Pixel height", height);
        return height;
    }

    public static double average(double[] nums) {
        double average = 0;

        for (double n : nums) {
            average = n + average;
        }

        return (double) average / nums.length;
    }

    public static double[] getDimensionArray(double[][] array, int dim) {
        double[] copy = new double[4];

        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i][dim];
        }

        return copy;
    }
}