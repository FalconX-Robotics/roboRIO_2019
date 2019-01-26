package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardTest extends Robot {
    public void operatorControl() {
        double counter = 0.0;
        while (isOperatorControl() && isEnabled()) {
            SmartDashboard.putNumber("Counter", counter++);
            Timer.delay(0.10);
        }
    }
}
