package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain.DirectionState;

public class TankDriveWithXbox extends Command {
    public TankDriveWithXbox() {
        super("Tank drive with Xbox Controller");
        requires(Robot.drivetrain);
    }

    @Override
    protected void execute() {
        double leftSpeed = Robot.oi.getDriverLeftYAxis();
        double rightSpeed = Robot.oi.getDriverRightYAxis();

        if (DirectionState.check(DirectionState.FORWARD)) {
            Robot.drivetrain.tankDrive(leftSpeed, rightSpeed);

        } else if (DirectionState.check(DirectionState.BACKWARD)) {
            Robot.drivetrain.tankDrive(rightSpeed, leftSpeed);

        } else if (DirectionState.check(DirectionState.INVALID)) {
            Robot.drivetrain.faceForwards();
            Robot.drivetrain.tankDrive(leftSpeed, rightSpeed);
        }



    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}