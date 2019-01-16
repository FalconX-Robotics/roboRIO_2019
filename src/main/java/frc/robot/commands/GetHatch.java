package main.java.frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class OpenHatch extends CommandGroup {

   public OpenHatch(DoubleSolenoid solenoid, DoubleSolenoid piston) {
       //Close the piston just in case
       addSequential(new OpenSolenoid(piston));
       //Open solenoid
       addSequential(new OpenSolenoid(solenoid));
   }

}
