package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Drivetrain.DirectionState;

public class ToggleCameraDirection extends InstantCommand {
  public ToggleCameraDirection() {
    super("Toggle Camera Direction");
  }

  protected void initialize() {
    if(Robot.drivetrain.getCameraDirection() == DirectionState.FORWARD){
      Drivetrain.setCameraDirection(DirectionState.BACKWARD);
    }
    else if(Robot.drivetrain.getCameraDirection() == DirectionState.BACKWARD){
      Drivetrain.setCameraDirection(DirectionState.FORWARD);
    }
  }
}
