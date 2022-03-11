// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.Pigeon2;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/*
The Drivetrain subsystem is responsible for "driving" the robot around.
It uses two gearboxes with four Falcon 500s total signaled by 4 Talon FX Motor Controllers.
These comments are meant for rookies to learn what the basic structure of the Subsystem here should be.
Written (mostly) by Ryan Wilks and Mason Pizzalato.
*/

public class Drivetrain extends SubsystemBase {
  //Referencing 4 TalonFXs, and a special object used for handling input to output
  //Basically "telling the computer we have four motor controllers and we'll be using them shortly"

  //To-DO: Re-Change IDs to refer "Constants" here instead of in the Constructor everytime.
  //*Ask if we can switch to leader and follower terminology.
  private WPI_TalonFX leftLeader = new WPI_TalonFX(Constants.leftMasterDriveID);
  private WPI_TalonFX leftFollower = new WPI_TalonFX(Constants.rightMasterDriveID);
  private WPI_TalonFX rightLeader = new WPI_TalonFX(Constants.leftFollowerDriveID);
  private WPI_TalonFX rightFollower = new WPI_TalonFX(Constants.rightFollowerDriveID);

  //private WPI_TalonFX testMotor = new WPI_TalonFX(Constants.testMotorID);
  private Pigeon2 pigeonGyro = new Pigeon2(Constants.gyroID);
  //private AxisCamera gamer = new AxisCamera("Gamering", host);

  //Differential Drive class which relates two motors to inputs.
  //Here we only control the leaders with the DifferentialDrive object.
  private DifferentialDrive drive = new DifferentialDrive(leftLeader, rightLeader);

  //Constructor method which runs everytime we refer to the class.
  public Drivetrain() {

    //Making "slave follow master", or in this case followers follow master.  
    //This allows us to control all motors while making sure they act the same across the board.
    leftFollower.follow(leftLeader); 
    rightFollower.follow(rightLeader);

    //Sets the right side opposite of the left side. Since they both spin the same direction normally.
    rightLeader.setInverted(true);
    rightFollower.setInverted(true);
  }

  //A "setter" method to set the values of the motors using two doubles.
  public void arcadeDrive(double speed, double rotation){
    drive.arcadeDrive(0.7 * speed, 0.7 * rotation);
  }

  public double getHeading(){
    return Math.IEEEremainder(pigeonGyro.getYaw(), 360);
  }

  /*

  //Method to return the Left Master Encoder value, should be all we need to control the bot's auton motion.
  public double checkTestEncoder(){
    return testMotor.getSelectedSensorPosition();
  }

  public void setTest(double speed){
    testMotor.set(ControlMode.PercentOutput, speed);
  }
  

  public void resetEncoder(){
    testMotor.setSelectedSensorPosition(0);
  }

  */

  @Override
  public void periodic() {
    //SmartDashboard.putNumber("LeftEncoderValue", this.checkTestEncoder());
    SmartDashboard.putNumber("GyroData", pigeonGyro.getYaw());
  }

  public double clamp(double val, int min, int max) {
    return Math.max(min ,Math.min(max, val));
  }
}
