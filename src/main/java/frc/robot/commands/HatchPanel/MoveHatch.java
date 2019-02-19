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
import frc.robot.subsystems.HatchPanelGrabber.HatchPanelPositionState;

public class MoveHatch extends Command {
  private static double speed = 0.5; // set speed of motorxd
  private HatchPanelPositionState state = HatchPanelPositionState.update();

  public MoveHatch() {
    super("Move Hatch");
    requires(Robot.hatchPanelGrabber);
  }

  @Override
  protected void initialize() {
    SmartDashboard.putBoolean("Move hatch running", true);
    state = HatchPanelPositionState.update();
    if (state == HatchPanelPositionState.IN_BETWEEN) {
      SmartDashboard.putString("Was in between", state.toString());
      state = HatchPanelPositionState.DOWN;  
    }
    SmartDashboard.putString("Move hatch state", state.toString());
  }

  @Override
  protected void execute() {
    if (state == HatchPanelPositionState.UP) {
      // Robot.hatchPanelGrabber.runHatchMotor(speed * -1);
    } else {
      //  Robot.hatchPanelGrabber.runHatchMotor(speed);
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
    SmartDashboard.putBoolean("Move hatch running", false);
    // Robot.hatchPanelGrabber.runHatchMotor(0);
    HatchPanelPositionState.update();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
