package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class OpenHatch extends CommandGroup {
    
 private static DoubleSolenoid.Value in = DoubleSolenoid.Value.kReverse;

 public OpenHatch() {
     //Open Grabber
     addSequential(new ToggleGrabber(in));
     //Retract Pushers
     addSequential(new TogglePusher(in));
 }

}

