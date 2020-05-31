package frc.robot;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * Moves a PWM servo (port 0) back and forth from the minimum and maximum.
 */
public class Robot extends TimedRobot {

    Servo servo;

  @Override
  public void robotInit() {
      servo = new Servo(0);
  }

  @Override
  public void teleopPeriodic() {

    //Once the servo reaches the min or max throw, set the position target to the opposite side.
    if (servo.get() < 0.1) {
        servo.set(1);
    }
    else if (servo.get() > 0.9) {
        servo.set(0);
    }
  }
}
