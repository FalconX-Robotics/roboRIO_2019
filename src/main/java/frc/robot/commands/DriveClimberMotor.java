package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveClimberMotor extends Command {

    public DriveClimberMotor() {
        super ("DriveClimberMotor");
        requires(Robot.climber);
    }

    @Override
    protected void execute() {
        double forwardSpeed = Robot.oi.getDriverLeftTriggerAxis();
        double backwardSpeed = Robot.oi.getDriverRightTriggerAxis();

        Robot.climber.setClimberMotorSpeed(forwardSpeed + backwardSpeed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
    
}