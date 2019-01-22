package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class HatchPanelGrabber extends Subsystem {
    private DoubleSolenoid hatchGrabSolenoid = new DoubleSolenoid(RobotMap.HATCH_GRAB_FORWARD,
            RobotMap.HATCH_GRAB_REVERSE); // Middle piston
    private DoubleSolenoid hatchPushSolenoid = new DoubleSolenoid(RobotMap.HATCH_PUSH_FORWARD,
            RobotMap.HATCH_PUSH_REVERSE); // Outside pistons

    enum HatchPanelStatus {

    }

    public HatchPanelGrabber() {
        super("Hatch Panel Grabber");
        hatchGrabSolenoid.set(Value.kOff);
        hatchPushSolenoid.set(Value.kOff);
    }

    public void toggleHatchGrabSolenoid(Value value) {
        if (value == Value.kForward) {
            SmartDashboard.putBoolean("GrabSoleniod", true);
        } else {
            SmartDashboard.putBoolean("GrabSoleniod", false);
        }
        hatchGrabSolenoid.set(Value.kOff);
        hatchGrabSolenoid.set(value);
    }

    public void toggleHatchPushSolenoid(Value value) {
        if (value == Value.kForward) {
            SmartDashboard.putBoolean("PushSolenoid", true);
        } else {
            SmartDashboard.putBoolean("PushSolenoid", false);
        }
        hatchGrabSolenoid.set(Value.kOff);
        hatchPushSolenoid.set(value);
    }

    public Value getHatchGrabSolenoidValue() {
        return hatchGrabSolenoid.get();
    }

    public Value getHatchPushSolenoidValue() {
        return hatchPushSolenoid.get();
    }

    @Override
    protected void initDefaultCommand() {

    }
}
