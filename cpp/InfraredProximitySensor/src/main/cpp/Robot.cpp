#include <frc/AnalogInput.h>
#include <frc/MedianFilter.h>
#include <frc/TimedRobot.h>
#include <frc/smartdashboard/SmartDashboard.h>

/**
 * This is a sample program demonstrating how to find the value of an Infrared Proximity Sensor
 */
class Robot : public frc::TimedRobot {
 public:

  void TeleopPeriodic() override {

    //Get the voltage value from the sensor
    double voltage = m_infrared.GetAverageVoltage();
    bool proximity = false;

    //Find if the sensor voltage is within the given range
    if (voltage <= maxValue && voltage >= minValue) {
      proximity = true;
    }

    frc::SmartDashboard::PutNumber("Infrared Voltage", voltage);
    frc::SmartDashboard::PutBoolean("Proximity Alert", proximity);
  }

 private:
  frc::AnalogInput m_infrared{0};

  //These min and max values should be tuned for every sensor. Can be changed to refine the distance precision.
  double minValue = 1.8;
  double maxValue = 2.8;
};

#ifndef RUNNING_FRC_TESTS
int main() { return frc::StartRobot<Robot>(); }
#endif
