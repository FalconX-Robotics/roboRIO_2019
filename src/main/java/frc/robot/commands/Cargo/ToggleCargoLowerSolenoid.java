// package frc.robot.commands.Cargo;

// import edu.wpi.first.wpilibj.command.InstantCommand;
// import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
// import frc.robot.Robot;
// import frc.robot.subsystems.Cargo.CargoState;

// public class ToggleCargoLowerSolenoid extends InstantCommand {
//     Value toggleValue;
//     boolean update;

//     public ToggleCargoLowerSolenoid(Value toggleValue, boolean update) {
//         super("Toggle Cargo Lower Solenoid");
//         this.toggleValue = toggleValue;
//         this.update = update;
//     }

//     public void initialize() {
//         if (update) CargoState.update();
//         if (CargoState.check(CargoState.INVALID) && update) {
//             Robot.cargo.toggleCargoUpperSolenoid(Value.kReverse);
//             Robot.cargo.toggleCargoLowerSolenoid(Value.kForward);
//         }
//         Robot.cargo.toggleCargoLowerSolenoid(toggleValue);
//     }
// }