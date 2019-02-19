/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.HatchPanelGrabber.HatchPanelPositionState;

public class MoveHatch extends Command {
  private static double speed = 0.1; // set speed of motorxd
  private static HatchPanelPositionState state = HatchPanelPositionState.update();

  public MoveHatch() {
    super("Move Hatch");
    requires(Robot.hatchPanelGrabber);
  }

  @Override
  protected void initialize() {
    state = HatchPanelPositionState.update();
    if (state == HatchPanelPositionState.IN_BETWEEN) {
      state = HatchPanelPositionState.DOWN;
    }
  }

  @Override
  protected void execute() {
    if (state == HatchPanelPositionState.UP) {
      Robot.hatchPanelGrabber.runHatchMotor(speed * -1);
    } else {
       Robot.hatchPanelGrabber.runHatchMotor(speed);
    }
  }

  @Override
  protected boolean isFinished() {
    SmartDashboard.putBoolean("Bottom Switch", Robot.hatchPanelGrabber.getBottomSwitch());
    SmartDashboard.putBoolean("Top Switch", Robot.hatchPanelGrabber.getTopSwitch());

    if (state == HatchPanelPositionState.UP) {
      return !Robot.hatchPanelGrabber.getBottomSwitch();
    } else {
      return !Robot.hatchPanelGrabber.getTopSwitch();
    }
  }

  @Override
  protected void end() {
    // Robot.hatchPanelGrabber.runHatchMotor(0);
    HatchPanelPositionState.update();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
