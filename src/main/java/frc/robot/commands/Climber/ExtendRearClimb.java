package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ExtendRearClimb extends Command {
  public ExtendRearClimb() {
    super("Extend Rear Climb");
    requires(Robot.climber);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.climber.setBackSolenoid(true, false);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
