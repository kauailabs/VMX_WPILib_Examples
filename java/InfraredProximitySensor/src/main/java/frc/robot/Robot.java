package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a sample program demonstrating how to find the value of an Infrared Proximity Sensor
 */

public class Robot extends TimedRobot {

  private final AnalogInput m_infrared = new AnalogInput(0);

  //These min and max values should be tuned for every sensor. Can be changed to refine the distance precision.
  private final double minValue = 1.8;
  private final double maxValue = 2.8;

  @Override
  public void teleopPeriodic() {

    //Get the voltage value from the sensor
    double voltage = m_infrared.getAverageVoltage();
    boolean proximity = false;
    
    //Find if the sensor voltage is within the given range
    if (voltage <= maxValue && voltage >= minValue) {
      proximity = true;
    }

    SmartDashboard.putNumber("Infrared Voltage", voltage);
    SmartDashboard.putBoolean("Proximity Alert", proximity);

  }
}
