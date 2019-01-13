package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {

  private WPI_TalonSRX leftFront = new WPI_TalonSRX(0); //robotMap this later
  private WPI_TalonSRX leftRear = new WPI_TalonSRX(1);
  private SpeedControllerGroup leftSide = new SpeedControllerGroup(leftFront, leftRear);
    
  private WPI_TalonSRX rightFront = new WPI_TalonSRX(2); //robotMap this later
  private WPI_TalonSRX rightRear = new WPI_TalonSRX(3);
  private SpeedControllerGroup rightSide = new SpeedControllerGroup(rightFront, rightRear);

  protected void initDefaultCommand() {
    //setDefaultCommand(new TankDriveWithJava());
  }

}