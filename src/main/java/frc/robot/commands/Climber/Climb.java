// package frc.robot.commands.Climber;

// import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
// import edu.wpi.first.wpilibj.command.CommandGroup;
// import frc.robot.commands.WaitCommand;

// public class Climb extends CommandGroup {

//     public Climb() {
//         // Extend pistons
//         addSequential(new ClimberToggleFrontSolenoid(Value.kForward));
//         addSequential(new ClimberToggleBackSolenoid(Value.kForward));
//         // Wait for _ seconds
//         addSequential(new WaitCommand(), 5);
//         // Retract 1st piston
//         addSequential(new ClimberToggleFrontSolenoid(Value.kReverse));
//         // Motor drive
//         addSequential(new SetClimberMotorSpeed(0.5));
//         // Wait for _ seconds
//         addSequential(new WaitCommand(), 4);
//         // Stop motor
//         addSequential(new SetClimberMotorSpeed(0));
//         // Retract 2nd piston
//         addSequential(new ClimberToggleBackSolenoid(Value.kReverse));
//         // Motor drive
//         addSequential(new SetClimberMotorSpeed(0.5));
//         // Wait for _ seconds
//         addSequential(new WaitCommand(), 2);
//         // Stop motor
//         addSequential(new SetClimberMotorSpeed(0));  

//     }
// }