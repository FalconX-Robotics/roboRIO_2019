package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;

public class SetBackClimberSolenoid extends InstantCommand {
    private Value state;

    public SetBackClimberSolenoid(Value state) {
        super("SetBackClimberSolenoid");
        this.state = state;
    }

    @Override
    public void initialize() {
        Robot.climber.setBackSolenoid(state);
    }
}