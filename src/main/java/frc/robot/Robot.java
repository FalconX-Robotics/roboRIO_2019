/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.commands.*;
import frc.robot.commands.Climber.InitializeClimber;
import frc.robot.commands.Drivetrain.*;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {
  public static Drivetrain drivetrain;
  public static HatchPanelGrabber hatchPanelGrabber;
  public static Climber climber;
  public static ToggleGear toggleGearCommand;

  // MAKE THIS LAST
  public static OI oi;

  private CalibrateGyro calibrationCommand = null;

  public static void log(String key, Object value) {
    if (!RobotMap.loggable)
      System.out.println(value);
    else if (value instanceof Number) {
      SmartDashboard.putNumber(key, Double.parseDouble(value.toString()));
    } else if (value instanceof String) {
      SmartDashboard.putString(key, (String) value);
    } else if (value instanceof Boolean) {
      SmartDashboard.putBoolean(key, (boolean) value);
    } else if (value instanceof SendableBase) {
      SmartDashboard.putData(key, (SendableBase) value);
    } else if (value != null) {
      SmartDashboard.putString(key, value.toString());
    }
  }

  @Override
  public void robotInit() {
    drivetrain = new Drivetrain();
    //hatchPanelGrabber = new HatchPanelGrabber();
    //climber = new Climber();
    // Vision.initialize();

    // MAKE THIS LAST
    oi = new OI();

    // drivetrain.faceForwards();
    // drivetrain.shifterBackward();
    // climber.reverseBackSolenoid();
    // climber.reverseFrontSolenoid();

    // SMART_DASH_BOARD
    SmartDashboard.putData("Drivetrain", drivetrain);
    // SmartDashboard.putData("Climber", climber);
    // SmartDashboard.putData("Hatch", hatchPanelGrabber);
    // SmartDashboard.putData("InitalizeClimber", new InitializeClimber());
    // SmartDashboard.putData("HatchPanelGrabber", hatchPanelGrabber);
    Robot.log("ResetGyro", new ResetGyro());
  }

  @Override
  public void robotPeriodic() {
    Robot.log("Encoder Speed", drivetrain.getSpeed());
    Robot.log("Encoder Distance", drivetrain.getEncoderDistance());

    Robot.log("Gyro Yaw: ", drivetrain.getYaw());
    Robot.log("Gyro Pitch: ", drivetrain.getPitch());
    Robot.log("Gyro Roll: ", drivetrain.getRoll());

    Robot.log("Raw Yaw", drivetrain.getGyroData()[0]);
    Robot.log("Raw Pitch", drivetrain.getGyroData()[1]);
    Robot.log("Raw Roll", drivetrain.getGyroData()[2]);
  }

  @Override
  public void disabledInit() {

  }

  @Override
  public void disabledPeriodic() {
    if (calibrationCommand == null && oi.calibrateGyro.get()) {
      calibrationCommand = new CalibrateGyro();
    } else if (calibrationCommand != null && !calibrationCommand.isRunning()) {
      Robot.log("Gyro Calibration Status", "Ready");
      calibrationCommand = null;
    }
    // climber.reverseBackSolenoid();
    // climber.reverseFrontSolenoid();

    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    if (calibrationCommand != null) {
      calibrationCommand.cancel();
      calibrationCommand = null;
    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if (calibrationCommand != null) {
      calibrationCommand.cancel();
      calibrationCommand = null;
    }
    drivetrain.resetGyro();
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

