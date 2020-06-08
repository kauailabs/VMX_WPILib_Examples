#include "AHRS.h"
#include <frc/SPI.h>
#include <frc/Joystick.h>
#include <frc/PWMVictorSPX.h>
#include <frc/TimedRobot.h>
#include <frc/drive/MecanumDrive.h>

/**
 * This is a sample program that uses mecanum drive with the VMX-Pi integrated NavX sensor to
 * maintain rotation vectors in relation to the starting orientation of the robot
 * (field-oriented controls).
 */
class Robot : public frc::TimedRobot {
 public:
  void RobotInit() override {
    // Invert the left side motors. You may need to change or remove this to
    // match your robot.
    m_frontLeft.SetInverted(true);
    m_rearLeft.SetInverted(true);

  }

  /**
   * Mecanum drive is used with the gyro yaw as an input.
   */
  void TeleopPeriodic() override {
    m_robotDrive.DriveCartesian(m_joystick.GetX(), m_joystick.GetY(),
                                m_joystick.GetZ(), m_gyro.GetYaw());
  }

 private:

  frc::PWMVictorSPX m_frontLeft{0};
  frc::PWMVictorSPX m_rearLeft{1};
  frc::PWMVictorSPX m_frontRight{2};
  frc::PWMVictorSPX m_rearRight{3};
  frc::MecanumDrive m_robotDrive{m_frontLeft, m_rearLeft, m_frontRight, m_rearRight};

  AHRS m_gyro{frc::SPI::Port::kMXP}; //Uses the VMX-Pi on-board navX sensors
  frc::Joystick m_joystick{0};
};

#ifndef RUNNING_FRC_TESTS
int main() { return frc::StartRobot<Robot>(); }
#endif
