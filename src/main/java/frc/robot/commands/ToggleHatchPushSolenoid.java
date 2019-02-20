package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.HatchPanelGrabber.HatchPanelGrabberState;

public class ToggleHatchPushSolenoid extends InstantCommand {
    private boolean toggleValue, update;

    public ToggleHatchPushSolenoid(boolean toggleValue, boolean update) {
        super("Toggle Hatch Push Solenoid");
        this.toggleValue = toggleValue;
    }

    @Override
    protected void initialize() {
        Robot.hatchPanelGrabber.toggleHatchPushSolenoid(toggleValue);
    }

    @Override
    protected void end() {
        if (update) {
            HatchPanelGrabberState.update();
        }
    }
}