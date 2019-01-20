package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class HatchPanelGrabber extends Subsystem {
    //solenoid that controls the center cylinder
    private DoubleSolenoid hatchGrabber = new DoubleSolenoid(RobotMap.HATCH_GRAB_MODULE_NUM, RobotMap.HATCH_GRAB_IN, RobotMap.HATCH_GRAB_OUT);
    //the two outer solenoids that push the panel
    private DoubleSolenoid hatchPusher = new DoubleSolenoid(RobotMap.HATCH_PUSH_MODULE_NUM, RobotMap.HATCH_PUSH_IN, RobotMap.HATCH_PUSH_OUT);

    public HatchPanelGrabber() {
        super("Hatch Panel Grabber");
        
    }
    
    public void toggleGrabber(Value value) {
        hatchGrabber.set(value);
    }

    public void togglePusher(Value value) {
        hatchPusher.set(value);
    }

    public Value getGrabberState(){
        return hatchGrabber.get();
    }

    public Value getPusherState(){
        return hatchPusher.get();
    }

    @Override
    protected void initDefaultCommand() {
      //set useful default for later
    }
}

