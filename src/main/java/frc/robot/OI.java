/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ToggleHatch;
import frc.robot.commands.ChangeDirection;
import frc.robot.commands.InitializeClimber;
import frc.robot.commands.LaunchPanel;
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
  public static final int SHIFT_GEAR_BUTTON = 2; // Xbox B button
  public static final int CHANGE_ROBOT_DIRECTION_BUTTON = 1; // Xbox A button

  public static final double XBOX_LEFT_Y_THRESHOLD = 0.1;
  public static final double XBOX_RIGHT_Y_THRESHOLD = 0.1;
  public static final double TRIGGER_THRESHOLD = 0.1;

  public static final int CLIMBER_INITIALIZE_BUTTON = 4; // Xbox Y button
  public static final int CLIMBER_FRONT_BUTTON = 8; // Xbox Start button
  public static final int CLIMBER_BACK_BUTTON = 7; // Xbox Select button
  // Driver Two
  public static final int TOGGLE_HATCH_PANEL_BUTTON = 3; // Xbox X button
  public static final int LAUNCH_HATCH_PANEL_BUTTON = 4; // Xbox Y button

  XboxController driverOne = new XboxController(DRIVER_PORT_ONE);
  XboxController driverTwo = new XboxController(DRIVER_PORT_TWO);

  // Climber (Driver One)
  private Button climberFrontButton = new JoystickButton(driverOne, CLIMBER_FRONT_BUTTON);
  private Button climberBackButton = new JoystickButton(driverOne, CLIMBER_BACK_BUTTON);
  // Hatch (Driver Two)
  private Button toggleHatchButton = new JoystickButton(driverTwo, TOGGLE_HATCH_PANEL_BUTTON);
  private Button launchHatchButton = new JoystickButton(driverTwo, LAUNCH_HATCH_PANEL_BUTTON);
  // Drivetrain (Driver One)
  private Button shiftGearButton = new JoystickButton(driverOne, SHIFT_GEAR_BUTTON);
  private Button initializeClimberButton = new JoystickButton(driverOne, CLIMBER_INITIALIZE_BUTTON);
  private Button changeRobotDirectionButton = new JoystickButton(driverOne, CHANGE_ROBOT_DIRECTION_BUTTON);

  public OI() {
    // Climber
    //climberFrontButton.whenPressed(new ToggleFrontClimberSolenoid());
    //climberBackButton.whenPressed(new ToggleBackClimberSolenoid());
    //initializeClimberButton.whenPressed(new InitializeClimber());
    // Hatch
    //toggleHatchButton.whenPressed(new ToggleHatch());
    //launchHatchButton.whenPressed(new LaunchPanel());
    // Drivetrain
    shiftGearButton.whenPressed(new ToggleGear());
    changeRobotDirectionButton.whenPressed(new ChangeDirection());
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

}
