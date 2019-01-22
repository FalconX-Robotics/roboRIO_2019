package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TankDriveWithXbox extends Command {
    public TankDriveWithXbox() {
        super("Tank drive with Xbox Controller");
        requires(Robot.drivetrain);
    }

    @Override
    protected void execute() {
        double leftSpeed = Robot.oi.getDriverLeftYAxis();
        double rightSpeed = Robot.oi.getDriverRightYAxis();

        Robot.drivetrain.tankDrive(leftSpeed, rightSpeed);

    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}