package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.TankDriveWithXbox;

public class Drivetrain extends Subsystem
{
    private WPI_TalonSRX leftFront = new WPI_TalonSRX(RobotMap.FRONT_LEFT_MOTOR);
    private WPI_TalonSRX leftRear = new WPI_TalonSRX(RobotMap.REAR_LEFT_MOTOR);
    private SpeedControllerGroup leftSide = new SpeedControllerGroup(leftFront, leftRear);

    private WPI_TalonSRX rightFront = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_MOTOR);
    private WPI_TalonSRX rightRear = new WPI_TalonSRX(RobotMap.REAR_RIGHT_MOTOR);
    private SpeedControllerGroup rightSide = new SpeedControllerGroup(rightFront, rightRear);
   

    DifferentialDrive drivetrain = new DifferentialDrive(leftSide, rightSide);

    public enum Gear {
      LOW, HIGH
    }
    
    private Gear shiftState = Gear.LOW;


    public Drivetrain() {
      super("Drivetrain");
      // Rear motor controllers follow front motor controllers
      leftRear.follow(leftFront);
      rightRear.follow(rightFront);

      // Initialize to low gear
		  shiftGear(Gear.LOW);
      SmartDashboard.putString("Gear", getShiftStateName());
      
    }

    public void tankDrive(double leftSpeed, double rightSpeed) 
    {
		drivetrain.tankDrive(leftSpeed, rightSpeed);
    }

    public void shiftGear(Gear targetGear) {
      switch (targetGear) {
        case LOW:
          shifter.set(DoubleSolenoid.Value.kReverse);
          shiftState = Gear.LOW;
          break;
        case HIGH:
          shifter.set(DoubleSolenoid.Value.kForward);
          shiftState = Gear.HIGH;
          break;
      }
    }
    
    public String getShiftStateName() {
      return shiftState.toString();
    }
  

    @Override
    protected void initDefaultCommand() {
      setDefaultCommand(new TankDriveWithXbox());

    }

}