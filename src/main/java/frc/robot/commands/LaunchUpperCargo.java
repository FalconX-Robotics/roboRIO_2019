package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LaunchUpperCargo extends CommandGroup {

    public LaunchUpperCargo() {
        super("Launch Upper Cargo");
        // Push ball out and in with cargo upper solenoid
        addSequential(new ToggleCargoLowerSolenoid(Value.kForward));
        addSequential(new ToggleCargoUpperSolenoid(Value.kForward));
        addSequential(new WaitCommand(), 0.5); // A delay of 0.5 seconds
        addSequential(new ToggleCargoUpperSolenoid(Value.kReverse));
    }
}