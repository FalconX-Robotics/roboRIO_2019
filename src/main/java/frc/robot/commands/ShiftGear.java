package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class ShiftGear extends InstantCommand {
    private Value toShift;

    public ShiftGear(Value toShift) {
        super("ShiftGear");
        this.toShift = toShift;
    }

    @Override
    public void execute() {
        
        if (toShift == Value.kReverse) {
            Robot.drivetrain.shifterForward();
            SmartDashboard.putString("Gear", "High");
        } else {
            Robot.drivetrain.shifterBackward();
            SmartDashboard.putString("Gear", "Low");
        }
    }
}