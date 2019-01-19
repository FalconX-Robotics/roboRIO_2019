package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class CloseHatch extends CommandGroup {
 private static DoubleSolenoid.Value out = DoubleSolenoid.Value.kForward;
 private static DoubleSolenoid.Value in = DoubleSolenoid.Value.kReverse;
 private static DoubleSolenoid.Value off = DoubleSolenoid.Value.kOff;

 public CloseHatch() {
    requires(Robot.hatchPanelGrabber);
    //Push solenoid in
     addSequential(new ToggleHatchPushSolenoid(in));
     //Grab solenoid out
     addSequential(new ToggleHatchGrabSolenoid(out));
 }

}
