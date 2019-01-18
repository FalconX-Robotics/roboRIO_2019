package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class ToggleHatchPushSolenoid extends Command {
  private DoubleSolenoid.Value toggleType;
   public ToggleHatchPushSolenoid(DoubleSolenoid.Value toggleType) {
      super("HatchPushSolenoid");
      this.toggleType = toggleType;
  }

  @Override
  protected void execute() {
      Robot.hatchPanelGrabber.toggleHatchPushSolenoid(toggleType);
  }

  @Override
  protected boolean isFinished() {
      return true;
  }

}
