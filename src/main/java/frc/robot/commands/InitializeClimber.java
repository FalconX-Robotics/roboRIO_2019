package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.Climber.ClimberState;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class InitializeClimber extends CommandGroup {

    public InitializeClimber() {
        super("InitializeClimber");
        ClimberState.set(ClimberState.INITIALIZED);
        // Open two Solenoids
        addSequential(new ClimberToggleFrontSolenoid(Value.kForward));
        addSequential(new ClimberToggleBackSolenoid(Value.kForward));
        addSequential(new WaitCommand(), 2);
    }

}