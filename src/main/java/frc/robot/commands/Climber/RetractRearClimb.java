package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class RetractRearClimb extends Command {
  public RetractRearClimb() {
    super("Retract Rear Climb");
    requires(Robot.climber);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.climber.reverseBackSolenoid();
    SmartDashboard.putBoolean("I ran retract rear clib", true);
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