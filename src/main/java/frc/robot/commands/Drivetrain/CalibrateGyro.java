/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class CalibrateGyro extends Command {
  /**
   * Add your docs here.
   */
  public CalibrateGyro() {
    super("Calibrate Gyro");
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.drivetrain.calibrate();
    SmartDashboard.putString("Gyro Calibration Status", "Calibrating");
  }

  @Override
  protected boolean isFinished() {
    if(Robot.drivetrain.calibrationFinished()) {
      //SmartDashboard.putString("Gyro Calibration Status", "Ready");
      return true;
    }
    return false;
  }

}
