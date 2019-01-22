package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class ClimberToggleBackSolenoid extends InstantCommand {
    private Value toValue;

    public ClimberToggleBackSolenoid(Value toValue) {
        super("ClimberToggleBackSolenoid");
        this.toValue = toValue;
    }

    @Override
    public void initialize() {
        Robot.climber.setBackSolenoid(toValue);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}