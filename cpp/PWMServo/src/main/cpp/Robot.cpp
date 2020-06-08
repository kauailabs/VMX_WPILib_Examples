#include <frc/TimedRobot.h>
#include <frc/Servo.h>

/**
 * Moves a PWM servo (port 0) back and forth from the minimum and maximum.
 */
class Robot : public frc::TimedRobot
{
public:
  void TeleopPeriodic() override
  {
    //Once the servo reaches the min or max throw, set the position target to the opposite side.
    if (servo.Get() < 0.1)
    {
      servo.Set(1);
    }
    else if (servo.Get() > 0.9)
    {
      servo.Set(0);
    }
  }

private:
  frc::Servo servo{0};
};

#ifndef RUNNING_FRC_TESTS
int main()
{
  return frc::StartRobot<Robot>();
}
#endif
