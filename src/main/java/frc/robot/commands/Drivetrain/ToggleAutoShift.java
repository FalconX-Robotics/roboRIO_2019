/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ToggleAutoShift extends InstantCommand {
  public ToggleAutoShift() {
    super("Toggle Auto Shift");
    requires(Robot.drivetrain);
  }

  @Override
  protected void initialize() {
    if(!Robot.drivetrain.getAutoShift()){
      Robot.drivetrain.tankDrive(0, 0);
      Robot.drivetrain.setAutoShift(true);
      Robot.drivetrain.tankDrive(0, 0);
    }
    else {
      Robot.drivetrain.tankDrive(0, 0);
      Robot.drivetrain.setAutoShift(false);
      Robot.drivetrain.tankDrive(0, 0);
    }
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
