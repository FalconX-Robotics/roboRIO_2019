package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ToggleHatch extends CommandGroup {
    private static boolean solenoidOpen = false;

    public ToggleHatch() {
        solenoidOpen = !solenoidOpen; //Switch boolean

        if (solenoidOpen) {
            SmartDashboard.putBoolean("ToggleHatch", solenoidOpen);
            addParallel(new OpenHatch());
            return;
        }
         else 
        {
            SmartDashboard.putBoolean("ToggleHatch", solenoidOpen);
            addParallel(new CloseHatch());
            return;    
        }  
    }
}