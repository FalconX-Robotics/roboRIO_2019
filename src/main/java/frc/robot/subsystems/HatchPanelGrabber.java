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
            return currentState;
        }

        public static HatchPanelGrabberState check() {
            HatchPanelGrabberState state = INVALID;

            if (Robot.hatchPanelGrabber.getHatchGrabSolenoidValue() == Value.kForward
                    && Robot.hatchPanelGrabber.getHatchPushSolenoidValue() == false) {
                state = CLOSED;
            } else if (Robot.hatchPanelGrabber.getHatchGrabSolenoidValue() == Value.kReverse
                    && Robot.hatchPanelGrabber.getHatchPushSolenoidValue() == true) {
                state = LAUNCHING;
            } else if (Robot.hatchPanelGrabber.getHatchGrabSolenoidValue() == Value.kReverse
                    && Robot.hatchPanelGrabber.getHatchPushSolenoidValue() == false) {
                state = OPENED;
            }

            currentState = state;

            SmartDashboard.putString("Hatch Panel Grabber State", state.toString());

            return state;
        }
    }

    public void toggleHatchGrabSolenoid(Value value) {
        SmartDashboard.putString("Grab Soleniod", value.toString());
        hatchGrabSolenoid.set(value);
    }

    public void toggleHatchPushSolenoid(Boolean value) {
        SmartDashboard.putBoolean("Push Soleniod", value);
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
