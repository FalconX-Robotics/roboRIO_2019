package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class OpenHatch extends CommandGroup {
 private static DoubleSolenoid.Value out = DoubleSolenoid.Value.kForward;
 private static DoubleSolenoid.Value in = DoubleSolenoid.Value.kReverse;

 public OpenHatch() {
     requires(Robot.hatchPanelGrabber);
     //Push solenoid in
     addSequential(new ToggleHatchPushSolenoid(in));
     //Grab solenoid in
     addSequential(new ToggleHatchGrabSolenoid(in));
 }

}

