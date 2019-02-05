package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain.GearShiftState;

public class ShiftToHigh extends Command {

    public ShiftToHigh() {
        super("Shift to High Gear");
        requires(Robot.drivetrain);
    }

    @Override
    public void initialize() {
        
        if (GearShiftState.check(GearShiftState.LOW)) {
            Robot.drivetrain.shifterForward();
            Robot.oi.rumble(RumbleType.kRightRumble, 0.5);

        } else if (GearShiftState.check(GearShiftState.HIGH)) {
           Robot.drivetrain.shifterForward();
           Robot.oi.rumble(RumbleType.kRightRumble, 0.5);
        }
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}