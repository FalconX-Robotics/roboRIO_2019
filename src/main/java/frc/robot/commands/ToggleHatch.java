package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.HatchPanelGrabber.HatchPanelGrabberState;

public class ToggleHatch extends Command {

    public ToggleHatch() {
        super("Toggle Hatch");
        requires(Robot.hatchPanelGrabber);
    }

    @Override
    protected void initialize() {

        if (HatchPanelGrabberState.get() == HatchPanelGrabberState.OPENED) {
            // Push Solenoid in
            Robot.hatchPanelGrabber.toggleHatchPushSolenoid(false);
            // Grab Solenoid out
            Robot.hatchPanelGrabber.toggleHatchGrabSolenoid(Value.kForward);

        } else if (HatchPanelGrabberState.get() == HatchPanelGrabberState.CLOSED) {
            // Push Solenoid in
            Robot.hatchPanelGrabber.toggleHatchPushSolenoid(false);
            // Grab Solenoid in
            Robot.hatchPanelGrabber.toggleHatchGrabSolenoid(Value.kReverse);

        } else if (HatchPanelGrabberState.get() == HatchPanelGrabberState.LAUNCHING) {
            // Grab Solenoid in (So it can't break)
            Robot.hatchPanelGrabber.toggleHatchGrabSolenoid(Value.kReverse);

        } else if (HatchPanelGrabberState.get() == HatchPanelGrabberState.INVALID) {
            // Push Solenoid in (So it unbreaks)
            Robot.hatchPanelGrabber.toggleHatchPushSolenoid(false);
        }
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}