package frc.robot.commands.HatchPanel;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.WaitCommand;

public class LaunchPanel extends CommandGroup {
    private static DoubleSolenoid.Value in = DoubleSolenoid.Value.kReverse;

    public LaunchPanel() {
        super("Launch Panel");
        // Grab solenoid in
        addSequential(new ToggleHatchGrabSolenoid(in, false));
        // Wait 0.3
        addSequential(new WaitCommand(), 0.3);
        // Push solenoid out
        addSequential(new ToggleHatchPushSolenoid(true, false));
        // Wait 0.1
        addSequential(new WaitCommand(), 0.1);
        // Push solenoid In
        addSequential(new ToggleHatchPushSolenoid(false, true));
    }
}
