package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LaunchPanel extends CommandGroup {
    private static DoubleSolenoid.Value in = DoubleSolenoid.Value.kReverse;

    public LaunchPanel() {
        super("Launch Panel");
        // Grab solenoid in
        addSequential(new ToggleHatchGrabSolenoid(in));
        // Wait 0.3
        addSequential(new WaitCommand(), 0.3);
        // Push solenoid out
        addSequential(new ToggleHatchPushSolenoid(true));
        // Wait 0.1
        addSequential(new WaitCommand(), 0.1);
        // Push solenoid In
        addSequential(new ToggleHatchPushSolenoid(false));
    }
}
