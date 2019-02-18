package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class HatchPanelGrabber extends Subsystem {
    private DoubleSolenoid hatchGrabSolenoid = new DoubleSolenoid(1, RobotMap.HATCH_GRAB_FORWARD,
            RobotMap.HATCH_GRAB_REVERSE); // Middle piston
    private Solenoid hatchPushSolenoid = new Solenoid(1, RobotMap.HATCH_PUSH); // Outside pistons

    DigitalInput limitSwitch = new DigitalInput(RobotMap.HATCH_LIMIT_SWITCH);
    private WPI_TalonSRX hatchMotor = new WPI_TalonSRX(RobotMap.HATCH_MOTOR);
    Counter counter = new Counter(limitSwitch);


    public HatchPanelGrabber() {
        super("Hatch Panel Grabber");
        hatchGrabSolenoid.set(Value.kReverse);
        hatchPushSolenoid.set(false);
        HatchPanelPositionState.set(HatchPanelPositionState.UP);
    }

    public enum HatchPanelGrabberState {
        OPENED, LAUNCHING, CLOSED, INVALID;

        private static HatchPanelGrabberState currentState = CLOSED;

        public static HatchPanelGrabberState get() {
            SmartDashboard.putString("HatchPanelGrabberState", currentState.toString());
            return currentState;
        }

        public static void set(HatchPanelGrabberState state) {
            currentState = state;
        }

        public static HatchPanelGrabberState update() {
            if (Robot.hatchPanelGrabber.getHatchGrabSolenoidValue() == Value.kForward
                    && Robot.hatchPanelGrabber.getHatchPushSolenoidValue() == false) {
                set(CLOSED);
            } else if (Robot.hatchPanelGrabber.getHatchGrabSolenoidValue() == Value.kReverse
                    && Robot.hatchPanelGrabber.getHatchPushSolenoidValue() == true) {
                set(LAUNCHING);
            } else if (Robot.hatchPanelGrabber.getHatchGrabSolenoidValue() == Value.kReverse
                    && Robot.hatchPanelGrabber.getHatchPushSolenoidValue() == false) {
                set(OPENED);
            } else {
                set(INVALID);
            }

            SmartDashboard.putString("Hatch Panel Grabber State", currentState.toString());

            return currentState;
        }

        public static boolean check(HatchPanelGrabberState state) {
            if (HatchPanelGrabberState.get() == state) {
                return true;
            }
            return false;
        }
    }

    public enum HatchPanelPositionState {
        UP, DOWN;

        public static HatchPanelPositionState currentPositionState = UP;

        public static HatchPanelPositionState get() {
            return currentPositionState;
        }

        public static void set(HatchPanelPositionState state) {
            currentPositionState = state;
        }

        public static boolean check(HatchPanelPositionState state) {
            if (HatchPanelPositionState.get() == state) {
                return true;
            }
            return false;
        }

        public static void update() {
            if (get() == UP) {
                set(DOWN);

            } else if (get() == DOWN) {
                set(UP);
            }
            SmartDashboard.putString("Hatch Position", get().toString());
            
        }
    }

    public void toggleHatchGrabSolenoid(Value value) {
        SmartDashboard.putString("Grab Soleniod", value.toString());
        HatchPanelGrabberState.update();
        hatchGrabSolenoid.set(value);
    }

    public void toggleHatchPushSolenoid(Boolean value) {
        SmartDashboard.putBoolean("Push Soleniod", value);
        HatchPanelGrabberState.update();
        hatchPushSolenoid.set(value);
    }

    public Value getHatchGrabSolenoidValue() {
        return hatchGrabSolenoid.get();
    }

    public boolean getHatchPushSolenoidValue() {
        return hatchPushSolenoid.get();
    }

    public void runHatchMotor(double speed) {
        hatchMotor.set(speed);
    }


    public boolean isSwitchSet() {
        return counter.get() > 0;
    }

    public void initializeCounter() {
        counter.reset();
    }

    @Override
    protected void initDefaultCommand() {

    }
}
