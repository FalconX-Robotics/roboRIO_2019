package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ExtendFrontClimb extends Command {
  public ExtendFrontClimb() {
    super("Extend Front Climb");
    requires(Robot.climber);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.climber.setFrontSolenoid(true, false);
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
