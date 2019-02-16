package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;

public class ToggleHatchGrabSolenoid extends InstantCommand {
    private Value toggleValue;

    public ToggleHatchGrabSolenoid(Value toggleValue) {
        super("Toggle Hatch Grab Solenoid");
        this.toggleValue = toggleValue;
    }

    public void initialize() {
        Robot.hatchPanelGrabber.toggleHatchGrabSolenoid(toggleValue);
    }
}