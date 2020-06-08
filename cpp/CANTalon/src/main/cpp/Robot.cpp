#include <frc/TimedRobot.h>
#include <frc/smartdashboard/SmartDashboard.h>
#include <ctre/phoenix/motorcontrol/can/WPI_TalonSRX.h>
#include <ctre/phoenix/cci/Unmanaged_CCI.h>

using namespace frc;
using namespace ctre::phoenix::motorcontrol::can;

//An example program to show the proper use of a CAN Talon device. A single CAN Talon is used with a CAN ID of zero.
//The talon will output at .2 power in the direction specified by the SmartDashboard "Positive" boolean
class Robot : public frc::TimedRobot {
 public:
  void RobotInit() override {
    SmartDashboard::PutBoolean("Positive", true);
  }
  
  void TeleopPeriodic() override { 
    
    //REQUIRED for use of VMX-Pi with the CAN Talon devices when no RoboRIO is present on the CAN Bus
    //Must also be used in autonomousPeriodic, when applicable
    c_FeedEnable(100); // Enable Phoenix CAN Devices for 100 Milliseconds

    double power;

    //Inverts power if told to do so by the SmartDashboard boolean
    if (SmartDashboard::GetBoolean("Positive", true)) {
      power = .2;
    }
    else {
      power = -.2;
    }

    talon.Set(power);

   }

 private:
  WPI_TalonSRX talon{0};
};

#ifndef RUNNING_FRC_TESTS
int main() { return frc::StartRobot<Robot>(); }
#endif
