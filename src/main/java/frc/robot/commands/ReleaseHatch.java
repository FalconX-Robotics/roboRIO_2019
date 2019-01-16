package main.java.frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CloseHatch extends CommandGroup {

  public CloseHatch(DoubleSolenoid solenoid, DoubleSolenoid piston) {
      //Open Solenoid
      addSequential(new OpenSolenoid(Solenoid));
      //Wait (1 second)
      addSequential(new WaitCommand(), HATCH_DELAY);
      //Push Piston
      addSequential(new OpenSolenoid(piston));
      //Wait (1 second)
      addSequential(new WaitCommand(), HATCH_DELAY);
      //Back piston
      addSequential(new CloseSolenoid(piston));
      //Close Solenoid
      addSequential(new CloseSolenoid(solenoid));
  }

}
