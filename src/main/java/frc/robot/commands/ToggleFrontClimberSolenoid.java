package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;

public class ToggleFrontClimberSolenoid extends InstantCommand {

    public ToggleFrontClimberSolenoid() {
        super("Toggle Front Climber Solenoid");
        requires(Robot.climber);
    }

    @Override
    public void initialize() {
        if (Robot.climber.getFrontSolenoidValue() == Value.kForward) {
            Robot.climber.setFrontSolenoid(Value.kReverse);
        } else {
            Robot.climber.setFrontSolenoid(Value.kForward);
        }
    }
}