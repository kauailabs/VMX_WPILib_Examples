#include <frc/TimedRobot.h>
#include <frc/smartdashboard/smartdashboard.h>
#include <frc/util/color.h>

#include "rev/ColorSensorV3.h"
#include "rev/ColorMatch.h"

/**
 * This is a sample program demonstrating how to find a few basic colors and the proximity value of the Rev Color Sensor V3
 * The colors roughly match the 2020 Control Panel colors
 */
class Robot : public frc::TimedRobot {

 public:
  void RobotInit() {
    m_colorMatcher.AddColorMatch(kBlueTarget);
    m_colorMatcher.AddColorMatch(kGreenTarget);
    m_colorMatcher.AddColorMatch(kRedTarget);
    m_colorMatcher.AddColorMatch(kYellowTarget);
  }

    
    //Reports the best color match and the proximity value of the sensor
  void RobotPeriodic() {
    //Finds the closest WPI Color  match
    frc::Color detectedColor = m_colorSensor.GetColor();

    //Compares the detected color to the above provided colors
    double confidence = 0.0;
    frc::Color matchedColor = m_colorMatcher.MatchClosestColor(detectedColor, confidence);

    std::string colorString;
    if (matchedColor == kBlueTarget) {
      colorString = "Blue";
    } else if (matchedColor == kRedTarget) {
      colorString = "Red";
    } else if (matchedColor == kGreenTarget) {
      colorString = "Green";
    } else if (matchedColor == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    //Find the value of the proximity sensor
    bool proximity = false;
    if (m_colorSensor.GetProximity() >= minProx && m_colorSensor.GetProximity() <= maxProx) {
        proximity = true;
    }

    //Report the color and proximity to SmartDashboard
    frc::SmartDashboard::PutString("Color", colorString);
    frc::SmartDashboard::PutBoolean("Color Sensor Proximity", proximity);

  }

  private:
    rev::ColorSensorV3 m_colorSensor{frc::I2C::Port::kOnboard}; //Uses the 4-Pin JST I2C port on the VMX-Pi
    rev::ColorMatch m_colorMatcher;

    //A few color targets, roughly matching the 2020 Control Panel colors
    static constexpr frc::Color kBlueTarget = frc::Color(0.143, 0.427, 0.429);
    static constexpr frc::Color kGreenTarget = frc::Color(0.197, 0.561, 0.240);
    static constexpr frc::Color kRedTarget = frc::Color(0.561, 0.232, 0.114);
    static constexpr frc::Color kYellowTarget = frc::Color(0.361, 0.524, 0.113);

    //Tune this range to define what is considered within proximity
    int minProx = 100;
    int maxProx = 300;
};

#ifndef RUNNING_FRC_TESTS
int main() { return frc::StartRobot<Robot>(); }
#endif