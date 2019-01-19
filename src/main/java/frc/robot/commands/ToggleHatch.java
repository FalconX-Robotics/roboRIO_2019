package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ToggleHatch extends CommandGroup {
    private static boolean solenoidState = false;

    public ToggleHatch() {
        solenoidState = !solenoidState;

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