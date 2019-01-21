package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ToggleHatch extends Command {
    //private static boolean isOpen = false;

    public ToggleHatch() {
        super("ToggleHatch");
    }

    @Override
    protected void initialize() {
        //isOpen = !isOpen;

        if (Robot.hatchPanelGrabber.getHatchGrabSolenoidValue() == Value.kReverse) {
            Robot.hatchPanelGrabber.toggleHatchPushSolenoid(Value.kReverse); 
            Robot.hatchPanelGrabber.toggleHatchGrabSolenoid(Value.kForward); 
        } else {
            Robot.hatchPanelGrabber.toggleHatchPushSolenoid(Value.kReverse); 
            Robot.hatchPanelGrabber.toggleHatchGrabSolenoid(Value.kReverse); 
        }  
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}