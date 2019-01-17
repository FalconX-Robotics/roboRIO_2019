package main.java.frc.robot.commands;

public class ToggleHatch extends InstantCommand {

    public ToggleHatch() {
        super("ToggleHatch");
    }

    public void execute() {
        boolean currentGrabSolenoidState = Robot.hatchPanelGrabber.getHatchGrabSolenoidValue();
        if (currentGrabSolenoidState) {
            OpenHatch();
        }
         else 
        {
            CloseHatch();        
        }        
    }
}