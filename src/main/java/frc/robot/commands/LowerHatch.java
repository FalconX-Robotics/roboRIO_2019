/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LowerHatch extends Command {

  private final double timeLimit = 0; //set time based on speed
  private final double speed = 0; //set speed of motor

  public LowerHatch() {
    super("Lower Hatch");
    requires(Robot.drivetrain);
  }

  @Override
  protected void initialize() {
    setTimeout(timeLimit);
  }

  @Override
  protected void execute() {
    Robot.hatchPanelGrabber.runHatchMotor(speed);
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  @Override
  protected void end() {
    Robot.hatchPanelGrabber.runHatchMotor(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
