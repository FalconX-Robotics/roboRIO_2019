package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;
import frc.robot.subsystems.Cargo.CargoState;

public class ToggleCargoLowerSolenoid extends InstantCommand {
    Value toggleValue;

    public ToggleCargoLowerSolenoid(Value toggleValue) {
        super("Toggle Cargo Lower Solenoid");
        this.toggleValue = toggleValue;
    }

    public void initialize() {
        if (CargoState.check(CargoState.READY))
            Robot.cargo.toggleCargoLowerSolenoid(toggleValue);
        else {
            Robot.cargo.toggleCargoUpperSolenoid(Value.kReverse);
            Robot.cargo.toggleCargoLowerSolenoid(Value.kForward);
            Robot.cargo.toggleCargoLowerSolenoid(toggleValue);
        }
    }
}