package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LaunchPanel extends CommandGroup {
   private static DoubleSolenoid.Value out = DoubleSolenoid.Value.kForward;
   private static DoubleSolenoid.Value in = DoubleSolenoid.Value.kReverse;

   public LaunchPanel() {
       //Grabber open
       addSequential(new ToggleGrabber(in));
       //Wait 0.5
       addSequential(new WaitCommand(), 0.5);
       //Pusher extend
       addSequential(new ToggleGrabber(out));
       //Wait 0.5
       addSequential(new WaitCommand(), 0.5);
       //Pusher retract
       addSequential(new ToggleGrabber(in));
   }


}
