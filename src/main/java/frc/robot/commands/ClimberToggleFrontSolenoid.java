package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

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

    @Override
    protected boolean isFinished() {
        return false;
    }
}