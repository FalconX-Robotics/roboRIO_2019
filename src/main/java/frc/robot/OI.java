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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.commands.ShiftGear;
import frc.robot.commands.ToggleHatch;
import frc.robot.commands.LaunchPanel;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public static final int DRIVER_PORT = 0;

  //public static final int SHIFT_GEAR_BUTTON = 2; // Xbox B button
  public static final double XBOX_LEFT_Y_THRESHOLD = 0.1;
  public static final double XBOX_RIGHT_Y_THRESHOLD = 0.1;
  public static final int TOGGLE_HATCH_PANEL_BUTTON = 3; // Xbox X button
  public static final int LAUNCH_HATCH_PANEL_BUTTON = 4; // Xbox Y button
  public static final int CLIMBER_PART_ONE_BUTTON = 5; // Xbox X button
  public static final int CLIMBER_PART_TWO_BUTTON = 6; // Xbox Y button

  XboxController Driver = new XboxController(DRIVER_PORT);

  //private Button shiftGearButton = new JoystickButton(Driver, SHIFT_GEAR_BUTTON);
  private Button toggleHatchButton = new JoystickButton(Driver, TOGGLE_HATCH_PANEL_BUTTON);
  private Button launchHatchButton = new JoystickButton(Driver, LAUNCH_HATCH_PANEL_BUTTON);

  //private Button climberPartOneButton = new JoystickButton(Driver, CLIMBER_PART_ONE_BUTTON);
  //private Button climberPartTwoButton = new JoystickButton(Driver, CLIMBER_PART_TWO_BUTTON);

  public OI() {
		//shiftGearButton.whenPressed(new ShiftGear());
    toggleHatchButton.whenReleased(new ToggleHatch());
    launchHatchButton.whenReleased(new LaunchPanel());
    //climberPartOneButton.whenPressed(new ClimberPartOne());
    //climberPartTwoButton.whenPressed(new EnableClimberBackSolenoid());
    SmartDashboard.putNumber("TestToggleHatch", 999);
  }

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
