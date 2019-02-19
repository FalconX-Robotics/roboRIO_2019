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
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
  private static final int DRIVER_PORT_ONE = 0;
  private static final int DRIVER_PORT_TWO = 1;

  // Driver One
  private static final int TOGGLE_GEAR_BUTTON = 5; //Xbox Left Bumper 
  private static final int AUTO_ALIGN_BUTTON = 6; // Xbox Right Bumper
  private static final int CHANGE_ROBOT_DIRECTION_BUTTON = 2; // Xbox B button

  private static final double XBOX_LEFT_Y_THRESHOLD = 0.05;
  private static final double XBOX_RIGHT_Y_THRESHOLD = 0.05;
  private static final double TRIGGER_THRESHOLD = 0.05;

  private static final int CLIMBER_INITIALIZE_BUTTON = 8; // Xbox Start button
  private static final int CLIMBER_FRONT_BUTTON = 3; // Xbox X button

  private static final int CLIMBER_BACK_BUTTON = 4; // Xbox Y button
  // private static final int CLIMBER_BUTTON = 8;

  private static final int DIRECTION_STATE_BUTTON = 1; //Xbox A button

  private static final int PANIC_BUTTON = 7; //"back" button
  
  // Driver Two
  private static final int TOGGLE_UPPER_CARGO_BUTTON = 2; // Xbox B Button
  private static final int TOGGLE_LOWER_CARGO_BUTTON = 1; // Xbox A Button

  private static final int TOGGLE_HATCH_PANEL_BUTTON = 4; // Xbox X button
  private static final int LAUNCH_HATCH_PANEL_BUTTON = 3; // Xbox Y button

  public static final int MOVE_HATCH = 8; // Xbox start button

  XboxController driverOne = new XboxController(DRIVER_PORT_ONE);
  XboxController driverTwo = new XboxController(DRIVER_PORT_TWO);

  // Drivetrain (Driver One)
  private Button toggleGearButton = new JoystickButton(driverOne, TOGGLE_GEAR_BUTTON);
  private Button autoAlignButton = new JoystickButton(driverOne, AUTO_ALIGN_BUTTON);
  private Button changeRobotDirectionButton = new JoystickButton(driverOne, CHANGE_ROBOT_DIRECTION_BUTTON);

  // Climber (Driver One)
  private Button initializeClimberButton = new JoystickButton(driverOne, CLIMBER_INITIALIZE_BUTTON);
  private Button climberFrontButton = new JoystickButton(driverOne, CLIMBER_FRONT_BUTTON);
  private Button climberBackButton = new JoystickButton(driverOne, CLIMBER_BACK_BUTTON);
  // private Button climberButton = new JoystickButton(driverOne, CLIMBER_BUTTON);

  private Button PanicButton1 = new JoystickButton(driverOne, PANIC_BUTTON);

  private Button changeCameraDirection = new JoystickButton(driverOne, DIRECTION_STATE_BUTTON);

  // Hatch (Driver Two)
  private Button toggleHatchButton = new JoystickButton(driverTwo, TOGGLE_HATCH_PANEL_BUTTON);
  private Button launchHatchButton = new JoystickButton(driverTwo, LAUNCH_HATCH_PANEL_BUTTON);
  private Button moveHatchButton = new JoystickButton(driverTwo, MOVE_HATCH);

  // Cargo (Driver Two)
  private Button toggleLowerCargoButton = new JoystickButton(driverTwo, TOGGLE_LOWER_CARGO_BUTTON);
  private Button toggleUpperCargoButton = new JoystickButton(driverTwo, TOGGLE_UPPER_CARGO_BUTTON);

  private Button PanicButton2 = new JoystickButton(driverTwo, PANIC_BUTTON);

  public OI() {
    // Bind button to command
    //Change Direction of camera
    changeCameraDirection.whenPressed(new ToggleCameraDirection());
    // Gear Shift (Driver One)
    toggleGearButton.whenPressed(new ToggleGear());

    // Change which direction is forward (Driver One)
    changeRobotDirectionButton.whenPressed(new ChangeDirection());

    // Climber (Driver One)
    // climberButton.whenPressed(new Climb());
    climberFrontButton.whenPressed(new ToggleFrontClimberSolenoid());
    climberBackButton.whenPressed(new ToggleBackClimberSolenoid());
    initializeClimberButton.whenPressed(new InitializeClimber());

    // Auto Align
    autoAlignButton.whenPressed(new AutoAlign());

    PanicButton1.whenPressed(new Panic());

    // Hatch (Driver Two)
    toggleHatchButton.whenPressed(new ToggleHatch());
    launchHatchButton.whenPressed(new LaunchPanel());
    moveHatchButton.whenPressed(new MoveHatch());

    // Cargo (Driver Two)
    toggleUpperCargoButton.whenPressed(new LaunchUpperCargo());
    toggleLowerCargoButton.whenPressed(new LaunchLowerCargo());

    PanicButton2.whenPressed(new Panic());

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
