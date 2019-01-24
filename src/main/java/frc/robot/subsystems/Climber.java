package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Climber extends Subsystem {

    private DoubleSolenoid frontSolenoid = new DoubleSolenoid(RobotMap.FRONT_FORWARD_CLIMB_SOLENOID,
            RobotMap.FRONT_REVERSE_CLIMB_SOLENOID);
    private DoubleSolenoid backSolenoid = new DoubleSolenoid(RobotMap.BACK_FORWARD_CLIMB_SOLENOID,
            RobotMap.BACK_REVERSE_CLIMB_SOLENOID);
    private WPI_TalonSRX climberMotor = new WPI_TalonSRX(RobotMap.CLIMBER_MOTOR);

    public Climber() {
        super("Climber");
    }

    public void setClimberMotorSpeed(double speed) {
        climberMotor.set(speed);
    }

    public void setFrontSolenoid(Value toValue) {
        frontSolenoid.set(toValue);
    }

    public void setBackSolenoid(Value toValue) {
        backSolenoid.set(toValue);
    }

    public Value getFrontSolenoidValue() {
        return frontSolenoid.get();
    }

    public Value getBackSolenoidValue() {
        return backSolenoid.get();
    }

    @Override
    protected void initDefaultCommand() {

    }

}