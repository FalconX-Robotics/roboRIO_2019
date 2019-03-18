package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class SetClimberMotorSpeed extends InstantCommand {
    private double speed;

    public SetClimberMotorSpeed(double speed) {
        super("Set Climber Motor");
        requires(Robot.climber);
        this.speed = speed;
    }

    @Override
    protected void initialize() {
        Robot.climber.setClimberMotorSpeed(speed);
    }

    @Override
    protected void end() {
        
    }

    @Override
    protected void interrupted() {
        end();
    }

}