// package frc.robot.commands.Climber;

// import edu.wpi.first.wpilibj.command.InstantCommand;
// import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
// import frc.robot.Robot;

// public class ClimberToggleBackSolenoid extends InstantCommand {
//     private Value toValue;

//     public ClimberToggleBackSolenoid(Value toValue) {
//         super("Climber Toggle Back Solenoid");
//         requires(Robot.climber);
//         this.toValue = toValue;
//     }

//     @Override
//     public void initialize() {
//         Robot.climber.setBackSolenoid(toValue);
//     }

// }