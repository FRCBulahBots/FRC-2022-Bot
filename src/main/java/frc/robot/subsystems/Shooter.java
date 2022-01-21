// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/*
The Shooter subsystem is built for 
It is a single Neo controlled by a CANSparkMax to fling the gamepiece sky-high. 
These comments are meant for rookies to learn what the basic structure of the Subsystem here should be.

Psst. Rohan from 10PM on 1/20/22 here.
THIS IS A PID-BASED SUBSYSTEM. Very special! And also somewhat wacky compared to the normal Subsystem
Rohan from the past wants you to go look up: "PID Theory Explained" and go see the article from PID Explained.com
Try and get a grasp on it for yourself before Rohan from the future talks to you about it.

*/
public class Shooter extends PIDSubsystem {
  private final CANSparkMax shooterMotor = new CANSparkMax (Constants.shooterMotorID, MotorType.kBrushless);


  public Shooter() {
    super(new PIDController(0.003, 0, 0));
    shooterMotor.setIdleMode(IdleMode.kCoast);
    setSetpoint(4000);  
  }

@Override
protected void useOutput(double output, double setpoint) {
    shooterMotor.set(output + 0.1);
}

@Override
protected double getMeasurement() {
    return shooterMotor.getEncoder().getVelocity();
}


@Override
public void periodic() {
    
}

}
