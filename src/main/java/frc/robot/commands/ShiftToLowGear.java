package main.java.frc.robot.commands;

import org.usfirst.frc.team6662.robot.subsystems.Drivetrain.Gear;

public class ShiftToLowGear extends ShiftGear {
    public ShiftToLowGear() {
        super(Gear.LOW);
    }
}