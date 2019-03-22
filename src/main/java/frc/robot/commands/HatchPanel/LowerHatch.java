/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.HatchPanel;

import edu.wpi.first.wpilibj.command.Command;
<<<<<<< HEAD
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.HatchPanelGrabber;

public class LowerHatch extends Command {

  private double speed = -0.4; //set speed of motor

  public LowerHatch() {
    super("Lower Hatch");
=======
import frc.robot.Robot;

<<<<<<< HEAD:src/main/java/frc/robot/commands/HatchPanel/RaiseHatch.java
public class RaiseHatch extends Command {

  private final double speed = 0.4; //set speed for motor

  public RaiseHatch() {
    super("Raise Hatch");
=======
public class LowerHatch extends Command {

  private double speed = -0.4; // set speed of motor

  public LowerHatch() {
    super("Lower Hatch");
>>>>>>> 84dea5008d5e3a0e2d8bcaac2979368ad58a430f:src/main/java/frc/robot/commands/HatchPanel/LowerHatch.java
>>>>>>> 84dea5008d5e3a0e2d8bcaac2979368ad58a430f
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
<<<<<<< HEAD
    return !Robot.hatchPanelGrabber.getBottomSwitch();
=======
<<<<<<< HEAD:src/main/java/frc/robot/commands/HatchPanel/RaiseHatch.java
    return !Robot.hatchPanelGrabber.getTopSwitch();
=======
    return !Robot.hatchPanelGrabber.getBottomSwitch();
>>>>>>> 84dea5008d5e3a0e2d8bcaac2979368ad58a430f:src/main/java/frc/robot/commands/HatchPanel/LowerHatch.java
>>>>>>> 84dea5008d5e3a0e2d8bcaac2979368ad58a430f
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
