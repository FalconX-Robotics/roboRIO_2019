package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class HatchPanelGrabber extends Subsystem {
    private DoubleSolenoid hatchGrabSolenoid = new DoubleSolenoid(RobotMap.HATCH_GRAB_FORWARD,
            RobotMap.HATCH_GRAB_REVERSE); // Middle piston
    private Solenoid hatchPushSolenoid = new Solenoid(RobotMap.HATCH_PUSH); // Outside pistons

    public HatchPanelGrabber() {
        super("Hatch Panel Grabber");
        hatchGrabSolenoid.set(Value.kReverse);
        hatchPushSolenoid.set(false);
    }

    public enum hatchPanelState {
        OPENED, LAUNCHING, CLOSED, INVALID;

        private static hatchPanelState currentState = CLOSED;

        public static hatchPanelState checkState() {
            hatchPanelState state = INVALID;

            // check if both is close
            // check if both is open
            // check if push is open, grab is close
            // check if push is close, grab is open
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

            SmartDashboard.putString("Hatch Panel State", state.toString());

            return state;
        }

        public static hatchPanelState get() {
            return currentState;
        }
    }

    public void toggleHatchGrabSolenoid(Value value) {
        if (value == Value.kForward) {
            SmartDashboard.putBoolean("Grab Soleniod", true);
        } else {
            SmartDashboard.putBoolean("Grab Soleniod", false);
        }
        hatchGrabSolenoid.set(value);
    }

    public void toggleHatchPushSolenoid(Boolean value) {
        if (value == true) {
            SmartDashboard.putBoolean("Push Solenoid", true);
        } else {
            SmartDashboard.putBoolean("Push Solenoid", false);
        }
        hatchPushSolenoid.set(false);
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
