package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class HatchPanelGrabber extends Subsystem {
    private DoubleSolenoid hatchGrabSolenoid = new DoubleSolenoid(RobotMap.HATCH_GRAB_FORWARD, RobotMap.HATCH_GRAB_REVERSE); // Middle piston
    private DoubleSolenoid hatchPushSolenoid = new DoubleSolenoid(RobotMap.HATCH_PUSH_FORWARD, RobotMap.HATCH_PUSH_REVERSE); // Outside pistons

    public HatchPanelGrabber() {
        super("Hatch Panel Grabber");
    }
    
    public void toggleHatchGrabSolenoid(Value value) {
        hatchGrabSolenoid.set(value);
    }

    public void toggleHatchPushSolenoid(Value value) {
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

