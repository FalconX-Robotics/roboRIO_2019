package frc.robot.subsystems;

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

    public HatchPanelGrabber() {
        super("Hatch Panel Grabber");
        hatchGrabSolenoid.set(Value.kReverse);
        hatchPushSolenoid.set(false);
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

    @Override
    protected void initDefaultCommand() {

    }
}
