package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Climber.ClimberState;

public class DriveClimberMotor extends Command {

    public DriveClimberMotor() {
        super("Drive Climber Motor");
        requires(Robot.climber);
    }

    @Override
    protected void execute() {
        if (ClimberState.get() == ClimberState.READY)
            return;

        double forwardSpeed = Robot.oi.getDriverLeftTriggerAxis();
        double backwardSpeed = Robot.oi.getDriverRightTriggerAxis();
        

        Robot.climber.setClimberMotorSpeed(forwardSpeed - backwardSpeed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}