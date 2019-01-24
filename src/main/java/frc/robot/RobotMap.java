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
  public static final int FRONT_LEFT_MOTOR = 0;
  public static final int REAR_LEFT_MOTOR = 1;
  public static final int FRONT_RIGHT_MOTOR = 2;
  public static final int REAR_RIGHT_MOTOR = 3;

  public static final int SHIFTER_FORWARD = 0;
  public static final int SHIFTER_REVERSE = 1;

  public static final int HATCH_GRAB_FORWARD = 4;
  public static final int HATCH_GRAB_REVERSE = 5;

  public static final int HATCH_PUSH_FORWARD = 2;
  public static final int HATCH_PUSH_REVERSE = 3;

  public static final int FRONT_FORWARD_CLIMB_SOLENOID = 6;
  public static final int FRONT_REVERSE_CLIMB_SOLENOID = 7;

  public static final int BACK_FORWARD_CLIMB_SOLENOID = 7;
  public static final int BACK_REVERSE_CLIMB_SOLENOID = 7;

  public static final int CLIMBER_MOTOR = 4;

  public static final int COMPRESSOR = 0;

  public static final int encoderChannelA = 0;
  public static final int encoderChannelB = 1;
}
