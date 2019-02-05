package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class LaunchLowerCargo extends CommandGroup {

    public LaunchLowerCargo() {
        super("Launch Lower Cargo");
        // Reverse then immediately push out when button goes down
        addSequential(new ToggleCargoLowerSolenoid(Value.kReverse));
        addSequential(new WaitCommand(), 2); // A delay of 2 second
        addSequential(new ToggleCargoLowerSolenoid(Value.kForward));
    }

}