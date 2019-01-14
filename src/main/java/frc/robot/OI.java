/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public static final int DRIVER_PORT = 0;

  public static final int SHIFT_DOWN_BUTTON = 5; // Xbox left bumper button
  public static final int SHIFT_UP_BUTTON = 6; // Xbox right bumper button
  public static final double XBOX_LEFT_Y_THRESHOLD = 0.1;
  public static final double XBOX_RIGHT_Y_THRESHOLD = 0.1;

  XboxController Driver = new XboxController(DRIVER_PORT);

  private Button shiftDownButton = new JoystickButton(driver, SHIFT_DOWN_BUTTON);
  private Button shiftUpButton = new JoystickButton(driver, SHIFT_UP_BUTTON);


  public double getDriverLeftYAxis() {
    double rawLeftYAxis = Driver.getY(Hand.kLeft);

    return deadband(rawLeftYAxis, XBOX_LEFT_Y_THRESHOLD);

  }

  public double getDriverRightYAxis() {
    double rawRightYAxis = Driver.getY(Hand.kRight);

    return deadband(rawRightYAxis, XBOX_RIGHT_Y_THRESHOLD);

  }

  public double deadband(double input, double threshold) {
    return Math.abs(input) <= Math.abs(threshold) ? 0 : input;
  }
  
}
