package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import com.studica.frc.TitanQuad;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**
 * This is a sample program that uses mecanum drive with the VMX-Pi integrated NavX 
 * sensor and a Titan Quad motor controller to maintain rotation vectors in relation 
 * to the starting orientation of the robot
 * (field-oriented controls).
 */
public class Robot extends TimedRobot {

  private MecanumDrive m_robotDrive;
  private final AHRS m_gyro = new AHRS(Port.kMXP);
  private final Joystick m_joystick = new Joystick(0);

  @Override
  public void robotInit() {
    TitanQuad frontLeft = new TitanQuad(0, 0);
    TitanQuad rearLeft = new TitanQuad(0, 1);
    TitanQuad frontRight = new TitanQuad(0, 2);
    TitanQuad rearRight = new TitanQuad(0, 3);

    

    // Invert the left side motors.
    // You may need to change or remove this to match your robot.
    frontLeft.setInverted(true);
    rearLeft.setInverted(true);

    m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

  }

  /**
   * Mecanum drive is used with the gyro yaw as an input.
   */
  @Override
  public void teleopPeriodic() {
    m_robotDrive.driveCartesian(m_joystick.getX(), m_joystick.getY(),
        m_joystick.getZ(), m_gyro.getYaw());
  }
}
