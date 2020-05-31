package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.MedianFilter;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a sample program demonstrating how to find a distance using an Ultrasonic Sensor
 */

public class Robot extends TimedRobot {

  // factor to convert sensor values to a distance in inches
  private static final double kValueToInches = 0.125;

  // median filter to discard outliers; filters over 10 samples
  private final MedianFilter m_filter = new MedianFilter(10);

  private final AnalogInput m_ultrasonic = new AnalogInput(0);

  /**
   * Reports the distance found by the sensor
   */
  @Override
  public void teleopPeriodic() {
    // sensor returns a value from 0-4095 that is scaled to inches
    // returned value is filtered with a rolling median filter, since ultrasonics
    // tend to be quite noisy and susceptible to sudden outliers
    double currentDistance = m_filter.calculate(m_ultrasonic.getValue()) * kValueToInches;

    SmartDashboard.putNumber("Ultrasonic Distance", currentDistance);

  }
}
