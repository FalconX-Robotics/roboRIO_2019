package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.subsystems.Drivetrain;

public class TankDriveWithXbox extends Command {
    private boolean canShift = true;
    Timer timer = new Timer();
    private final static int SPEED_TO_SHIFT_DOWN = 7; // previously 13 
    private final static int SPEED_TO_SHIFT_UP = 8; // previously 14

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

        if(canShift && Robot.drivetrain.getAutoShift())
        {
            // SmartDashboard.putString("Auto shift", "Is running");
            if((actualLeftSpeed < SPEED_TO_SHIFT_DOWN || actualRightSpeed < SPEED_TO_SHIFT_DOWN) && 
                Robot.drivetrain.getShifterValue() == Value.kForward)
            {
                Robot.drivetrain.shifterBackward();
                SmartDashboard.putString("Gear Shift State", "LOW");
                timer.start();
                canShift = false;
            }
            else if(actualLeftSpeed > SPEED_TO_SHIFT_UP && actualRightSpeed > SPEED_TO_SHIFT_UP && 
                Robot.drivetrain.getShifterValue() == Value.kReverse)
            {
                Robot.drivetrain.shifterForward();
                SmartDashboard.putString("Gear Shift State", "HIGH");
                timer.start();
                canShift = false;
            }
        }
        else if(timer.get() > 1.0)
        {
            timer.stop();
            timer.reset();
            canShift = true;
        }

        // Robot.oi.rumble(RumbleType.kLeftRumble, Math.abs(leftSpeed));
        // Robot.oi.rumble(RumbleType.kRightRumble, Math.abs(rightSpeed));

        Robot.drivetrain.tankDrive(leftSpeed, rightSpeed);
    }

    private void putEncoderValue() {
        SmartDashboard.putNumber("Speed", Robot.drivetrain.getSpeed());
        SmartDashboard.putNumber("Distance in m", Robot.drivetrain.getEncoderDistance());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}