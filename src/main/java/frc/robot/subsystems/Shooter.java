// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import com.revrobotics.CANSparkMax;

/*
The Shooter subsystem is built for 
It is a single Neo controlled by a CANSparkMax to fling the gamepiece sky-high. 
These comments are meant for rookies to learn what the basic structure of the Subsystem here should be.

Psst. Rohan from 10PM on 1/20/22 here.
THIS IS A PID-BASED SUBSYSTEM. Very special! And also somewhat wacky compared to the normal Subsystem
Rohan from the past wants you to go look up: "PID Theory Explained" and go see the article from National Instruments, or NI.
Try and get a grasp on it for yourself before Rohan from the future talks to you about it.

*/
public class Shooter extends PIDSubsystem {
    private final CANSparkMax shooterMotor;
    private final SimpleMotorFeedforward feedforward;

  public Shooter() {
    super(new PIDController(kp, ki, kd, period));
      
  }

@Override
protected void useOutput(double output, double setpoint) {
    // TODO Auto-generated method stub
}

@Override
protected double getMeasurement() {
    // TODO Auto-generated method stub
    return 0;
}


@Override
public void periodic() {
    
}

}
