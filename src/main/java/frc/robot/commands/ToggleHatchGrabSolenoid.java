package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class ToggleHatchGrabSolenoid extends Command {
  private DoubleSolenoid.Value toggleType;
   public ToggleHatchGrabSolenoid(DoubleSolenoid.Value toggleType) {
      super("HatchGrabSolenoid");
      requires(Robot.hatchPanelGrabber);
      this.toggleType = toggleType;
  }

  @Override
  protected void initialize() {
    setTimeout(0.5);
  }

  @Override
  protected void execute() {
      Robot.hatchPanelGrabber.toggleHatchGrabSolenoid(toggleType);
  }

  @Override
  protected boolean isFinished() {
      return isTimedOut();

}
}
