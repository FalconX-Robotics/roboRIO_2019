package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class ToggleHatchPushSolenoid extends Command {
  private DoubleSolenoid.Value toggleType;
   public ToggleHatchPushSolenoid(DoubleSolenoid.Value toggleType) {
      super("HatchPushSolenoid");
      requires(Robot.hatchPanelGrabber);
      this.toggleType = toggleType;
  }

  @Override
  protected void initialize() {
    setTimeout(0.5);
  }

  @Override
  protected void execute() {
      Robot.hatchPanelGrabber.toggleHatchPushSolenoid(toggleType);
  }

  @Override
  protected boolean isFinished() {
      return isTimedOut();
  }

}
