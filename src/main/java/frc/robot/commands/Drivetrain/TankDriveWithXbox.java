package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class TankDriveWithXbox extends Command {

    private static final int SPEED_TO_SHIFT_UP = 0; // 0's are placeholders
    private static final int SPEED_TO_SHIFT_DOWN = 0;

    private boolean canShift = true;
    Timer timer = new Timer();

    public TankDriveWithXbox() {
        super("Tank Drive with Xbox Controller");
        requires(Robot.drivetrain);
    }

    @Override
    public void execute() {
        double leftSpeed = Robot.oi.getDriverLeftYAxis();
        double rightSpeed = Robot.oi.getDriverRightYAxis();

        double actualLeftSpeed = Robot.drivetrain.getLeftEncoderSpeed();
        double actualRightSpeed = Robot.drivetrain.getRightEncoderSpeed();

        if (canShift) {
            if (actualLeftSpeed < SPEED_TO_SHIFT_DOWN && actualRightSpeed < SPEED_TO_SHIFT_DOWN) {
                Robot.drivetrain.shifterBackward();
            } else if ((actualLeftSpeed > SPEED_TO_SHIFT_UP && actualRightSpeed > SPEED_TO_SHIFT_UP)) {
                Robot.drivetrain.shifterForward();
            }
            timer.start();
            canShift = false;
        } else if (timer.get() > 2.0) {
            timer.stop();
            timer.reset();
            canShift = true;
        }

        Robot.oi.rumble(RumbleType.kLeftRumble, Math.abs(leftSpeed));
        Robot.oi.rumble(RumbleType.kRightRumble, Math.abs(rightSpeed));

        Robot.drivetrain.tankDrive(leftSpeed, rightSpeed);

        // long startTime = System.nanoTime();
        // double startDistance = Robot.drivetrain.getEncoderDistance();

        // SmartDashboard.putNumber("Encoder Value",
        // Robot.drivetrain.getEncodersCount());

        // if (System.nanoTime() - startTime > RobotMap.UPDATE_TIME) {
        // startTime = System.nanoTime();
        // SmartDashboard.putNumber("Encoder Angle",
        // Robot.drivetrain.getEncodersCount());
        // SmartDashboard.putNumber("Robot Speed (cm/s)", Robot.drivetrain.getSpeed());
        // if (Robot.drivetrain.getSpeed() > RobotMap.ROBOT_GEAR_SHIFT_SPEED) {
        // Robot.drivetrain.shifterForward();
        // } else {
        // Robot.drivetrain.shifterBackward();
        // }
        // }
    }

    private void putEncoderValue() {
        Robot.log("Speed", Robot.drivetrain.getSpeed());
        Robot.log("Distance in cm", Robot.drivetrain.getEncoderDistance());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}