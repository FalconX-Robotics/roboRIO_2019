package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CloseHatch extends CommandGroup {

 private static Value out = Value.kForward;
 private static Value in = Value.kReverse;

 public CloseHatch() {
     //retract pushers
     addSequential(new TogglePusher(in));
     //close grabbers
     addSequential(new ToggleGrabber(out));
 }

}

