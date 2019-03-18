package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;

public class ToggleFrontClimberSolenoid extends InstantCommand {

    public ToggleFrontClimberSolenoid() {
        super("Toggle Front Climber Solenoid");
        requires(Robot.climber);
    }

    @Override
    public void initialize() {
        if (Robot.climber.getFrontSolenoidForwardValue() == true && Robot.climber.getFrontSolenoidReverseValue() == false) {
            Robot.climber.setFrontSolenoid(false, true);
        } else {
            Robot.climber.setFrontSolenoid(true, false);
        }
    }
}