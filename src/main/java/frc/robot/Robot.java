/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

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
  public static Cargo cargo;
  public static ToggleGear toggleGearCommand;

  // MAKE THIS LAST
  public static OI oi;

  private CalibrateGyro calibrationCommand = null;

  @Override
  public void robotInit() {
    drivetrain = new Drivetrain();
    hatchPanelGrabber = new HatchPanelGrabber();
    climber = new Climber();
    cargo = new Cargo();
    // Vision.initialize();

    // MAKE THIS LAST
    oi = new OI();

    // drivetrain.faceForwards();
    drivetrain.shifterBackward();

    // SMART_DASH_BOARD
    SmartDashboard.putData("Drivetrain", drivetrain);
    SmartDashboard.putData("Climber", climber);
    SmartDashboard.putData("Cargo", cargo);
    SmartDashboard.putData("Hatch", hatchPanelGrabber);
    SmartDashboard.putData("InitalizeClimber", new InitializeClimber());
    SmartDashboard.putData("ResetGyro", new ResetGyro());
    SmartDashboard.putData("HatchPanelGrabber", hatchPanelGrabber);
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Encoder Speed", drivetrain.getSpeed());
    SmartDashboard.putNumber("Encoder Distance", drivetrain.getEncoderDistance());
    
    SmartDashboard.putNumber("Gyro Yaw: ", drivetrain.getYaw());
    SmartDashboard.putNumber("Gyro Pitch: ", drivetrain.getPitch());
    SmartDashboard.putNumber("Gyro Roll: ", drivetrain.getRoll());

    SmartDashboard.putNumber("Raw Yaw", drivetrain.getGyroData()[0]);
    SmartDashboard.putNumber("Raw Pitch", drivetrain.getGyroData()[1]);
    SmartDashboard.putNumber("Raw Roll", drivetrain.getGyroData()[2]);
  }

  @Override
  public void disabledInit() {

  }

  @Override
  public void disabledPeriodic() {
    if (calibrationCommand == null && oi.calibrateGyro.get()) {
      calibrationCommand = new CalibrateGyro();
    } else if (calibrationCommand != null && !calibrationCommand.isRunning()) {
      SmartDashboard.putString("Gyro Calibration Status", "Ready");
      calibrationCommand = null;
    }
    climber.reverseBackSolenoid();
    climber.reverseFrontSolenoid();

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

