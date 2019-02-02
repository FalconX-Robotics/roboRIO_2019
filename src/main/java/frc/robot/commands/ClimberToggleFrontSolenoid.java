package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;

public class ClimberToggleFrontSolenoid extends InstantCommand {
    private Value toValue;

    public ClimberToggleFrontSolenoid(Value toValue) {
        super("ClimberToggleFrontSolenoid");
        this.toValue = toValue;
    }

    @Override
    public void initialize() {
        Robot.climber.setFrontSolenoid(toValue);

    }
}