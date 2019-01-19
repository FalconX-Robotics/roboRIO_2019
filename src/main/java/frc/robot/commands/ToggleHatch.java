package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ToggleHatch extends CommandGroup {
    private static boolean solenoidOpen = false;

    public ToggleHatch() {
        solenoidOpen = !solenoidOpen; //Switch boolean

        if (solenoidOpen) {
            addSequential(new OpenHatch());
        }
         else 
        {
            addSequential(new CloseHatch());        
        }  
    }
}