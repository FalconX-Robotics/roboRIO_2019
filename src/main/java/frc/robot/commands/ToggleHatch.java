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
            Robot.hatchPanelGrabber.toggleHatchPushSolenoid(false);
            Robot.hatchPanelGrabber.toggleHatchGrabSolenoid(Value.kForward);

        } else if (hatchPanelState.get() == hatchPanelState.CLOSED) {
            Robot.hatchPanelGrabber.toggleHatchPushSolenoid(false);
            Robot.hatchPanelGrabber.toggleHatchGrabSolenoid(Value.kReverse);

        } else if (hatchPanelState.get() == hatchPanelState.LAUNCHING) {
            Robot.hatchPanelGrabber.toggleHatchGrabSolenoid(Value.kReverse);

        } else if (hatchPanelState.get() == hatchPanelState.INVALID) {
            Robot.hatchPanelGrabber.toggleHatchPushSolenoid(false);
        }
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}