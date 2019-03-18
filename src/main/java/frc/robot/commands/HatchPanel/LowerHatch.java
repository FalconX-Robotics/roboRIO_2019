/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.HatchPanel;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.HatchPanelGrabber;

public class LowerHatch extends Command {

  private double speed = -0.4; //set speed of motor

  public LowerHatch() {
    super("Lower Hatch");
    requires(Robot.hatchPanelGrabber);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    Robot.hatchPanelGrabber.runHatchMotor(speed);
  }

  @Override
  protected boolean isFinished() {
    return !Robot.hatchPanelGrabber.getBottomSwitch();
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
