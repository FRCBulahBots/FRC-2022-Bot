// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/*
The Shooter subsystem is built for... shooting the game piece!
It is a single Neo controlled by a CANSparkMax to fling the gamepiece sky-high. 
These comments are meant for rookies to learn what the basic structure of the Subsystem here should be.
*/

public class Shooter extends SubsystemBase {
  //Referencing and instantiating a single SparkMax, the motor controller for the Neo.
  private final CANSparkMax shooterMotor = new CANSparkMax (Constants.shooterMotorID, MotorType.kBrushless);
  private SparkMaxPIDController shooterMotorPID = shooterMotor.getPIDController();
  private WPI_TalonSRX beltLoader = new WPI_TalonSRX(Constants.beltLoaderID);

  public Shooter() {
    //This motor is special since we want some PID control to it.
    //PID = regulation of a value given another value.
    //We're using a desired value for RPM to regulate our motor's percent output THROUGH the SparkMax's PID controller..

    shooterMotorPID.setP(0.0002, 0);
    shooterMotorPID.setI(0,0);
    shooterMotorPID.setD(0, 0);
    //shooterMotorPID.setFF(200);
    shooterMotorPID.setOutputRange(-1, 1, 0);
    shooterMotor.setIdleMode(IdleMode.kCoast);

    shooterMotor.burnFlash();

  }

  @Override
  public void periodic() { 
    //Reports the ShooterRPM to ShuffleBoard, which is a form of SmartDashboard
    SmartDashboard.putNumber("ShooterRPM", shooterMotor.getEncoder().getVelocity());
  }

  public void setShooterMotorWithPID(double speedToSet){
    //speedToSet is a value b/w 1 and -1, we turn that into a certain RPM...
    double setpoint = speedToSet / 5676;
    //... and set the Motor to aim for that RPM.
    shooterMotorPID.setReference(setpoint, CANSparkMax.ControlType.kDutyCycle, 0);
  }



  //Basic Setter Method for Shooter Speed.
  public void setShooter(double speedToSet){
    shooterMotor.set(speedToSet);
    beltLoader.set(-0.5);
  }

  public void beltLoaderVroom(double setToSpeed){
    beltLoader.set(setToSpeed);
  }
}
