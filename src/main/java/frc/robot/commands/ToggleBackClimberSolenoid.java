package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;

public class ToggleBackClimberSolenoid extends InstantCommand {

    public ToggleBackClimberSolenoid() {
        super("Toggle Back Climber Solenoid");
        requires(Robot.climber);
    }

    @Override
    public void initialize() {
        if (Robot.climber.getBackSolenoidValue() == Value.kForward) {
            Robot.climber.setBackSolenoid(Value.kReverse);
        } else {
            Robot.climber.setBackSolenoid(Value.kForward);
        }
    }
}