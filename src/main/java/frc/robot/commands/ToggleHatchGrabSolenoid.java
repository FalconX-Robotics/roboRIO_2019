package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ToggleHatchGrabSolenoid extends InstantCommand {
    private Value toggleValue;

    public ToggleHatchGrabSolenoid(Value toggleValue) {
        super("ToggleHatchGrabSolenoid");
        this.toggleValue = toggleValue;
    }

    public void initialize() {
        Robot.hatchPanelGrabber.toggleHatchGrabSolenoid(toggleValue);
    }
}