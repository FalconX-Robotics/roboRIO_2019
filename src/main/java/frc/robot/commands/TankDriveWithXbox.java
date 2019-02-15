package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
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

        // Robot.oi.rumble(RumbleType.kLeftRumble, Math.abs(leftSpeed));
        // Robot.oi.rumble(RumbleType.kRightRumble, Math.abs(rightSpeed));

        if (DirectionState.check(DirectionState.FORWARD)) {
            Robot.drivetrain.tankDrive(rightSpeed, leftSpeed);

        } else if (DirectionState.check(DirectionState.BACKWARD)) {
            Robot.drivetrain.tankDrive(leftSpeed, rightSpeed);

        } else if (DirectionState.check(DirectionState.INVALID)) {
            Robot.drivetrain.faceForwards();
            Robot.drivetrain.tankDrive(leftSpeed, rightSpeed);
        }

        long startTime = System.nanoTime();
        double startDistance = Robot.drivetrain.getEncodersDistance();

        SmartDashboard.putNumber("Encoder Value", Robot.drivetrain.getEncodersCount());

        if (System.nanoTime() - startTime > RobotMap.UPDATE_TIME) {
            startTime = System.nanoTime();
            SmartDashboard.putNumber("Encoder Angle", Robot.drivetrain.getEncodersCount());
            SmartDashboard.putNumber("Robot Speed (cm/s)", Robot.drivetrain.getSpeed());

            if (Robot.drivetrain.getSpeed() > (double) RobotMap.ROBOT_GEAR_SHIFT_SPEED) {
                Robot.drivetrain.shifterForward();
            } else {
                Robot.drivetrain.shifterBackward();
            }
        }

    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}