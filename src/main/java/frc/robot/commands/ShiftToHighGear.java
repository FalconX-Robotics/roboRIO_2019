package frc.robot.commands;

import frc.robot.subsystems.Drivetrain.Gear;

public class ShiftToHighGear extends ShiftGear {
    public ShiftToHighGear() {
        super(Gear.HIGH);
    }
}
