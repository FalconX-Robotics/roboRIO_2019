package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.HatchPanelGrabber.hatchPanelState;

public class LaunchPanel extends CommandGroup {
    private static DoubleSolenoid.Value out = DoubleSolenoid.Value.kForward;
    private static DoubleSolenoid.Value in = DoubleSolenoid.Value.kReverse;

    public LaunchPanel() {
        super("LaunchPanelYourMom");
        if (hatchPanelState.get() == hatchPanelState.CLOSED) {
        // Grab solenoid in
        addSequential(new ToggleHatchGrabSolenoid(out));
        // Wait 0.3
        addSequential(new WaitCommand(), 0.3);
        // Push solenoid out
        addSequential(new ToggleHatchPushSolenoid(out));
        // Wait 0.1
        addSequential(new WaitCommand(), 0.1);
        // Push solenoid In
        addSequential(new ToggleHatchPushSolenoid(in));

        } else if (hatchPanelState.get() == hatchPanelState.OPENED) {
        // Push solenoid out
        addSequential(new ToggleHatchPushSolenoid(out));
        // Wait 0.1
        addSequential(new WaitCommand(), 0.1);
        // Push solenoid In
        addSequential(new ToggleHatchPushSolenoid(in));

        } else if (hatchPanelState.get() == hatchPanelState.LAUNCHING) {
            //empty for now

        } else if (hatchPanelState.get() == hatchPanelState.INVALID) {
        addSequential(new ToggleHatchPushSolenoid(in));
        addSequential(new ToggleHatchGrabSolenoid(in));
        // Wait 0.3
        addSequential(new WaitCommand(), 0.3);
        // Push solenoid out
        addSequential(new ToggleHatchPushSolenoid(out));
        // Wait 0.1
        addSequential(new WaitCommand(), 0.1);
        // Push solenoid In
        addSequential(new ToggleHatchPushSolenoid(in));
        }
        
    }

    @Override
    protected void initialize() {
        // try {
        // Robot.hatchPanelGrabber.toggleHatchGrabSolenoid(out);
        // TimeUnit.MILLISECONDS.sleep(300);
        // Robot.hatchPanelGrabber.toggleHatchPushSolenoid(out);
        // TimeUnit.MILLISECONDS.sleep(100);
        // Robot.hatchPanelGrabber.toggleHatchPushSolenoid(in);
        // } catch (InterruptedException e) {

        // }
        // finished = true;
    }

    // @Override
    // protected boolean isFinished() {
    // return finished;
    // }

}
