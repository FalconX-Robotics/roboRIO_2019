package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.commands.TankDriveWithXbox;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEntry;

public class Drivetrain extends Subsystem {

  private WPI_TalonSRX leftFront = new WPI_TalonSRX(RobotMap.FRONT_LEFT_MOTOR);
  private WPI_TalonSRX leftRear = new WPI_TalonSRX(RobotMap.REAR_LEFT_MOTOR);
  private SpeedControllerGroup leftSide = new SpeedControllerGroup(leftFront, leftRear);

  private WPI_TalonSRX rightFront = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_MOTOR);
  private WPI_TalonSRX rightRear = new WPI_TalonSRX(RobotMap.REAR_RIGHT_MOTOR);
  private SpeedControllerGroup rightSide = new SpeedControllerGroup(rightFront, rightRear);

  private DifferentialDrive drivetrain = new DifferentialDrive(leftSide, rightSide);

  private AnalogGyro gyro = new AnalogGyro(0);

  private DoubleSolenoid shifter = new DoubleSolenoid(RobotMap.SHIFTER_FORWARD, RobotMap.SHIFTER_REVERSE);

  private static NetworkTable obiWan;
  private static NetworkTableEntry directionStateEntry;

  private static DirectionState cameraDirection;

  private static final double DISTANCE_PER_COUNT = (5.08 / 4096 * Math.PI);

  public Drivetrain() {
    super("Drivetrain");
    SmartDashboard.putString("Drivetrain", "enabled");
    // INITIALIZE
    obiWan = NetworkTableInstance.getDefault().getTable("ObiWan");
    directionStateEntry = obiWan.getEntry("DirectionState");
    leftRear.follow(leftFront);
    rightRear.follow(rightFront);
    shifterBackward();
    //faceForwards();

    // ENCODERS
    leftFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    rightFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    //GYRO
    gyro.calibrate();
  }

  public double getGyroAngle() {
     return gyro.getAngle() % 360;
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    drivetrain.tankDrive(leftSpeed, rightSpeed, true);
  }

  public void setLeftSide(double speed) {
    leftSide.set(speed);
  }

  public void setRightSide(double speed) {
    rightSide.set(speed);
  }

  // ENCODERS
  public void resetEncoders() {
    leftFront.setSelectedSensorPosition(0);
    rightFront.setSelectedSensorPosition(0);
  }

  public double findDistancePerPulse(double countsPerRevolution) {
    return (Math.PI * RobotMap.WHEEL_DIAMETER) / countsPerRevolution;
  }

  //Raw Encoder counts
  public int getEncodersCount() {
    return (getLeftEncoderCount() + getRightEncoderCount()) / 2;
  }

  public int getLeftEncoderCount() {
    return leftFront.getSelectedSensorPosition(); 
  }

  public int getRightEncoderCount() {
    return rightFront.getSelectedSensorPosition();
  }
  
  //Encoder distances in cm
  public double getLeftEncoderDistance() {
    return getLeftEncoderCount() * DISTANCE_PER_COUNT;
  }

  public double getRightEncoderDistance() {
    return getRightEncoderCount() * DISTANCE_PER_COUNT;
  }

  public double getEncoderDistance(){
    return (getLeftEncoderDistance() + getRightEncoderDistance()) / 2;
  }

  //Encoder speeds in cm/s
  public double getLeftEncoderSpeed(){
    return leftFront.getSelectedSensorVelocity();
  }

  public double getRightEncoderSpeed(){
    return rightFront.getSelectedSensorVelocity() * 10;
  }

  public double getSpeed() {
    return (getLeftEncoderSpeed() + getRightEncoderSpeed()) / 2;
  }

  // SHIFTER
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

    public static GearShiftState update() {
      if (Robot.drivetrain.getShifterValue() == Value.kForward) {
        set(HIGH);
      }
      else {
        set(LOW);
      }

      return currentState;
    }

    public static boolean check(GearShiftState state) {
      if (GearShiftState.get() == state) {
        return true;
      }
      return false;
    }

  }

  public void shifterForward() {
    shifter.set(Value.kForward);
    GearShiftState.set(GearShiftState.HIGH);
  }

  public void shifterBackward() {
    shifter.set(Value.kReverse);
    GearShiftState.set(GearShiftState.LOW);
  }

  public Value getShifterValue() {
    return shifter.get();
  }

  // FACE
  public enum DirectionState {
    FORWARD, BACKWARD, INVALID;

    private static DirectionState currentState = DirectionState.FORWARD;

    public static DirectionState get() {
      return currentState;
    }

    public static void set(DirectionState state) {
      setCameraDirection(state);
      directionStateEntry.setString(state.toString());
    }

    public static DirectionState update() {
      DirectionState state;

      if (Robot.drivetrain.getLeftDirection() != Robot.drivetrain.getRightDirection()) {
        state = INVALID;
      } else {
        if (Robot.drivetrain.getLeftDirection() == false) {
          state = BACKWARD;
        } else {
          state = FORWARD;
        }
      }

      set(state);
      return state;
    }

    public static boolean check(DirectionState state) {
      if (DirectionState.get() == state) {
        return true;
      }
      return false;
    }
  }

  public void faceForwards() {
    leftSide.setInverted(true);
    rightSide.setInverted(true);
    DirectionState.update();
  }

  public void faceBackwards() {
    leftSide.setInverted(false);
    rightSide.setInverted(false);
    DirectionState.update();
  }

  public boolean getLeftDirection() {
    return leftSide.getInverted();
  }

  public boolean getRightDirection() {
    return rightSide.getInverted();
  }

  public static void setCameraDirection(DirectionState toCameraDirection){
    cameraDirection = toCameraDirection;
    SmartDashboard.putString("Camera State", cameraDirection.toString());
  }

  public DirectionState getCameraDirection(){
    return cameraDirection;
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new TankDriveWithXbox());
  }

}