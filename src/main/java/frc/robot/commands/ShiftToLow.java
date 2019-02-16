package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain.GearShiftState;

public class ShiftToLow extends Command {

    public ShiftToLow() {
        super("Shift to Low Gear");
        requires(Robot.drivetrain);
    }

    @Override
    public void initialize() {

        if (GearShiftState.check(GearShiftState.LOW)) {
            Robot.drivetrain.shifterBackward();

        } else if (GearShiftState.check(GearShiftState.HIGH)) {
            Robot.drivetrain.shifterBackward();
        }
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}