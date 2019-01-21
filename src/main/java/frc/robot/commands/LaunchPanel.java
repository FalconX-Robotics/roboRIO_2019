package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LaunchPanel extends CommandGroup {
    private static DoubleSolenoid.Value out = DoubleSolenoid.Value.kForward;
    private static DoubleSolenoid.Value in = DoubleSolenoid.Value.kReverse;

    public LaunchPanel() {
        super("LaunchPanelYourMom");
        // Grab solenoid in
        addSequential(new ToggleHatchGrabSolenoid(out));
        // Wait 0.5
        addSequential(new WaitCommand(), 0.3);
        // Push solenoid out
        addSequential(new ToggleHatchPushSolenoid(out));
        // Wait 0.5
        addSequential(new WaitCommand(), 0.1);
        // Push solenoid In
        addSequential(new ToggleHatchPushSolenoid(in));
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
