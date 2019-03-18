package frc.robot.commands.HatchPanel;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.HatchPanelGrabber.HatchPanelGrabberState;

public class ToggleHatch extends Command {
    // private static boolean state = false;

    public ToggleHatch() {
        super("Toggle Hatch");
        requires(Robot.hatchPanelGrabber);
    }

    @Override
    protected void initialize() {
        HatchPanelGrabberState.update();
        if (HatchPanelGrabberState.check(HatchPanelGrabberState.OPENED)) {
            // Grab Solenoid out
            Robot.hatchPanelGrabber.toggleHatchGrabSolenoid(Value.kForward);
        } else if (HatchPanelGrabberState.check(HatchPanelGrabberState.CLOSED)) {
            // Grab Solenoid in
            Robot.hatchPanelGrabber.toggleHatchGrabSolenoid(Value.kReverse);
        } else if (HatchPanelGrabberState.check(HatchPanelGrabberState.INVALID)) {
            // Push Solenoid in (So it unbreaks)
            Robot.hatchPanelGrabber.toggleHatchPushSolenoid(false);
        }
    }

    @Override
    protected void end() {
        HatchPanelGrabberState.update();
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}