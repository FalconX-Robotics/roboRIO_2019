package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain.GearShiftState;

public class ToggleGear extends Command {

    public ToggleGear() {
        super("ToggleGear");
        requires(Robot.drivetrain);
        // super(Robot.drivetrain.getShifterValue() == Value.kForward ? Value.kReverse :
        // Value.kForward);
    }

    @Override
    public void initialize() {
        if (GearShiftState.check(GearShiftState.LOW)) {
            Robot.drivetrain.shifterForward();

        } else if (GearShiftState.check(GearShiftState.HIGH)) {
           Robot.drivetrain.shifterBackward();
        }
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}