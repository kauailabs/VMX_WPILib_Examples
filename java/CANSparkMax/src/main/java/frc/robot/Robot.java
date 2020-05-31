package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.jni.CANSparkMaxJNI;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


//An example program to show the proper use of a CAN Spark Max device with a brushless motor. A single CAN Spark Max is used with a Brushless NEO and CAN ID of zero.
//The Spark Max will output at .2 power in the direction specified by the SmartDashboard "Positive" boolean
public class Robot extends TimedRobot {

  CANSparkMax spark;
  

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

    SmartDashboard.putBoolean("Positive", true);

    spark = new CANSparkMax(0, MotorType.kBrushless);
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

    //REQUIRED for use of VMX-Pi with the CAN Spark Max devices when no RoboRIO is present on the CAN Bus
    //Must also be used in autonomousPeriodic, when applicable
    CANSparkMaxJNI.c_SparkMax_SetEnable(true); // Periodically ensure motor controller outputs are enabled

    double power;

    if (SmartDashboard.getBoolean("Positive", true)) {
      power = .2;
    }
    else {
      power = -.2;
    }

    spark.set(power);

  }

}
