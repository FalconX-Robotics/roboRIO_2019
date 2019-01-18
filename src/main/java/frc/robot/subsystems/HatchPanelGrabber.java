package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class HatchPanelGrabber extends Subsystem {
    //solenoid that controls the center cylinder
    private DoubleSolenoid hatchGrabber = new DoubleSolenoid(RobotMap.HATCH_GRABBER_IN, RobotMap.HATCH_GRABBER_OUT);
    //the two outer solenoids that push the panel
    private DoubleSolenoid hatchPusher = new DoubleSolenoid(RobotMap.HATCH_PUSHER_IN, RobotMap.HATCH_PUSHER_OUT);

    public HatchPanelGrabber() {
        super("Hatch Panel Grabber");
    }
    
    public void toggleGrabber(DoubleSolenoid.Value value) {
        hatchGrabber.set(value);
    }

    public void togglePusher(DoubleSolenoid.Value value) {
        hatchPusher.set(value);
    }

    @Override
    protected void initDefaultCommand() {
      

    }
}

