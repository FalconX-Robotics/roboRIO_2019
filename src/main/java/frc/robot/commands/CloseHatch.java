package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CloseHatch extends CommandGroup {
 private static DoubleSolenoid.Value out = DoubleSolenoid.Value.kForward;
 private static DoubleSolenoid.Value in = DoubleSolenoid.Value.kReverse;


 public CloseHatch() {
     //Push solenoid in
     addSequential(new ToggleHatchPushSolenoid(in));
     //Grab solenoid out
     addSequential(new ToggleHatchGrabSolenoid(out));
 }

}
