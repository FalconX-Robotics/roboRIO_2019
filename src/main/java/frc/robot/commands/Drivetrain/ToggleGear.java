package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain.GearShiftState;

public class ToggleGear extends Command {

    public ToggleGear() {
        super("Toggle Gear");
        requires(Robot.drivetrain);
    }

    @Override
    public void initialize() {
        GearShiftState.update();
        if (GearShiftState.check(GearShiftState.LOW)) {
            Robot.drivetrain.shifterForward();

        } else if (GearShiftState.check(GearShiftState.HIGH)) {
            Robot.drivetrain.shifterBackward();
        }
    }

    @Override
    protected void end() {
        GearShiftState.update();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}