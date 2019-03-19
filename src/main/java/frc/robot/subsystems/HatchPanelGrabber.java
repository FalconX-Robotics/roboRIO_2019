package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class HatchPanelGrabber extends Subsystem {
    private DoubleSolenoid hatchGrabSolenoid = new DoubleSolenoid(RobotMap.FRONT_MODULE,
     RobotMap.HATCH_GRAB_FORWARD, RobotMap.HATCH_GRAB_REVERSE); // Middle piston
    private Solenoid hatchPushSolenoid = new Solenoid(RobotMap.FRONT_MODULE, RobotMap.HATCH_PUSH); // Outside pistons

    DigitalInput limitSwitchTop = new DigitalInput(RobotMap.TOP_LIMIT_SWITCH);
    DigitalInput limitSwitchBottom = new DigitalInput(RobotMap.BOTTOM_LIMIT_SWITCH);
    private WPI_TalonSRX hatchMotor = new WPI_TalonSRX(RobotMap.HATCH_MOTOR);
    // Counter counterTop = new Counter(limitSwitchTop);
    // Counter counterBottom = new Counter(limitSwitchBottom);

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
        UP, DOWN, IN_BETWEEN;

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

        public static HatchPanelPositionState update() {
            if (!Robot.hatchPanelGrabber.getTopSwitch()) {
                currentPositionState = UP;
            } else if (!Robot.hatchPanelGrabber.getBottomSwitch()) {
                currentPositionState = DOWN;
            } else {
                currentPositionState = IN_BETWEEN;
            }
            return currentPositionState;
        }
    }

    public void toggleHatchGrabSolenoid(Value value) {
        SmartDashboard.putString("Grab Soleniod", value.toString());
        hatchGrabSolenoid.set(value);
        HatchPanelGrabberState.update();
    }

    public void toggleHatchPushSolenoid(Boolean value) {
        SmartDashboard.putBoolean("Push Soleniod", value);
        hatchPushSolenoid.set(value);
        HatchPanelGrabberState.update();
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

    public boolean getTopSwitch() {
        SmartDashboard.putBoolean("Bottom Switch", limitSwitchTop.get());
        return limitSwitchTop.get();
    }
  
    public boolean getBottomSwitch() {
        SmartDashboard.putBoolean("Bottom Switch", limitSwitchBottom.get());
        return limitSwitchBottom.get();
    }

    @Override
    protected void initDefaultCommand() {
    }
}
