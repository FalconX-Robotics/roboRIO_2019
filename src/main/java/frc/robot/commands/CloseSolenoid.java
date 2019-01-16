package main.java.frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class CloseSolenoid extends InstantCommand {
   private DoubleSolenoid Solenoid;

   public CloseSolenoid(DoubleSolenoid Solenoid) {
        //requires(HatchPanelGrabber);
       super("CloseSolenoid");

       this.Solenoid = Solenoid;
   }

   @Override
   protected void initialize() {
       Solenoid.set(DoubleSolenoid.Value.kReverse);
   }
}
