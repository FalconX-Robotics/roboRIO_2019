package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class InitializeClimber extends CommandGroup {

    public InitializeClimber() {
        super("InitializeClimber");
        //Open two Solenoids
        addParallel(new ClimberToggleFrontSolenoid(Value.kForward));
        addSequential(new ClimberToggleBackSolenoid(Value.kForward));
        addSequential(new WaitCommand(), 2);

    }
    
}