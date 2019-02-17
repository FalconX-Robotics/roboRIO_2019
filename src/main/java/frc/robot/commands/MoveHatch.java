/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.HatchPanelGrabber.HatchPanelPositionState;

public class MoveHatch extends Command {

  private double timeLimit = 1; //set time based on speed
  private double speed = 1; //set speed of motor

  public MoveHatch() {
    super("Move Hatch");
    requires(Robot.hatchPanelGrabber);
  }

  @Override
  protected void initialize() {
    setTimeout(timeLimit);
  }

  @Override
  protected void execute() {
    if (HatchPanelPositionState.check(HatchPanelPositionState.UP))
    {
        Robot.hatchPanelGrabber.runHatchMotor(speed * -1);
    } else {
        Robot.hatchPanelGrabber.runHatchMotor(speed);
    }
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  @Override
  protected void end() {
    Robot.hatchPanelGrabber.runHatchMotor(0);
    HatchPanelPositionState.update();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
