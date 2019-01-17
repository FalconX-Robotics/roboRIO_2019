package main.java.frc.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;
import edu.wpi.first.wpilibj.command;

public class ToggleHatchGrabSolenoid extends Command {
  private DoubleSolenoid.Value toggleType;
   public ToggleHatchGrabSolenoid() {
      super("HatchGrabSolenoid");
      this.toggleType = toggleType;
  }

  @Override
  protected void execute() {
      Robot.HatchPanelGrabber.toggleHatchGrabSolenoid(toggleType);
  }

  @Override
  protected boolean isFinished() {
      return true;
  }

}
