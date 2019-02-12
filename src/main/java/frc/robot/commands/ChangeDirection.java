package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain.DirectionState;

public class ChangeDirection extends InstantCommand {
    public ChangeDirection() {
        super("Change Robot Direction");
        requires(Robot.drivetrain);
    }

    @Override
    protected void initialize() {
        DirectionState state = DirectionState.update();

        System.out.println("State is " + state.toString());
        SmartDashboard.putString("Direction State", state.toString());
        if (state == DirectionState.FORWARD) {

            Robot.drivetrain.faceBackwards();
            Robot.oi.rumble(RumbleType.kRightRumble, 0.5);
            Robot.oi.rumble(RumbleType.kLeftRumble, 0.5);

        } else if (state == DirectionState.BACKWARD) {
            System.out.println("Face forwards");
            Robot.drivetrain.faceForwards();
            Robot.oi.rumble(RumbleType.kRightRumble, 0.5);
            Robot.oi.rumble(RumbleType.kLeftRumble, 0.5);
        } else {
            System.out.println("Invalid State");
            Robot.drivetrain.faceForwards();;
        }

        DirectionState.update();

    }
}