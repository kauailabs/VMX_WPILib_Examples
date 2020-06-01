package frc.robot;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.MedianFilter;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

/**
 * This is a sample program demonstrating how to find a few basic colors and the proximity value of the Rev Color Sensor V3
 * The colors roughly match the 2020 Control Panel colors
 */

public class Robot extends TimedRobot {

  ColorSensorV3 colorSensor = new ColorSensorV3(Port.kOnboard); //Uses the 4-Pin JST I2C port on the VMX-Pi
  ColorMatch colorMatcher = new ColorMatch();
  ColorMatchResult colorResult;

  //A few color targets, roughly matching the 2020 Control Panel colors
  final static Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  final static Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  final static Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  final static Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);


  //Tune this range to define what is considered within proximity
  int minProx = 100;
  int maxProx = 300;

  /**
   * Reports the best color match and the proximity value of the sensor
   */
  @Override
  public void teleopPeriodic() {

    SmartDashboard.putString("Color", getClosestColor());

    SmartDashboard.putBoolean("Color Sensor Proximity", isInProximity());

  }

  public String getClosestColor() {
    //Finds the closest WPI Color object Enum match
    Color detectedColor = colorSensor.getColor();

    //Compares the detected color to the above provided colors
    colorResult = colorMatcher.matchClosestColor(detectedColor);

    //Looks at the reported color target match and provides a String name
    String colorString;
    if (colorResult.color == kBlueTarget) {
        colorString = "Blue";
      } else if (colorResult.color == kRedTarget) {
        colorString = "Red";
      } else if (colorResult.color == kGreenTarget) {
        colorString = "Green";
      } else if (colorResult.color == kYellowTarget) {
        colorString = "Yellow";
      } else {
        colorString = "Unknown";
      }

      return colorString;
  }

  public boolean isInProximity() {
    if (colorSensor.getProximity() >= minProx && colorSensor.getProximity() <= maxProx) {
      return true;
    }
    return false;
  }
}
