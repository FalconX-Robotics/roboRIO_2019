// package frc.robot.commands.Cargo;

// import edu.wpi.first.wpilibj.command.CommandGroup;
// import frc.robot.commands.WaitCommand;
// import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

// public class LaunchLowerCargo extends CommandGroup {

//     public LaunchLowerCargo() {
//         super("Launch Lower Cargo");
//         // Reverse then immediately push out when ball goes down
//         addSequential(new ToggleOpenCloseSolenoid(Value.kForward));
//         addSequential(new WaitCommand(), 2);
//         addSequential(new ToggleCargoUpperSolenoid(Value.kReverse, true));
//         addSequential(new ToggleCargoLowerSolenoid(Value.kReverse, false));
//         addSequential(new WaitCommand(), 1.5); 
//         addSequential(new ToggleCargoLowerSolenoid(Value.kForward, false));
//         addSequential(new WaitCommand(), 2);
//         addSequential(new ToggleOpenCloseSolenoid(Value.kReverse));
//     }
// }