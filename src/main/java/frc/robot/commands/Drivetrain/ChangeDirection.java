package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Drivetrain.DirectionState;

public class ChangeDirection extends InstantCommand {

    public ChangeDirection() {
        super("ChangeDirection");
        requires(Robot.drivetrain);
    }

    @Override
    protected void initialize() {
        if (Drivetrain.DirectionState.check(DirectionState.FORWARD)) {
            Robot.drivetrain.faceForward();
        } else if (Drivetrain.DirectionState.check(DirectionState.BACKWARD)) {
            Robot.drivetrain.faceBackward();
        } else {
            Robot.drivetrain.faceForward();
        }
    }
}