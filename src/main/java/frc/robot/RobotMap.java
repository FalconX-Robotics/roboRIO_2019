/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  public static final int FRONT_LEFT_MOTOR = 2;
  public static final int REAR_LEFT_MOTOR = 3;
  public static final int FRONT_RIGHT_MOTOR = 0;
  public static final int REAR_RIGHT_MOTOR = 1;

  public static final int SHFTER_REVERSE = 0;
  public static final int SHIFTER_FORWARD = 1;

  public static final int HATCH_GRAB_IN = 0;
  public static final int HATCH_GRAB_OUT = 1;
  public static final int HATCH_PUSH_IN =  2;
  public static final int HATCH_PUSH_OUT = 3;

  public static final int HATCH_GRAB_MODULE_NUM = 1; //numbers not determined
  public static final int HATCH_PUSH_MODULE_NUM = 2;

}
