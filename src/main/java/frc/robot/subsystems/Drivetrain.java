// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
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

  //To-DO: Re-Change IDs to refer "Constants" here instead of in the Constructor everytime.
  //*Ask if we can switch to leader and follower terminology.
  private WPI_TalonFX left1 = new WPI_TalonFX(left1ID);
  private WPI_TalonFX left2 = new WPI_TalonFX(left2ID);
  private WPI_TalonFX right1 = new WPI_TalonFX(right1ID);
  private WPI_TalonFX right2 = new WPI_TalonFX(right2ID);
  

  //Differential Drive class which relates two motors to inputs.
  //Here we only control the 1s with the DifferentialDrive object; the 2s also follow their 1s.
  private DifferentialDrive drive = new DifferentialDrive(left1, right1);;

  //Constructor method which runs everytime we refer to the class.
  public Drivetrain() {

  //Making "slave follow master", or in this case telling 2s to follow 1s.
  left2.follow(left1); 
  right2.follow(right1);

  }


  //A "setter" method to set the values of the motors using two doubles.
  public void arcadeDrive(double speed, double rotation){
    drive.arcadeDrive(speed, rotation);
  }

  /*
  if we have time to, we can try adding chrp functionality.
  public void chirp(){
  }
  */

}
