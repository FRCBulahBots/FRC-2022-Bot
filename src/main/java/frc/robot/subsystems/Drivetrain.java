// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/*
The Drivetrain subsystem is responsible for "driving" the robot around.
It uses two gearboxes with four Falcon 500s total signaled by 4 Talon FX Motor Controllers.
These comments are meant for rookies to learn what the basic structure of the Subsystem here should be.
*/

public class Drivetrain extends SubsystemBase {
  //Referencing 4 TalonFXs, and a special object used for handling input to output
  //Basically "telling the computer we have four motor controllers and we'll be using them shortly"
  private WPI_TalonFX left1, left2, right1, right2;
  private DifferentialDrive drive;

  //Constructor with specific CAN IDs to instantiate each motor controller
  //Basically "telling the computer now these four motor controller you see with these IDs are the one we're talking about."
  public Drivetrain(int left1ID, int left2ID, int right1ID, int right2ID) {
  left1 = new WPI_TalonFX(left1ID);
  left2 = new WPI_TalonFX(left2ID);
  right1 = new WPI_TalonFX(right1ID);
  right2 = new WPI_TalonFX(right2ID);

  //Making "slave follow master", or in this case telling 2s to follow 1s.
  left2.follow(left1); 
  right2.follow(right1);

  //Setting 2s inverted to 1s so that they don't break the gearboxes and actually spin.
  left2.setInverted(true);
  right2.setInverted(true);

  //Telling that we only care about the status of the 1s, and that we want to control them.
  drive = new DifferentialDrive(left1, right1);
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
