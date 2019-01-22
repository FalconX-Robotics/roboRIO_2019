package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class ToggleGear extends Command {

    public ToggleGear() {
        super("ToggleGear");
        // super(Robot.drivetrain.getShifterValue() == Value.kForward ? Value.kReverse :
        // Value.kForward);
    }

    @Override
    public void initialize() {
        SmartDashboard.putString("lma", "memes");
        if (Robot.drivetrain.getShifterValue() == Value.kReverse) {
            Robot.drivetrain.shifterForward();
        } else {
            Robot.drivetrain.shifterBackward();
        }
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}