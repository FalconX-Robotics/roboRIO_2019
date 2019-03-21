package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Climber.DriveClimberMotor;
// import frc.robot.commands.Climber.HoldSolenoid;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;

public class Climber extends Subsystem {

    private Solenoid frontSolenoidForward = new Solenoid(RobotMap.FRONT_MODULE, RobotMap.FRONT_FORWARD_CLIMB_SOLENOID);
    private Solenoid frontSolenoidReverse = new Solenoid(RobotMap.FRONT_MODULE, RobotMap.FRONT_REVERSE_CLIMB_SOLENOID);

    private Solenoid backSolenoidForward = new Solenoid(RobotMap.REAR_MODULE, RobotMap.BACK_FORWARD_CLIMB_SOLENOID);
    private Solenoid backSolenoidReverse = new Solenoid(RobotMap.REAR_MODULE, RobotMap.BACK_REVERSE_CLIMB_SOLENOID);

    private WPI_TalonSRX climberMotor = new WPI_TalonSRX(RobotMap.CLIMBER_MOTOR);

    public Climber() {
        super("Climber");

        climberMotor.setInverted(true);
    }

    public void setClimberMotorSpeed(double speed) {
        climberMotor.set(speed * 0.6);
    }

    public void setFrontSolenoid(Boolean forwardValue, Boolean reverseValue) {
        frontSolenoidForward.set(forwardValue);
        frontSolenoidReverse.set(reverseValue);
    }

    public void setBackSolenoid(Boolean forwardValue, Boolean reverseValue) {
        backSolenoidForward.set(forwardValue);
        backSolenoidReverse.set(reverseValue);
    }

    public boolean getFrontSolenoidForwardValue() {
        return frontSolenoidForward.get();
    }

    public boolean getFrontSolenoidReverseValue() {
        return frontSolenoidReverse.get();
    }

    public boolean getBackSolenoidForwardValue() {
        return backSolenoidForward.get();
    }

    public boolean getBackSolenoidReverseValue() {
        return backSolenoidReverse.get();
    }

    public void forwardFrontSolenoid() {
        frontSolenoidForward.set(true);
        frontSolenoidReverse.set(false);
    }

    public void reverseFrontSolenoid() {
        frontSolenoidForward.set(false);
        frontSolenoidReverse.set(true);
    }

    public void pauseFrontSolenoid() {
        frontSolenoidForward.set(false);
        frontSolenoidReverse.set(false);
    }

    public void forwardBackSolenoid() {
        backSolenoidForward.set(true);
        backSolenoidReverse.set(false);
    }

    public void reverseBackSolenoid() {
        backSolenoidForward.set(false);
        backSolenoidReverse.set(true);
    }

    public void pauseBackSolenoid() {
        backSolenoidForward.set(false);
        backSolenoidReverse.set(false);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveClimberMotor());
    }
}