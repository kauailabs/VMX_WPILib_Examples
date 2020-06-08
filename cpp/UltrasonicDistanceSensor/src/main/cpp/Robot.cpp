#include <frc/AnalogInput.h>
#include <frc/MedianFilter.h>
#include <frc/TimedRobot.h>
#include <frc/smartdashboard/SmartDashboard.h>

/**
 * This is a sample program demonstrating how to find a distance using an Ultrasonic Sensor
 */
class Robot : public frc::TimedRobot {
 public:

  void TeleopPeriodic() override {
    // Sensor returns a value from 0-4095 that is scaled to inches
    // returned value is filtered with a rolling median filter, since
    // ultrasonics tend to be quite noisy and susceptible to sudden outliers
    double currentDistance =
        m_filter.Calculate(m_ultrasonic.GetVoltage()) * kValueToInches;

    frc::SmartDashboard::PutNumber("Ultrasonic Distance", currentDistance);
  }

 private:
  // Factor to convert sensor values to a distance in inches
  static constexpr double kValueToInches = 0.125;


  // median filter to discard outliers; filters over 10 samples
  frc::MedianFilter<double> m_filter{10};

  frc::AnalogInput m_ultrasonic{0};

};

#ifndef RUNNING_FRC_TESTS
int main() { return frc::StartRobot<Robot>(); }
#endif
