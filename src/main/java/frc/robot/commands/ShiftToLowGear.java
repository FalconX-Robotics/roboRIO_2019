package frc.robot.commands;

import frc.robot.subsystems.Drivetrain.Gear;

public class ShiftToLowGear extends ShiftGear {
    public ShiftToLowGear() {
        super(Gear.LOW);
    }
}