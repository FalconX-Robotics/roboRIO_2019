package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class ToggleHatch extends CommandGroup {
    private boolean solenoidState = false;

    public ToggleHatch() {
        Value currentGrabSolenoidState = Robot.hatchPanelGrabber.getHatchGrabSolenoidValue();

        if (currentGrabSolenoidState == Value.kForward) {
            solenoidState = true;
        }
        
        if (solenoidState) {
            addSequential(new OpenHatch());
            solenoidState = false;
        }
         else 
        {
            addSequential(new CloseHatch());        
        }  
    }
}