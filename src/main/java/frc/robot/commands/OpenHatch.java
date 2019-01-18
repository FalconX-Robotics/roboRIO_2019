package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class OpenHatch extends CommandGroup {
 private static DoubleSolenoid.Value out = DoubleSolenoid.Value.kForward;
 private static DoubleSolenoid.Value in = DoubleSolenoid.Value.kReverse;

 public OpenHatch() {
     //Grab solenoid in
     addSequential(new ToggleGrabber(in));
     //Push solenoid in
     addSequential(new TogglePusher(in));
 }

}

