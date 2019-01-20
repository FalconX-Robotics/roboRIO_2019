package frc.robot.commands;

import java.util.concurrent.TimeUnit;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LaunchPanel extends Command {
   private static DoubleSolenoid.Value out = DoubleSolenoid.Value.kForward;
   private static DoubleSolenoid.Value in = DoubleSolenoid.Value.kReverse;
   private boolean finished = false;

   public LaunchPanel() {
       super("LaunchPanelYourMom");
       //Grab solenoid in
       //addSequential(new ToggleHatchGrabSolenoid(in));
       //Wait 0.5
       //addSequential(new WaitCommand(), 0.5);
       //Push solenoid out
       //addSequential(new ToggleHatchPushSolenoid(out));
       //Wait 0.5
       //addSequential(new WaitCommand(), 0.5);
       //Push solenoid In
       //addSequential(new ToggleHatchPushSolenoid(in));
   }

   @Override
   protected void initialize() {
        try {
            Robot.hatchPanelGrabber.toggleHatchGrabSolenoid(out);
            TimeUnit.MILLISECONDS.sleep(300);
            Robot.hatchPanelGrabber.toggleHatchPushSolenoid(out);
            TimeUnit.MILLISECONDS.sleep(100);
            Robot.hatchPanelGrabber.toggleHatchPushSolenoid(in);
        } catch (InterruptedException e) {

        }
        finished = true;
   }

    @Override
    protected boolean isFinished() {
        return finished;
    }


}
