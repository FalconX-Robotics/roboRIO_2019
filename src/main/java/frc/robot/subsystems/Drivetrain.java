package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.commands.TankDriveWithXbox;
import frc.robot.RobotMap;

public class Drivetrain extends Subsystem {
  private WPI_TalonSRX leftFront = new WPI_TalonSRX(RobotMap.FRONT_LEFT_MOTOR);
  private WPI_TalonSRX leftRear = new WPI_TalonSRX(RobotMap.REAR_LEFT_MOTOR);
  private SpeedControllerGroup leftSide = new SpeedControllerGroup(leftFront, leftRear);

  private WPI_TalonSRX rightFront = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_MOTOR);
  private WPI_TalonSRX rightRear = new WPI_TalonSRX(RobotMap.REAR_RIGHT_MOTOR);
  private SpeedControllerGroup rightSide = new SpeedControllerGroup(rightFront, rightRear);

  // private Compressor compressor = new Compressor(RobotMap.COMPRESSOR);
  DifferentialDrive drivetrain = new DifferentialDrive(leftSide, rightSide);

  private Encoder leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_CHANNEL_A, RobotMap.LEFT_ENCODER_CHANNEL_B, false,
      Encoder.EncodingType.k4X);
  private Encoder rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_CHANNEL_A, RobotMap.RIGHT_ENCODER_CHANNEL_B, false,
      Encoder.EncodingType.k4X);

  private DoubleSolenoid shifter = new DoubleSolenoid(RobotMap.SHIFTER_FORWARD, RobotMap.SHIFTER_REVERSE);

  public Drivetrain() {
    super("Drivetrain");
    // Rear motor controllers follow front motor controllers
    leftRear.follow(leftFront);
    rightRear.follow(rightFront);

    // encoder.setMinRate(60);
    // encoder.setDistancePerPulse(5);
    // encoder.setSamplesToAverage(10);
    leftEncoder.setDistancePerPulse(findDistancePerPulse(RobotMap.COUNTS_PER_REVOLUTION));
    rightEncoder.setDistancePerPulse(findDistancePerPulse(RobotMap.COUNTS_PER_REVOLUTION));
    resetEncoders();
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    drivetrain.tankDrive(leftSpeed, rightSpeed);
  }

  public enum GearShiftState {
    LOW, HIGH;

    private static GearShiftState currentState = GearShiftState.LOW;

    public static GearShiftState get() {
      return currentState;
    }

    public static void set(GearShiftState state) {
      currentState = state;
      SmartDashboard.putString("Gear Shift State", currentState.toString());
    }

    public static boolean check(GearShiftState state) {
      if (GearShiftState.get() == state) {
        return true;
      }
      return false;
    }

  }

  public enum DirectionState {
    FORWARD, BACKWARD, INVALID;

    private static DirectionState currentState = DirectionState.FORWARD;

    public static DirectionState get() {
      return currentState;
    }

    public static void set(DirectionState state) {
      currentState = state;
      SmartDashboard.putString("Direction State", currentState.toString());
    }

    public static boolean check(DirectionState state) {
      if (DirectionState.get() == state) {
        return true;
      }
      return false;
    }

    // public static DirectionState evaluate() {
    // private DirectionState state;
    // if (leftSide.getInverted() && rightSide.getInverted()) {
    // state = BACKWARD;
    // } else if (!(leftSide.getInverted() && rightSide.getInverted())) {
    // state = FORWARD;
    // } else {
    // state = INVALID;
    // }
    // return state;
    // }
  }

  // ENCODERS
  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public double findDistancePerPulse(double coutsPerRevolution) {
    return (Math.PI * RobotMap.WHEEL_DIAMETER) / coutsPerRevolution;
  }

  public double getEncodersCount() {
    // return (leftEncoder.get() + leftEncoder.get()) / 2;
    return (leftEncoder.get() + rightEncoder.get() / 2);
  }

  // public double average(double... args) {
  // int sum = 0;
  // for (int i : args)
  // sum += i;

  // return sum / args.length;
  // }

  public double getLeftEncoderDistance() {
    return leftEncoder.getDistance();
  }

  public double getRightEncoderDistance() {
    return rightEncoder.getDistance();
  }

  // returns speed of drivetrain in cm/s
  public double getSpeed() {
    return (leftEncoder.getRate() + rightEncoder.getRate()) / 2;
  }

  // @returns average of encoder distances
  public double getEncodersDistance() {
    // return (getLeftEncoderDistance() + getLeftEncoderDistance()) / 2;
    return (getLeftEncoderDistance() + getRightEncoderDistance() / 2);
  }

  // SHIFTER
  public void shifterForward() {
    shifter.set(Value.kForward);
    GearShiftState.set(GearShiftState.HIGH);
  }

  public void shifterBackward() {
    shifter.set(Value.kReverse);
    GearShiftState.set(GearShiftState.LOW);
  }

  public GearShiftState getShifterValue() {
    return GearShiftState.get();
  }

  // FACE
  public void faceForwards() {
    leftSide.setInverted(false);
    rightSide.setInverted(false);
    DirectionState.set(DirectionState.FORWARD);
  }

  public void faceBackwards() {
    leftSide.setInverted(true);
    rightSide.setInverted(true);
    DirectionState.set(DirectionState.BACKWARD);
  }

  public boolean getLeftDirection() {
    return leftSide.getInverted();
  }

  public boolean getRightDirection() {
    return rightSide.getInverted();
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new TankDriveWithXbox());
  }

}