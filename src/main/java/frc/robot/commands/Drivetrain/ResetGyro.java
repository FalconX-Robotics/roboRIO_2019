package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.command.InstantCommand;
import static frc.robot.Robot.drivetrain;

/**
 * ResetGyro
 */
public class ResetGyro extends InstantCommand {

    public ResetGyro() {
        super("Reset Gyro");
    }

    protected void initialize() {
        drivetrain.resetGyro();
    }
    
}