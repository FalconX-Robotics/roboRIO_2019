/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.util.Logger;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import edu.wpi.first.wpilibj.AnalogGyro;

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
  public static String fileSeparator = System.getProperty("file.separator");
  public static Logger errorLog = new Logger("errorLog", "src" + fileSeparator + "main" + fileSeparator + "java" + fileSeparator + "frc" + fileSeparator + "robot" + fileSeparator + "util", true, false); //ez;

  // MAKE THIS LAST
  public static OI oi;

  // private static Dictionary commandsDictionary = new Hashtable();

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  // public static void addToCommandsTable() {
  // commandsDictionary.put("ToggleGear", new ToggleGear());
  // commandsDictionary.put("WaitCommand", new WaitCommand());
  // }

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    drivetrain = new Drivetrain();
    //hatchPanelGrabber = new HatchPanelGrabber();
    //climber = new Climber();
    //cargo = new Cargo();

    // MAKE THIS LAST
    oi = new OI();

    // drivetrain.shifterBackward();

    // SMART_DASH_BOARD
    // addToCommandsTable();
    toggleGearCommand = new ToggleGear();

    SmartDashboard.putData("Auto mode", m_chooser);
    // Subsystems
    SmartDashboard.putData("Drivetrain", drivetrain);
    // SmartDashboard.putData("Climber", new Climber());
    // SmartDashboard.putData("HatchPanelGrabber", new HatchPanelGrabber());

    // Command
    // for (String name : commandsDictionary.keySet()) {

    // }

    errorLog.log("Robot initialized");
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Gyro angle", drivetrain.getGyroAngle());
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
     * switch(autoSelected) { case "My Auto": autonomousCommand = new
     * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
     * ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
