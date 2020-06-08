#include <frc/TimedRobot.h>
#include <frc/smartdashboard/SmartDashboard.h>
#include <rev/CANSparkMax.h>
#include <rev/CANSparkMaxLowLevel.h>

using namespace frc;
using namespace rev;

//An example program to show the proper use of a CAN Spark Max device with a brushless motor. A single CAN Spark Max is used with a Brushless NEO and CAN ID of zero.
//The Spark Max will output at .2 power in the direction specified by the SmartDashboard "Positive" boolean
class Robot : public frc::TimedRobot {
 public:
  void RobotInit() override {
    SmartDashboard::PutBoolean("Positive", true);
  }
  
  void TeleopPeriodic() override { 
    
    //REQUIRED for use of VMX-Pi with the CAN Spark Max devices when no RoboRIO is present on the CAN Bus
    //Must also be used in autonomousPeriodic, when applicable
    rev::CANSparkMaxLowLevel::SetEnable(true); // Periodically ensure motor controller outputs are enabled

    double power;

    //Inverts power if told to do so by the SmartDashboard boolean
    if (SmartDashboard::GetBoolean("Positive", true)) {
      power = .2;
    }
    else {
      power = -.2;
    }

    spark.Set(power);

   }

 private:
  CANSparkMax spark{0, CANSparkMaxLowLevel::MotorType::kBrushless};
};

#ifndef RUNNING_FRC_TESTS
int main() { return frc::StartRobot<Robot>(); }
#endif
