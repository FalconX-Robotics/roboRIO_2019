package main.java.frc.robot.commands;

import org.usfirst.frc.team6662.robot.Robot;
import edu.wpi.first.wpilibj.command;

public class ToggleHatchPushSolenoid extends Command {
  private DoubleSolenoid.Value toggleType;
   public ToggleHatchPushSolenoid() {
      super("HatchPushSolenoid");
      this.toggleType = toggleType;
  }

  @Override
  protected void execute() {
      Robot.HatchPanelGrabber.toggleHatchPushSolenoid(toggleType);
  }

  @Override
  protected boolean isFinished() {
      return true;
  }

}
