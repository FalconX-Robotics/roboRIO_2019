
package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class RetractFrontClimb extends Command {
  public RetractFrontClimb() {
    super("Retract Front Climb");
    requires(Robot.climber);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.climber.reverseFrontSolenoid();
    SmartDashboard.putBoolean("I ran froht clib", true);
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
