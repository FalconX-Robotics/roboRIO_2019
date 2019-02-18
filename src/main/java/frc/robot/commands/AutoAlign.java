/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Vision;

public class AutoAlign extends Command {
  public AutoAlign() {
    super("Auto Align");
    requires(Robot.drivetrain);
  }

  protected void initialize() {
  }

  protected void execute() {
    Vision.autoAlign();
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
  }
  
  @Override
  protected void interrupted() {
  }
}
