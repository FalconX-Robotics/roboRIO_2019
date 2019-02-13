package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.Robot;

public class Cargo extends Subsystem {
    private DoubleSolenoid cargoUpperSolenoid = new DoubleSolenoid(RobotMap.CARGO_UPPER_PISTON_IN, RobotMap.CARGO_UPPER_PISTON_OUT);

    private DoubleSolenoid cargoLowerSolenoid = new DoubleSolenoid(RobotMap.CARGO_LOWER_PISTON_IN, RobotMap.CARGO_LOWER_PISTON_OUT);

    public Cargo() {
        super("Cargo Push");
        toggleCargoUpperSolenoid(Value.kOff);
        toggleCargoLowerSolenoid(Value.kForward);
    }

    public enum CargoState {
        READY, INVALID;

        private static CargoState currentState = READY;

        public static void set(CargoState state) {
            currentState = state;
        }

        public static CargoState get() {
            return currentState;
        }

        public static CargoState update() {
            if (Robot.cargo.getCargoLowerSolenoidValue() == Value.kForward) {
                if (Robot.cargo.getCargoUpperSolenoidValue() == Value.kForward) {
                    set(INVALID);
                } else {
                    set(READY);
                }
            } else if (Robot.cargo.getCargoLowerSolenoidValue() == Value.kReverse) {
                set(INVALID);
            }

            SmartDashboard.putString("Cargo State", currentState.toString());

            return currentState;
        }

        public static boolean check(CargoState state) {
            if (CargoState.get() == state) {
                return true;
            }
            return false;
        }
    }

    public void toggleCargoLowerSolenoid(Value value) {
        SmartDashboard.putString("PushLowerSolenoid", value.toString());
        CargoState.update();
        cargoLowerSolenoid.set(value);
    }

    public void toggleCargoUpperSolenoid(Value value) {
        SmartDashboard.putString("PushUpperSolenoid", value.toString());
        CargoState.update();
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