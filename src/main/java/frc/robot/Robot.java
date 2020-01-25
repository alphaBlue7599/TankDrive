/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private Joystick m_gamepad;
  private int left_y;
  private int right_y;
  private double leftSpeed;
  private double rightSpeed;
  
  // Defining left and right speed controller groups. Need to import the type/class 

  private PWMVictorSPX m_motorLeftFront;
  private PWMVictorSPX m_motorLeftBack;
  private PWMVictorSPX m_motorRightFront;
  private PWMVictorSPX m_motorRightBack;
  private SpeedControllerGroup leftControllerGroup;
  private SpeedControllerGroup rightControllerGroup;

  @Override
  public void robotInit() {
    m_motorLeftFront = new PWMVictorSPX(1);
    m_motorLeftBack = new PWMVictorSPX(0);
    m_motorRightFront = new PWMVictorSPX(2);
    m_motorRightBack = new PWMVictorSPX(3);
    leftControllerGroup = new SpeedControllerGroup(m_motorLeftFront, m_motorLeftBack);
    rightControllerGroup = new SpeedControllerGroup(m_motorRightFront, m_motorRightBack);

    m_myRobot = new DifferentialDrive(leftControllerGroup, rightControllerGroup);
    m_gamepad = new Joystick(0);
    left_y = 1;
    right_y = 5;
    leftSpeed = 0;
    rightSpeed = 0;
  }

  @Override
  public void teleopPeriodic() {
    leftSpeed = -m_gamepad.getRawAxis(left_y);
    rightSpeed = -m_gamepad.getRawAxis(right_y);
    m_myRobot.tankDrive(leftSpeed, rightSpeed);
    System.out.println(leftSpeed);
    System.out.println(rightSpeed);
  }
}
