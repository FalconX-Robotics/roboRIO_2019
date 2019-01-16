package main.java.frc.robot.subsystems;

public class HatchPanelGrabber extends Subsystem {
    private DoubleSolenoid hatchGrab = new DoubleSolenoid(HATCH_GRAB_1, HATCH_GRAB_2);
    private DoubleSolenoid hatchPush = new DoubleSolenoid(HATCH_PUSH_1, HATCH_PUSH_2);

    public HatchPanelGrabber() {
        super("Hatch Panel Grabber");
    }
    
    @Override
    protected void initDefaultCommand() {
      setDefaultCommand(new MoveHatchPanelGrabberWithXbox());

    }
}

