// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import javax.xml.catalog.Catalog;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/*
The Shooter subsystem is built for... shooting the game piece!
It is a single Neo controlled by a CANSparkMax to fling the gamepiece sky-high. 
These comments are meant for rookies to learn what the basic structure of the Subsystem here should be.
*/

public class Shooter extends SubsystemBase {
  //Referencing a single SparkMax, the motor controller for the Neo.
  private final CANSparkMax shooterMotor = new CANSparkMax (Constants.shooterMotorID, MotorType.kBrushless);

  //private final SimpleMotorFeedforward = new SimpleMotorFeedforward(0, 0.1);

  public Shooter() {
    //might need to remove this line for brake idle.
    shooterMotor.setIdleMode(IdleMode.kBrake);
  }



@Override
public void periodic() {
    SmartDashboard.putNumber("ShooterRPM", shooterMotor.getEncoder().getVelocity());
}

public void setShooterMotor(double speedToSet){
  shooterMotor.set(speedToSet);
}

}
