package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class HatchPanelGrabber extends Subsystem {
    private DoubleSolenoid hatchGrabSolenoid = new DoubleSolenoid(RobotMap.HATCH_GRAB_1, RobotMap.HATCH_GRAB_2);
    private DoubleSolenoid hatchPushSolenoid = new DoubleSolenoid(RobotMap.HATCH_PUSH_1, RobotMap.HATCH_PUSH_2);

    public HatchPanelGrabber() {
        super("Hatch Panel Grabber");
    }
    
    public void toggleHatchGrabSolenoid(DoubleSolenoid.Value value) {
        hatchGrabSolenoid.set(value);
    }

    public void toggleHatchPushSolenoid(DoubleSolenoid.Value value) {
        hatchPushSolenoid.set(value);
    }

    @Override
    protected void initDefaultCommand() {
      

    }
}

