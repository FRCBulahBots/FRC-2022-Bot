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
  //Referencing 4 TalonFXs, and a special object used for handling input to output
  //Basically "telling the computer we have four motor controllers and we'll be using them shortly"
  private WPI_TalonFX leftLeader = new WPI_TalonFX(Constants.leftMasterDriveID);
  private WPI_TalonFX leftFollower = new WPI_TalonFX(Constants.rightMasterDriveID);
  private WPI_TalonFX rightLeader = new WPI_TalonFX(Constants.leftFollowerDriveID);
  private WPI_TalonFX rightFollower = new WPI_TalonFX(Constants.rightFollowerDriveID);
  private Pigeon2 pigeonGyro = new Pigeon2(Constants.gyroID);

  //Differential Drive class which relates two motors to inputs.
  //Here we only control the 1s with the DifferentialDrive object; the 2s also follow their 1s.
  private DifferentialDrive drive = new DifferentialDrive(leftLeader, rightLeader);

  //Constructor method which runs everytime we refer to the class.
  public Drivetrain() {

    //Making "slave follow master", or in this case telling 2s to follow 1s.
    leftFollower.follow(leftLeader); 
    rightFollower.follow(rightLeader);

    //Sets the left side opposite of the right side. Since they both spin the same direction normally.
    rightLeader.setInverted(true);
    rightFollower.setInverted(true);
  }

  //A "setter" method to set the values of the motors using two doubles.
  //Set at 70% for now.
  public void arcadeDrive(double speed, double rotation){
    drive.arcadeDrive(0.7 * speed, 0.7 * rotation);
  }


  //Method to return the Left Master Encoder value, should be all we need to control the bot's auton motion.
  public double checkLeftEncoder(){
    return leftLeader.getSelectedSensorPosition();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("LeftEncoderValue", this.checkLeftEncoder());
    SmartDashboard.putNumber("GyroData", pigeonGyro.getYaw());
  }
}
