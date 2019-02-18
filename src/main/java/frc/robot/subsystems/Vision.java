package frc.robot.subsystems;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Notifier;
import frc.robot.Robot;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.Trajectory.Segment;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class Vision {

    private static final double TAPE_WIDTH = 28.5;
    private static final double CAMERA_HEIGHT = 96.8;
    private static double pathfinderAngle;
    private static double[] vectorUno, vectorDos;

    private static final double MAX_VELOCITY = 0; // '0' values are placeholders
    private static final double MAX_ACCEL = 0;
    private static final double MAX_JERK = 0;
    private static final double WHEEL_BASE = 23;

    private static NetworkTableInstance networkTableInstance = NetworkTableInstance.getDefault();
    private static NetworkTable obiWan;
    private static NetworkTable smartDashboard;

    // private static double knownDistance = 1;
    private static NetworkTableValue rectangleOneAngles;
    private static NetworkTableValue rectangleTwoAngles;
    private static EncoderFollower leftFollower;
    private static EncoderFollower rightFollower;
    private static Notifier followerNotifier;

    private static Trajectory trajectory, leftPath, rightPath;
    private static Waypoint[] waypoints;
    private static Trajectory.Config config;
    private static TankModifier modifier;

    public static void initialize() {
        obiWan = networkTableInstance.getTable("ObiWan");
        smartDashboard = networkTableInstance.getTable("SmartDashboard");
        NetworkTableEntry horizontal = smartDashboard.getEntry("horizontal");
        NetworkTableEntry depth = smartDashboard.getEntry("depth");
        NetworkTableEntry hatchAngle = smartDashboard.getEntry("hatchAngle");

        config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05,
            MAX_VELOCITY, MAX_ACCEL, MAX_JERK);
        modifier = new TankModifier(trajectory).modify(WHEEL_BASE);
        //EncoderFollower leftSide

        /*
         * ObiWan values: rectangle1: arrays with 8 intergers rectangle2: arrys with 8
         * intergers
         */
        obiWan.addEntryListener("rectangle1", (table, key, entry, value, flags) -> {
        rectangleOneAngles = value;
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate |
        EntryListenerFlags.kLocal);

        obiWan.addEntryListener("rectangle2", (table, key, entry, rectangleTwoValue, flags) -> {
            rectangleTwoAngles = rectangleTwoValue;

            if (rectangleOneAngles.getDoubleArray().length == 2) {
                vectorUno = vectorize(rectangleOneAngles.getDoubleArray()[0], rectangleOneAngles.getDoubleArray()[1]);
                vectorDos = vectorize(rectangleTwoAngles.getDoubleArray()[0], rectangleTwoAngles.getDoubleArray()[1]);
                horizontal.setDouble((vectorUno[0] + vectorDos[0]) / 2);
                depth.setDouble((vectorUno[2] + vectorDos[2]) / 2);
                pathfinderAngle = findHatchAngle(rectangleOneAngles.getDoubleArray()[0], rectangleTwoAngles.getDoubleArray()[0]);
                hatchAngle.setDouble(pathfinderAngle);
            }
            
            waypoints = new Waypoint[] {
                new Waypoint(horizontal.getDouble(0), depth.getDouble(0), hatchAngle.getDouble(0))
            };
            trajectory = Pathfinder.generate(waypoints, config);
            leftPath = modifier.getLeftTrajectory();
            rightPath = modifier.getRightTrajectory();


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

    public static void loopTrajectory(Trajectory trajectory){
        Segment segment;
        for(int i = 0; i < trajectory.length(); i++){
            segment = trajectory.get(i);
        }
    }

    public static double[] vectorize(double xAngle, double yAngle) {
        double[] position = new double[3];
        position[0] = (CAMERA_HEIGHT - 72.5) * Math.tan(Math.toRadians(xAngle)) / Math.tan(Math.toRadians(yAngle));
        position[1] = CAMERA_HEIGHT - 72.5;
        position[2] = (CAMERA_HEIGHT - 72.5) / Math.tan(Math.toRadians(yAngle));
        return position;
    }
    public static double findHatchAngle(double xAngle, double yAngle) {
        double[] vectorPos = vectorize(xAngle, yAngle);
        double hypotenuse = Math.sqrt(Math.pow(vectorPos[0], 2)+Math.pow(vectorPos[2], 2));
        double angleToHatch = Math.asin(hypotenuse*Math.sin(Math.toRadians(xAngle))/TAPE_WIDTH);
        return Math.toDegrees(angleToHatch);
    }
}
