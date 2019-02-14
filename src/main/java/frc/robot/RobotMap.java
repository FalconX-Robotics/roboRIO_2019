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
  public static final int REAR_LEFT_MOTOR = 4;
  public static final int FRONT_RIGHT_MOTOR = 5;
  public static final int REAR_RIGHT_MOTOR = 3;

  public static final boolean ENABLE_TOGGLE_GEAR_SHIFT = false;
  public static final int SHIFTER_FORWARD = 0;
  public static final int SHIFTER_REVERSE = 1;

  public static final int HATCH_GRAB_FORWARD = 0;
  public static final int HATCH_GRAB_REVERSE = 1;

  public static final int HATCH_PUSH = 2;

  public static final int FRONT_FORWARD_CLIMB_SOLENOID = 6;
  public static final int FRONT_REVERSE_CLIMB_SOLENOID = 7;

  public static final int BACK_FORWARD_CLIMB_SOLENOID = 8;
  public static final int BACK_REVERSE_CLIMB_SOLENOID = 9;

  public static final int CARGO_UPPER_PISTON_IN = 2;
  public static final int CARGO_UPPER_PISTON_OUT = 3;
  public static final int CARGO_LOWER_PISTON_IN = 6;
  public static final int CARGO_LOWER_PISTON_OUT = 7;

  public static final int CLIMBER_MOTOR = 6;

  public static final int COMPRESSOR = 0;
  public static final int RELAY_COMPRESSOR = 1;
  public static final int PRESSURE_SWITCH_DI = 0;
  public static final int LEFT_ENCODER_CHANNEL_A = 0;
  public static final int LEFT_ENCODER_CHANNEL_B = 1;
  public static final int RIGHT_ENCODER_CHANNEL_A = 0;
  public static final int RIGHT_ENCODER_CHANNEL_B = 1;
  public static final int ROBOT_GEAR_SHIFT_SPEED = 85; // This is a random value not suited for the drivetrain, in cm.
  public static final int COUNTS_PER_REVOLUTION = 4096; // SRX Magnetic Encoder
  // https://www.ctr-electronics.com/downloads/pdf/Magnetic%20Encoder%20User's%20Guide.pdf
  // Section 1.4s
  public static final double WHEEL_DIAMETER = 15.24; // in cm
  public static final double DISTANCE_TO_SHIFT = 1; // in cm (per 0.2 seconds)
  public static final long UPDATE_TIME = (long) 200000000; // in nano seconds (for updating gear shifting)

  public static final boolean DRIVETRAIN_ENABLED = true;
  public static final boolean HATCH_PANEL_GRABBER_ENABLED = true;
  public static final boolean CLIMBER_ENABLED = false;
  public static final boolean CARGO_ENABLED = true;
  public static final boolean VISION_ENABLED = false;
}