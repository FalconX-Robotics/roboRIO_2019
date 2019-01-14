package main.java.frc.robot.commands;

import org.usfirst.frc.team6662.robot.subsystems.Drivetrain.Gear;
import org.usfirst.frc.team6662.robot.Robot;

public class ShiftGear extends Command {
    private Gear targetGear = Gear.LOW;

    public ShiftGear(Gear targetGear) {
        super("Shift to " + targetGear.toString() + " gear");
        requires(Robot.drivetrain);

        this.targetGear = targetGear;
    }

    @Override
    protected void execute() {
        Robot.driveTrain.shiftGear(targetGear);

        SmartDashboard.putString("Gear", Robot.drivetrain.getShiftStateName());
    }

    @Override
    protected boolean isFinished() {
        return true;
    }


}