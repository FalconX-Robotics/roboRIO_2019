/*package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Climber extends Subsystem
{
    private DoubleSolenoid frontSolenoid = new DoubleSolenoid(RobotMap.FRONT_FORWARD_CLIMB_SOLENOID, RobotMap.FRONT_REVERSE_CLIMB_SOLENOID);
    private DoubleSolenoid backSolenoid = new DoubleSolenoid(RobotMap.BACK_FORWARD_CLIMB_SOLENOID, RobotMap.BACK_REVERSE_CLIMB_SOLENOID);

    public Climber() {
      super("Climber");
    }

    public void frontLeftForward() {
        frontLeft.set(Value.kForward);
    }

    public void frontLeftReverse() {
        frontLeft.set(Value.kReverse);
    }

    public void frontRightForward() {
        frontRight.set(Value.kForward);
    }

    public void frontRightReverse() {
        frontRight.set(Value.kReverse);
    }

    public void backLeftForward() {
        backLeft.set(Value.kForward);
    }

    public void backLeftReverse() {
        backLeft.set(Value.kReverse);
    }

    public void backRightForward() {
        backRight.set(Value.kForward);
    }

    public void backRighttReverse() {
        backRight.set(Value.kReverse);
    }


  

    @Override
    protected void initDefaultCommand() {
    }

}
*/