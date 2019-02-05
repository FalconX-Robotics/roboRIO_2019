/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ToggleHatch;
import frc.robot.commands.ChangeDirection;
import frc.robot.commands.InitializeClimber;
import frc.robot.commands.LaunchLowerCargo;
import frc.robot.commands.LaunchPanel;
import frc.robot.commands.LaunchUpperCargo;
import frc.robot.commands.ShiftToHigh;
import frc.robot.commands.ShiftToLow;
import frc.robot.commands.ToggleBackClimberSolenoid;
import frc.robot.commands.ToggleFrontClimberSolenoid;
import frc.robot.commands.ToggleGear;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
  public static final int DRIVER_PORT_ONE = 0;
  public static final int DRIVER_PORT_TWO = 1;

  // Driver One
  public static final int SHIFT_GEAR_BUTTON = 5; // Xbox Left Bumper
  public static final int SECOND_SHIFT_GEAR_BUTTON = 6; // Xbox Right Bumper
  public static final int CHANGE_ROBOT_DIRECTION_BUTTON = 1; // Xbox A button

  public static final double XBOX_LEFT_Y_THRESHOLD = 0.1;
  public static final double XBOX_RIGHT_Y_THRESHOLD = 0.1;
  public static final double TRIGGER_THRESHOLD = 0.1;

  public static final int CLIMBER_INITIALIZE_BUTTON = 4; // Xbox Y button
  public static final int CLIMBER_FRONT_BUTTON = 8; // Xbox Start button
  public static final int CLIMBER_BACK_BUTTON = 7; // Xbox Select button
  // Driver Two
  public static final int TOGGLE_UPPER_CARGO_BUTTON = 2; // Xbox B Button
  public static final int TOGGLE_LOWER_CARGO_BUTTON = 1; // Xbox A Button

  public static final int TOGGLE_HATCH_PANEL_BUTTON = 3; // Xbox X button
  public static final int LAUNCH_HATCH_PANEL_BUTTON = 4; // Xbox Y button

  XboxController driverOne = new XboxController(DRIVER_PORT_ONE);
  XboxController driverTwo = new XboxController(DRIVER_PORT_TWO);

  // Drivetrain (Driver One)
  private Button shiftGearButton;
  private Button secondShiftGearButton;
  private Button changeRobotDirectionButton;

  // Climber (Driver One)
  private Button initializeClimberButton;
  private Button climberFrontButton;
  private Button climberBackButton;

  // Hatch (Driver Two)
  private Button toggleHatchButton;
  private Button launchHatchButton;

  // Cargo (Driver Two)
  private Button toggleUpperCargoButton;
  private Button toggleLowerCargoButton;

  public OI() {
    // Initialize button

    // Drivetrain (Driver One)
    shiftGearButton = new JoystickButton(driverOne, SHIFT_GEAR_BUTTON);
    secondShiftGearButton = new JoystickButton(driverOne, SECOND_SHIFT_GEAR_BUTTON);
    changeRobotDirectionButton = new JoystickButton(driverOne, CHANGE_ROBOT_DIRECTION_BUTTON);

    // Climber (Driver One)
    //!
    // initializeClimberButton = new JoystickButton(driverOne, CLIMBER_INITIALIZE_BUTTON);
    // climberFrontButton = new JoystickButton(driverOne, CLIMBER_FRONT_BUTTON);
    // climberBackButton = new JoystickButton(driverOne, CLIMBER_BACK_BUTTON);
    

    // Hatch (Driver Two)
    //!
    // toggleHatchButton = new JoystickButton(driverTwo, TOGGLE_HATCH_PANEL_BUTTON);
    // launchHatchButton = new JoystickButton(driverTwo, LAUNCH_HATCH_PANEL_BUTTON);

    // Cargo (Driver Two)
    //!
    // toggleUpperCargoButton = new JoystickButton(driverTwo,
    // TOGGLE_UPPER_CARGO_BUTTON);
    // toggleLowerCargoButton = new JoystickButton(driverTwo,
    // TOGGLE_LOWER_CARGO_BUTTON);

    // Bind button to command

    // Gear Shift (Driver One)
    if (RobotMap.ENABLE_TOGGLE_GEAR_SHIFT) {
      shiftGearButton.whenPressed(new ToggleGear());
      secondShiftGearButton.whenPressed(new ToggleGear());
    } else {
      shiftGearButton.whenPressed(new ShiftToLow());
      secondShiftGearButton.whenPressed(new ShiftToHigh());
    }

    // Change which direction is forward (Driver One)
    changeRobotDirectionButton.whenPressed(new ChangeDirection());

    // Climber (Driver One)
    //!
    // climberFrontButton.whenPressed(new ToggleFrontClimberSolenoid());
    // climberBackButton.whenPressed(new ToggleBackClimberSolenoid());
    // initializeClimberButton.whenPressed(new InitializeClimber());

    // Hatch (Driver Two)
    //!
    // toggleHatchButton.whenPressed(new ToggleHatch());
    // launchHatchButton.whenPressed(new LaunchPanel());

    // Cargo (Driver Two)
    //!
    // toggleUpperCargoButton.whenPressed(new LaunchUpperCargo());
    // toggleLowerCargoButton.whenPressed(new LaunchLowerCargo());
  }

  public double getDriverLeftTriggerAxis() {
    double triggerLeftAxis = driverOne.getTriggerAxis(Hand.kLeft);

    return deadband(triggerLeftAxis, TRIGGER_THRESHOLD);
  }

  public double getDriverRightTriggerAxis() {
    double triggerRightAxis = driverOne.getTriggerAxis(Hand.kRight);
    System.out.println(triggerRightAxis);
    return deadband(triggerRightAxis, TRIGGER_THRESHOLD);
  }

  public double getDriverLeftYAxis() {
    double rawLeftYAxis = driverOne.getY(Hand.kLeft);

    return deadband(rawLeftYAxis, XBOX_LEFT_Y_THRESHOLD);
  }

  public double getDriverRightYAxis() {
    double rawRightYAxis = driverOne.getY(Hand.kRight);

    return deadband(rawRightYAxis, XBOX_RIGHT_Y_THRESHOLD);
  }

  public double deadband(double input, double threshold) {
    return Math.abs(input) <= Math.abs(threshold) ? 0 : input;
  }

  public void rumble(GenericHID.RumbleType type, double value) {
    driverOne.setRumble(type, value);
  }
}
