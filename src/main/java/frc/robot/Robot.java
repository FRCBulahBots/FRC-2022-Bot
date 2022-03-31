// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  int red, blue, green;
  AddressableLED leds = new AddressableLED(0);
  AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(60);

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
/*
    //Alliance alliance = DriverStation.getAlliance();

    for (var i = 0; i < ledBuffer.getLength(); i++){
      ledBuffer.setRGB(i, 0, 0, 255);
    }
    leds.setData(ledBuffer);
    leds.setLength(ledBuffer.getLength());
    leds.start();
*/
  }

  @Override
  public void robotPeriodic() {
    /*
    for (var i = 0; i < ledBuffer.getLength(); i++){
      ledBuffer.setRGB(i, 0, 0, 255);
    }
    leds.setData(ledBuffer);
    */

    SmartDashboard.putNumber("JoystickTrigger Value", m_robotContainer.cockpit.getRawAxis(3));

    CommandScheduler.getInstance().run();
  }


  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}


  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();


    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }


  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
 
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}
}
