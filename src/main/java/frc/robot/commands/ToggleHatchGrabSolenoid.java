package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;
import frc.robot.subsystems.HatchPanelGrabber.HatchPanelGrabberState;

public class ToggleHatchGrabSolenoid extends InstantCommand {
    private Value toggleValue;
    private boolean update;

    public ToggleHatchGrabSolenoid(Value toggleValue, boolean update) {
        super("Toggle Hatch Grab Solenoid");
        this.toggleValue = toggleValue;
    }

    @Override
    protected void initialize() {
        Robot.hatchPanelGrabber.toggleHatchGrabSolenoid(toggleValue);
    }

    @Override
    protected void end() {
        if (update) {
            HatchPanelGrabberState.update();
        }
    }
}