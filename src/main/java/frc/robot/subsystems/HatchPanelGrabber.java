package main.java.frc.robot.subsystems;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class HatchPanelGrabber extends Subsystem {
    private DoubleSolenoid hatchGrabSolenoid = new DoubleSolenoid(HATCH_GRAB_1, HATCH_GRAB_2);
    private DoubleSolenoid hatchPushSolenoid = new DoubleSolenoid(HATCH_PUSH_1, HATCH_PUSH_2);

    public HatchPanelGrabber() {
        super("Hatch Panel Grabber");
    }
    
    public void toggleHatchGrabSolenoid(double value) {
        hatchGrabSolenoid.set(value);
    }

    public void openHatchPushSolenoid(double value) {
        hatchPushSolenoid.set(value);
    }

    @Override
    protected void initDefaultCommand() {
      

    }
}

