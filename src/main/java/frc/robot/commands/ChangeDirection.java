package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain.DirectionState;

public class ChangeDirection extends Command {
    public ChangeDirection() {
        super("Change Robot Direction");
        requires(Robot.drivetrain);
    }

    @Override
    protected void execute() {
        if (DirectionState.check(DirectionState.FORWARD)) {
            Robot.drivetrain.faceBackwards();
        } else if (DirectionState.check(DirectionState.BACKWARD)) {
            Robot.drivetrain.faceForwards();
        }

    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}