package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.Robot;

public class Cargo extends Subsystem {
    private DoubleSolenoid cargoUpperSolenoid = new DoubleSolenoid(RobotMap.UPPER_PISTON_IN, RobotMap.UPPER_PISTON_OUT);

    private DoubleSolenoid cargoLowerSolenoid = new DoubleSolenoid(RobotMap.LOWER_PISTON_IN, RobotMap.LOWER_PISTON_OUT);

    public Cargo() {
        super("Cargo Push");
        toggleCargoUpperSolenoid(Value.kOff);
        toggleCargoLowerSolenoid(Value.kForward);
    }

    public enum CargoState {
        READY, INVALID;

        private static CargoState currentState = READY;

        public static void setCurrentState(CargoState state) {
            currentState = state;
        }

        public static CargoState checkState() {
            if (Robot.cargo.getCargoLowerSolenoidValue() == Value.kForward) {
                if (Robot.cargo.getCargoUpperSolenoidValue() == Value.kForward) {
                    currentState = INVALID;
                } else {
                    currentState = READY;
                }
            } else if (Robot.cargo.getCargoLowerSolenoidValue() == Value.kReverse) {
                currentState = INVALID;
            }

            return currentState;
        }
    }

    /* 
    // public enum CargoState {
    // OPENED, LAUNCH, TOPOPEN, INCORRECT;

    // private static CargoState currentState = TOPOPEN;

    // public static CargoState checkState() {
    // CargoState state = TOPOPEN;

    // if (Robot.cargo.getCargoUpperSolenoidValue() == Value.kReverse
    // && Robot.cargo.getCargoLowerSolenoidValue() == Value.kForward) {
    // state = TOPOPEN;
    // } else if (Robot.cargo.getCargoUpperSolenoidValue() == Value.kReverse
    // && Robot.cargo.getCargoLowerSolenoidValue() == Value.kReverse) {
    // state = OPENED;
    // } else if (Robot.cargo.getCargoUpperSolenoidValue() == Value.kForward
    // && Robot.cargo.getCargoLowerSolenoidValue() == Value.kReverse) {
    // state = INCORRECT;
    // } else if (Robot.cargo.getCargoUpperSolenoidValue() == Value.kForward
    // && Robot.cargo.getCargoLowerSolenoidValue() == Value.kForward) {
    // state = LAUNCH;
    // }
    // return state;
    // }

    // public static void setState(CargoState state) {
    // currentState = state;
    // }

    // public static CargoState getState() {
    // return currentState;
    // }

    // public static CargoState get() {
    // return currentState;
    // }
    // }
    */

    public void toggleCargoLowerSolenoid(Value value) {
        if (value == Value.kForward) {
            SmartDashboard.putBoolean("PushBottomSolenoid", true);
        } else {
            SmartDashboard.putBoolean("PushBottomSolenoid", false);
        }
        cargoLowerSolenoid.set(Value.kOff);
        cargoLowerSolenoid.set(value);
    }

    public void toggleCargoUpperSolenoid(Value value) {
        if (value == Value.kReverse) {
            SmartDashboard.putBoolean("PushUpperSolenoid", true);
        } else {
            SmartDashboard.putBoolean("PushUpperSolenoid", false);
        }
        cargoUpperSolenoid.set(Value.kOff);
        cargoUpperSolenoid.set(value);
    }

    public Value getCargoLowerSolenoidValue() {
        return cargoLowerSolenoid.get();
    }

    public Value getCargoUpperSolenoidValue() {
        return cargoUpperSolenoid.get();
    }

    @Override
    protected void initDefaultCommand() {

    }
}