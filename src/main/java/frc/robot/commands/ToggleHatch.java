package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;
import frc.robot.subsystems.HatchPanelGrabber.hatchPanelState;

public class ToggleHatch extends Command {

    public ToggleHatch() {
        super("ToggleHatch");
        requires(Robot.hatchPanelGrabber);
    }

    @Override
    protected void initialize() {

        if (hatchPanelState.get() == hatchPanelState.OPENED) {
            // Push Solenoid in
            Robot.hatchPanelGrabber.toggleHatchPushSolenoid(false);
            // Grab Solenoid out
            Robot.hatchPanelGrabber.toggleHatchGrabSolenoid(Value.kForward);

        } else if (hatchPanelState.get() == hatchPanelState.CLOSED) {
            // Push Solenoid in
            Robot.hatchPanelGrabber.toggleHatchPushSolenoid(false);
            // Grab Solenoid in
            Robot.hatchPanelGrabber.toggleHatchGrabSolenoid(Value.kReverse);

        } else if (hatchPanelState.get() == hatchPanelState.LAUNCHING) {
            // Grab Solenoid in (So it can't break)
            Robot.hatchPanelGrabber.toggleHatchGrabSolenoid(Value.kReverse);

        } else if (hatchPanelState.get() == hatchPanelState.INVALID) {
            // Push Solenoid in (So it unbreaks)
            Robot.hatchPanelGrabber.toggleHatchPushSolenoid(false);
        }
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}