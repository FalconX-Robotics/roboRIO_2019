package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
//get rekt mate
public class DriveClimberMotor extends Command {

    public DriveClimberMotor() {
        super("Drive Climber Motor");
        requires(Robot.climber);
    }

    @Override
    protected void execute() {

        double forwardSpeed = Robot.oi.getDriverLeftTriggerAxis();
        double backwardSpeed = Robot.oi.getDriverRightTriggerAxis();
        

        Robot.climber.setClimberMotorSpeed(forwardSpeed - backwardSpeed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}