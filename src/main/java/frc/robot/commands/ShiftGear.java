package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ShiftGear extends InstantCommand {
    private static boolean shiftState = false;
    

    public ShiftGear() {
        super("Shift Gear");
        requires(Robot.drivetrain);
    }
    

    public void execute() {
        shiftState = !shiftState;
        
        if (shiftState) {
            Robot.drivetrain.shifterForward();
            
        }   

        else {
            Robot.drivetrain.shifterBackward();
        }
    }
}