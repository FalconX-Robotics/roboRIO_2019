/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static Drivetrain drivetrain;
  public static HatchPanelGrabber hatchPanelGrabber;
  public static Climber climber;
  public static Cargo cargo;
  public static ToggleGear toggleGearCommand;

  // MAKE THIS LAST
  public static OI oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    // hatchPanelGrabber = new HatchPanelGrabber();
    // climber = new Climber();
    // cargo = new Cargo();
    // Vision.initialize();

    // if (RobotMap.DRIVETRAIN_ENABLED) {
      drivetrain = new Drivetrain();
    // } if (RobotMap.HATCH_PANEL_GRABBER_ENABLED) {
      hatchPanelGrabber = new HatchPanelGrabber();
    // } if (RobotMap.CLIMBER_ENABLED) {
      climber = new Climber();
    // } if (RobotMap.CARGO_ENABLED) {
      cargo = new Cargo();
    // } if (RobotMap.VISION_ENABLED) {
      //Vision.initialize();
    // }

    // MAKE THIS LAST
    oi = new OI();

    // SMART_DASH_BOARD
    // SmartDashboard.putData("Drivetrain", drivetrain);
    // SmartDashboard.putData("Climber", climber);
    // SmartDashboard.putData("HatchPanelGrabber", hatchPanelGrabber);
  }

  @Override
  public void robotPeriodic() {
    // SmartDashboard.putNumber("Gyro angle", drivetrain.getGyroAngle());
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}
