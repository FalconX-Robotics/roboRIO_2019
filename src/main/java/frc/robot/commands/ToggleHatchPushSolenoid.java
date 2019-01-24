package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;

public class ToggleHatchPushSolenoid extends InstantCommand {
    private Value toggleValue;

    public ToggleHatchPushSolenoid(Value toggleValue) {
        super("ToggleHatchPushSolenoid");
        this.toggleValue = toggleValue;
    }

    public void initialize() {
        Robot.hatchPanelGrabber.toggleHatchPushSolenoid(toggleValue);
    }
}