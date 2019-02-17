package frc.robot.commands;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Panic extends InstantCommand {

    public Panic() {
        super("Panic");
    }

    @Override
    protected void initialize() {
        Scheduler.getInstance().removeAll(); // Stops all scheduled commands 
    }
}