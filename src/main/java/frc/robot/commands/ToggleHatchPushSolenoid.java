package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ToggleHatchPushSolenoid extends InstantCommand {
    private boolean toggleValue;

    public ToggleHatchPushSolenoid(boolean toggleValue) {
        super("ToggleHatchPushSolenoid");
        this.toggleValue = toggleValue;
    }

    public void initialize() {
        Robot.hatchPanelGrabber.toggleHatchPushSolenoid(toggleValue);
    }
}