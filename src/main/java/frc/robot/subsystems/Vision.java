package frc.robot.subsystems;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;
import frc.robot.Robot;
import frc.robot.RobotMap;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
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

    private static final double kp = 0, ki = 0, kd = 0, kv = 0, ka = 0; // '0' values are placeholders

    private static NetworkTableInstance networkTableInstance = NetworkTableInstance.getDefault();
    private static NetworkTable obiWan;
    private static NetworkTable smartDashboard;

    private static NetworkTableEntry horizontal;
    private static NetworkTableEntry depth;
    private static NetworkTableEntry hatchAngle;

    // private static double knownDistance = 1;
    private static NetworkTableValue rectangleOneAngles;
    private static NetworkTableValue rectangleTwoAngles;
    private static EncoderFollower leftFollower;
    private static EncoderFollower rightFollower;

    private static Trajectory trajectory, leftPath, rightPath;
    private static Waypoint[] waypoints;
    private static Trajectory.Config config;
    private static TankModifier modifier;

    public static void initialize() {
        obiWan = networkTableInstance.getTable("ObiWan");
        smartDashboard = networkTableInstance.getTable("SmartDashboard");
        horizontal = smartDashboard.getEntry("horizontal");
        depth = smartDashboard.getEntry("depth");
        hatchAngle = smartDashboard.getEntry("hatchAngle");

        config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05,
            MAX_VELOCITY, MAX_ACCEL, MAX_JERK);
        EncoderFollower leftFollower = new EncoderFollower(leftPath);
        EncoderFollower rightFollower = new EncoderFollower(rightPath);
        leftFollower.configureEncoder(Robot.drivetrain.getLeftEncoderCount(), RobotMap.COUNTS_PER_REVOLUTION,
            RobotMap.WHEEL_DIAMETER);
        rightFollower.configureEncoder(Robot.drivetrain.getRightEncoderCount(), RobotMap.COUNTS_PER_REVOLUTION,
            RobotMap.WHEEL_DIAMETER);
        leftFollower.configurePIDVA(kp, ki, kd, kv, ka);

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
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate | EntryListenerFlags.kLocal);
    }

    public static void autoAlign(){
        waypoints = new Waypoint[] {
            new Waypoint(horizontal.getDouble(0), depth.getDouble(0), hatchAngle.getDouble(0))
        };
        trajectory = Pathfinder.generate(waypoints, config);
        modifier = new TankModifier(trajectory).modify(WHEEL_BASE);
        leftPath = modifier.getLeftTrajectory();
        rightPath = modifier.getRightTrajectory();

        double leftSpeed = leftFollower.calculate(Robot.drivetrain.getLeftEncoderCount());
        double rightSpeed = rightFollower.calculate(Robot.drivetrain.getRightEncoderCount());
        double headingDifference = Pathfinder.boundHalfDegrees(Pathfinder.r2d(leftFollower.getHeading())
         - Robot.drivetrain.getGyroAngle());
        double turn = 0.8 * (-1.0 / 80.0) * headingDifference; //tbh i dunno what this lines does

        Robot.drivetrain.setLeftSide(leftSpeed + turn);
        Robot.drivetrain.setRightSide(rightSpeed - turn);
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
