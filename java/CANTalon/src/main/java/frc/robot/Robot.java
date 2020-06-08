package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.unmanaged.UnmanagedJNI;


//An example program to show the proper use of a CAN Talon device. A single CAN Talon is used with a CAN ID of zero.
//The talon will output at .2 power in the direction specified by the SmartDashboard "Positive" boolean
public class Robot extends TimedRobot {

  WPI_TalonSRX talon;


  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

    SmartDashboard.putBoolean("Positive", true);

    talon = new WPI_TalonSRX(0);

  }

  /**
   * This function is called once when teleop is enabled.
   */
  @Override
  public void teleopInit() {
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    //REQUIRED for use of VMX-Pi with the CAN Talon devices when no RoboRIO is present on the CAN Bus
    //Must also be used in autonomousPeriodic, when applicable
    UnmanagedJNI.JNI_FeedEnable(100); // Enable Phoenix CAN Devices for 100 Milliseconds

    double power;

    //Inverts power if told to do so by the SmartDashboard boolean
    if (SmartDashboard.getBoolean("Positive", true)) {
      power = .2;
    }
    else {
      power = -.2;
    }

    talon.set(power);
  }

}
