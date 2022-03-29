// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

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
*/

public class Drivetrain extends SubsystemBase {

  /*Referencing 4 TalonFXs on Falcon 500s, these motors have their motor controllers on top of motor.*/
  private WPI_TalonFX leftLeader = new WPI_TalonFX(Constants.leftMasterDriveID);
  private WPI_TalonFX leftFollower = new WPI_TalonFX(Constants.leftFollowerDriveID);
  private WPI_TalonFX rightLeader = new WPI_TalonFX(Constants.rightMasterDriveID);
  private WPI_TalonFX rightFollower = new WPI_TalonFX(Constants.rightFollowerDriveID);

  /*Gyro, which we read values to */
  private Pigeon2 pigeonGyro = new Pigeon2(Constants.gyroID);
  
  
  /*Differential Drive class which relates two motors to inputs.
  Here we only control the leaders with the DifferentialDrive object.*/
  private DifferentialDrive drive = new DifferentialDrive(leftLeader, rightLeader);

  /* Constructor method which runs everytime we refer to the class. */
  
  public Drivetrain() {

  /*Making "slave follow master", or in this case followers follow master.  
  This allows us to control all motors while making sure they act the same across the board.*/
    leftFollower.follow(leftLeader); 
    rightFollower.follow(rightLeader);

  /*Sets the right side opposite of the left side. Since they both spin the same direction normally.*/
    rightLeader.setInverted(true);
    rightFollower.setInverted(true);
  }

  /* end of constructor method */


  /*main Driving method; takes two doubles and does a lot of math to figure out a combination of motor speeds*/
  public void arcadeDrive(double speed, double rotation){
    drive.arcadeDrive(0.9 * speed, 0.9 * rotation);
  }


  /*method to return the turning "angle" of the bot. 
  Yaw (rotation along the vertical axis of the bot) / 360 = some value we don't care about as much as:
  THE REMAINDER OF THIS DIVISION gives us the actual angle between (-360, 360)*/
  public double getHeading(){
    return Math.IEEEremainder(pigeonGyro.getYaw(), 360);
  }

  /*method to set the Gyro to a specific value.
  mainly used to reset the Gyro to 0, as to reorient the bot.*/
  public void setGyro(double value){
    pigeonGyro.setYaw(value);
  }
  

  @Override
  public void periodic() {
    //SmartDashboard.putNumber("LeftEncoderValue", this.checkTestEncoder());
    SmartDashboard.putNumber("GyroData", pigeonGyro.getYaw());
  }

  /*method to clamp results within a max and min.
  copied from FRC 2018's drive and used primarily in JoystickToDriveGyro*/
  public double clamp(double val, int min, int max) {
    return Math.max(min ,Math.min(max, val));
  }
}
