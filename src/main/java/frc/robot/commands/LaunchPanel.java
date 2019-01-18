package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LaunchPanel extends CommandGroup {
   private static DoubleSolenoid.Value out = DoubleSolenoid.Value.kForward;
   private static DoubleSolenoid.Value in = DoubleSolenoid.Value.kReverse;

   public LaunchPanel() {
       //Grab solenoid in
       addSequential(new ToggleHatchGrabSolenoid(in));
       //Wait 0.5
       addSequential(new WaitCommand(), 0.5);
       //Push solenoid out
       addSequential(new ToggleHatchPushSolenoid(out));
       //Wait 0.5
       addSequential(new WaitCommand(), 0.5);
       //Push solenoid In
       addSequential(new ToggleHatchPushSolenoid(in));
   }


}
