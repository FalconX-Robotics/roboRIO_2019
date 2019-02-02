package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class LaunchUpperCargo extends CommandGroup {

    public LaunchUpperCargo() {
        // Pushes out, then retract
        addSequential(new ToggleCargoUpperSolenoid(Value.kForward));
        addSequential(new WaitCommand(), 0.5); // A delay of 2 second
        addSequential(new ToggleCargoUpperSolenoid(Value.kReverse));
    }

}