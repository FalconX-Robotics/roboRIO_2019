package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain.DirectionState;

public class TankDriveWithXbox extends Command {

    public TankDriveWithXbox() {
        super("Tank Drive with Xbox Controller");
        requires(Robot.drivetrain);
    }

    @Override
    protected void execute() {
        double leftSpeed = Robot.oi.getDriverLeftYAxis();
        double rightSpeed = Robot.oi.getDriverRightYAxis();

        Robot.oi.rumble(RumbleType.kLeftRumble, leftSpeed);
        Robot.oi.rumble(RumbleType.kRightRumble, rightSpeed);

        Robot.oi.rumble(RumbleType.kLeftRumble, leftSpeed*-1);
        Robot.oi.rumble(RumbleType.kRightRumble, rightSpeed*-1);

        if (DirectionState.check(DirectionState.FORWARD)) {
            Robot.drivetrain.tankDrive(leftSpeed, rightSpeed);

        } else if (DirectionState.check(DirectionState.BACKWARD)) {
            Robot.drivetrain.tankDrive(rightSpeed, leftSpeed);

        } else if (DirectionState.check(DirectionState.INVALID)) {
            Robot.drivetrain.faceForwards();
            Robot.drivetrain.tankDrive(leftSpeed, rightSpeed);
        }

        // long startTime = System.nanoTime();
        // double startDistance = Robot.drivetrain.getEncodersDistance();

        // SmartDashboard.putNumber("Encoder Value", Robot.drivetrain.getEncodersCount());

        // if (System.nanoTime() - startTime > RobotMap.UPDATE_TIME) {
        //     startTime = System.nanoTime();
        //     double distance = startDistance - Robot.drivetrain.getEncodersDistance();

        //     SmartDashboard.putNumber("Encoder Distance", distance);
        //     SmartDashboard.putNumber("Encoder Angle", Robot.drivetrain.getEncodersCount());
        //     SmartDashboard.putNumber("Robot Speed (cm/s)", Robot.drivetrain.getSpeed());
            // // if traveled distance passes distance to shift gear, shift gear
            // if (distance > RobotMap.DISTANCE_TO_SHIFT) {
            // // Shift gear to HIGH
            // Robot.drivetrain.shifterForward();
            // } else {
            // // Shift gear to LOW
            // Robot.drivetrain.shifterBackward();
            // }
            // startDistance = Robot.drivetrain.getEncodersDistance();

            // if (Robot.drivetrain.getSpeed() > (double) RobotMap.ROBOT_GEAR_SHIFT_SPEED) {
            //     Robot.drivetrain.shifterForward();
            // } else {
            //     Robot.drivetrain.shifterBackward();
            // }
        }

  //  }

    @Override
    protected boolean isFinished() {
        return false;
    }
}