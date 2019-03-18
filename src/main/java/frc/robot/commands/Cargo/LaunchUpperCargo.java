package frc.robot.commands.Cargo;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.WaitCommand;

public class LaunchUpperCargo extends CommandGroup {

    public LaunchUpperCargo() {
        super("Launch Upper Cargo");
        // Push ball out and in with cargo upper solenoid
        addSequential(new ToggleOpenCloseSolenoid(Value.kForward));
        addSequential(new WaitCommand(), 1.5);
        addSequential(new ToggleCargoLowerSolenoid(Value.kForward, true));
        addSequential(new ToggleCargoUpperSolenoid(Value.kForward, false));
        addSequential(new WaitCommand(), 0.5); 
        addSequential(new ToggleCargoUpperSolenoid(Value.kReverse, false));
        addSequential(new WaitCommand(), 2);
        addSequential(new ToggleOpenCloseSolenoid(Value.kReverse));
    }
}