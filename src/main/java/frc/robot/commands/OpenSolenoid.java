package main.java.frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class OpenSolenoid extends InstantCommand {
   private DoubleSolenoid Solenoid;

   public OpenSolenoid(DoubleSolenoid Solenoid) {
       //requires(HatchPanelGrabber);
       super("OpenSolenoid");

       this.Solenoid = Solenoid;
   }

   @Override
   protected void initialize() {
       Solenoid.set(DoubleSolenoid.Value.kForward);
   }
}