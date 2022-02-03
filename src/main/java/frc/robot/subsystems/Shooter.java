// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/*
The Shooter subsystem is built for... shooting the game piece!
It is a single Neo controlled by a CANSparkMax to fling the gamepiece sky-high. 
These comments are meant for rookies to learn what the basic structure of the Subsystem here should be.
*/

public class Shooter extends PIDSubsystem {
  //Referencing a single SparkMax, the motor controller for the Neo.
  private final CANSparkMax shooterMotor = new CANSparkMax (Constants.shooterMotorID, MotorType.kBrushless);

  public Shooter() {
    //This is the PID Controller we use to control the Shooter Motor. 
    //We are trying to set the motor at a set RPM by using percent-output. (Notice we only use P here. Not I or D just because it's not necessary)
    //Math goes: P term * (setpoint - current measurement). here our P term is the only one definite.
    //btw "super" refers to the empty PIDController in PIDSubsystem, hence we must define it here before using PID.
    super(new PIDController(0.003, 0, 0));

    //might need to remove this line for brake idle.
    shooterMotor.setIdleMode(IdleMode.kCoast);
    //setpoint is 4000 rpm.
    setSetpoint(4000);  
  }

//useOutput Method
//This method is how we'll get the motor output.
//We also add a set amount to allow the motor to overcome possible resistances ahead of time.
@Override
protected void useOutput(double output, double setpoint) {
    shooterMotor.set(output + 0.1);
}

//getMeasurement Method
//This method returns a double value we want to control.
//In this case, we constantly return the RPM of the motor.
@Override
protected double getMeasurement() {
    return shooterMotor.getEncoder().getVelocity();
}

public void setShooterMotor(double speedToSet){
  shooterMotor.set(speedToSet);
}

@Override
public void periodic() {
    SmartDashboard.putNumber("ShooterRPM", shooterMotor.getEncoder().getVelocity());
}

}
