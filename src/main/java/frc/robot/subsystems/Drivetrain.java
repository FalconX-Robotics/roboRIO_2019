package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
//import frc.robot.commands.ShiftGear;
import frc.robot.commands.TankDriveWithXbox;

public class Drivetrain extends Subsystem
{
    private WPI_TalonSRX leftFront = new WPI_TalonSRX(RobotMap.FRONT_LEFT_MOTOR);
    private WPI_TalonSRX leftRear = new WPI_TalonSRX(RobotMap.REAR_LEFT_MOTOR);
    private SpeedControllerGroup leftSide = new SpeedControllerGroup(leftFront, leftRear);

    private WPI_TalonSRX rightFront = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_MOTOR);
    private WPI_TalonSRX rightRear = new WPI_TalonSRX(RobotMap.REAR_RIGHT_MOTOR);
    private SpeedControllerGroup rightSide = new SpeedControllerGroup(rightFront, rightRear);
   
    //private Compressor compressor = new Compressor(RobotMap.COMPRESSOR);
    DifferentialDrive drivetrain = new DifferentialDrive(leftSide, rightSide);

    //private DoubleSolenoid shifter = new DoubleSolenoid(RobotMap.SHIFTER_FORWARD, RobotMap.SHIFTER_REVERSE);

    public Drivetrain() {
      super("Drivetrain");
      // Rear motor controllers follow front motor controllers
      leftRear.follow(leftFront);
      rightRear.follow(rightFront);
      //shifterBackward();
    }

    public void tankDrive(double leftSpeed, double rightSpeed) 
    {
		drivetrain.tankDrive(leftSpeed, rightSpeed);
    }

    public void shifterForward()
    {
        shifter.set(Value.kForward);
    }

    public void shifterBackward() 
    {
        shifter.set(Value.kReverse);
    }
    
  

    @Override
    protected void initDefaultCommand() {
      setDefaultCommand(new TankDriveWithXbox());

    }

}