package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;

public class ToggleBackClimberSolenoid extends InstantCommand {

    public ToggleBackClimberSolenoid() {
        super("Toggle Back Climber Solenoid");
        requires(Robot.climber);
    }

    @Override
    public void initialize() {
        if (Robot.climber.getBackSolenoidForwardValue() == true && Robot.climber.getBackSolenoidReverseValue() == false) {
            Robot.climber.setBackSolenoid(false, true);
        } else {
            Robot.climber.setBackSolenoid(true, false);
        }
    }
}