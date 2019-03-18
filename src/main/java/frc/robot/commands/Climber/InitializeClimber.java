package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class InitializeClimber extends Command {
    // threshold to turn off solenoid in degrees (can change)
    public final double OFF_ANGLE_THRESHOLD = 15;
    // threshold to turn both solenoids back on in degrees (can change)
    public final double ON_ANGLE_THRESHOLD = 0;

    private enum ClimberUpState {
        BALANCED,
        FRONT_LOW,
        BACK_LOW;
        
        /*public static ClimberUpState currentState = BALANCED;

        public static void set(ClimberUpState state) {
            currentState = state;
        }

        public static boolean check(ClimberUpState state) {
            if (state == currentState) {
                return true;
            } else {
                return false;
            }
            
        }*/
    }

    private ClimberUpState currentState = ClimberUpState.BALANCED;

    private void setState(ClimberUpState state) {
        currentState = state;
    }

    public boolean checkState(ClimberUpState state) {
        return state == currentState;
    }

    public InitializeClimber() {
        super("Initialize Climber");
        requires(Robot.climber);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        double pitch = Robot.drivetrain.getTilt();
        
        if (pitch <= -OFF_ANGLE_THRESHOLD) {
            setState(ClimberUpState.FRONT_LOW);

        } else if (pitch >= OFF_ANGLE_THRESHOLD) {
            setState(ClimberUpState.BACK_LOW);

        } else if (checkState(ClimberUpState.FRONT_LOW)) {
            if (pitch > ON_ANGLE_THRESHOLD) {
                setState(ClimberUpState.BALANCED);
            }

        } else if (checkState(ClimberUpState.BACK_LOW)) {
            if (pitch < ON_ANGLE_THRESHOLD) {
                setState(ClimberUpState.BALANCED);
            }
        } else {
            setState(ClimberUpState.BALANCED);
        }

        SmartDashboard.putString("ClimberUpState", currentState.toString());
        if (checkState(ClimberUpState.BALANCED)) {
            Robot.climber.forwardFrontSolenoid();
            Robot.climber.forwardBackSolenoid();
        } else if (checkState(ClimberUpState.FRONT_LOW)) {
            Robot.climber.forwardFrontSolenoid();
            Robot.climber.pauseBackSolenoid();
        } else if (checkState(ClimberUpState.BACK_LOW)) {
            Robot.climber.pauseFrontSolenoid();
            Robot.climber.forwardBackSolenoid();
        }
    }

    @Override
    public synchronized boolean isInterruptible() {
        return true;
    }
    
    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
        SmartDashboard.putString("Climber Status", "This didn't work");
        end();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}