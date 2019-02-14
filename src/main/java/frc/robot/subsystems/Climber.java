package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.DriveClimberMotor;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Climber extends Subsystem {

    private DoubleSolenoid frontSolenoid = new DoubleSolenoid(1, RobotMap.FRONT_FORWARD_CLIMB_SOLENOID,
            RobotMap.FRONT_REVERSE_CLIMB_SOLENOID);
    private DoubleSolenoid backSolenoid = new DoubleSolenoid(1, RobotMap.BACK_FORWARD_CLIMB_SOLENOID,
            RobotMap.BACK_REVERSE_CLIMB_SOLENOID);
    private WPI_TalonSRX climberMotor = new WPI_TalonSRX(RobotMap.CLIMBER_MOTOR);

    public Climber() {
        super("Climber");
        setFrontSolenoid(Value.kReverse);
        setBackSolenoid(Value.kReverse);
    }

    public enum ClimberState {
        INITIALIZED, READY, INVALID;

        private static ClimberState currentState = READY;

        public static ClimberState get() {
            return currentState;
        }

        public static void set(ClimberState state) {
            currentState = state;
            SmartDashboard.putString("Climber State", state.toString());
        }

        public static boolean check(ClimberState state) {
            if (ClimberState.get() == state) {
                return true;
            }
            return false;
        }
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
        setDefaultCommand(new DriveClimberMotor());
    }
}