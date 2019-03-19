// package frc.robot.commands.Cargo;

// import edu.wpi.first.wpilibj.command.InstantCommand;
// import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
// import frc.robot.Robot;
// import frc.robot.subsystems.Cargo.CargoState;

// public class ToggleOpenCloseSolenoid extends InstantCommand {
//     private Value toggleValue;

//     public ToggleOpenCloseSolenoid(Value toggleValue) {
//         super("Toggle Cargo Lower Solenoid");
//         this.toggleValue = toggleValue;
//     }

//     public void initialize() {
//         Robot.cargo.setOpenCloseCargo(toggleValue);
//     }
// }