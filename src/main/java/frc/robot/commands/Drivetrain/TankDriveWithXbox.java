package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Drivetrain.DirectionState;

public class TankDriveWithXbox extends Command {
    private Notifier notifier;

    public TankDriveWithXbox() {
        super("Tank Drive with Xbox Controller");
        requires(Robot.drivetrain);
        // notifier = new Notifier(this::putEncoderValue);
        // notifier.startPeriodic(1); //display encoder values to sdb
    }

    @Override
    public void execute() {
        double leftSpeed = Robot.oi.getDriverLeftYAxis();
        double rightSpeed = Robot.oi.getDriverRightYAxis();

        // Robot.oi.rumble(RumbleType.kLeftRumble, Math.abs(leftSpeed));
        // Robot.oi.rumble(RumbleType.kRightRumble, Math.abs(rightSpeed));

        Robot.drivetrain.tankDrive(leftSpeed, rightSpeed);

        // long startTime = System.nanoTime();
        // double startDistance = Robot.drivetrain.getEncoderDistance();

        // SmartDashboard.putNumber("Encoder Value", Robot.drivetrain.getEncodersCount());

        // if (System.nanoTime() - startTime > RobotMap.UPDATE_TIME) {
        //     startTime = System.nanoTime();
        //     SmartDashboard.putNumber("Encoder Angle", Robot.drivetrain.getEncodersCount());
        //     SmartDashboard.putNumber("Robot Speed (cm/s)", Robot.drivetrain.getSpeed());
        //     if (Robot.drivetrain.getSpeed() > RobotMap.ROBOT_GEAR_SHIFT_SPEED) {
        //         Robot.drivetrain.shifterForward();
        //     } else {
        //         Robot.drivetrain.shifterBackward();
        //     }
        // }
    }

    private void putEncoderValue() {
        SmartDashboard.putNumber("Speed", Robot.drivetrain.getSpeed());
        SmartDashboard.putNumber("Distance in cm", Robot.drivetrain.getEncoderDistance());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}