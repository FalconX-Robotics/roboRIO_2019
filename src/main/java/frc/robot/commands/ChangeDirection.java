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
        DirectionState state = DirectionState.update();

        if (state == DirectionState.FORWARD) {
            Robot.drivetrain.faceBackwards();
        } else if (state == DirectionState.BACKWARD) {
            Robot.drivetrain.faceForwards();
        }

        DirectionState.update();

    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}