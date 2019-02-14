package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;
import frc.robot.subsystems.Cargo.CargoState;

public class ToggleCargoUpperSolenoid extends InstantCommand {
    Value toggleValue;

    public ToggleCargoUpperSolenoid(Value toggleValue) {
        super("Toggle Cargo Upper Solenoid");
        this.toggleValue = toggleValue;
    }

    public void initialize() {
        CargoState.update();
        if (!CargoState.check(CargoState.INVALID))
            Robot.cargo.toggleCargoUpperSolenoid(toggleValue);
        else {
            Robot.cargo.toggleCargoUpperSolenoid(Value.kReverse);
            Robot.cargo.toggleCargoLowerSolenoid(Value.kForward);
            Robot.cargo.toggleCargoUpperSolenoid(toggleValue);
        }
    }
}