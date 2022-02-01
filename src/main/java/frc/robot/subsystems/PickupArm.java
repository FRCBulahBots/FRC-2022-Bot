// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/*
The Shooter subsystem is built for 
It is a single Neo controlled by a CANSparkMax to fling the gamepiece sky-high. 
These comments are meant for rookies to learn what the basic structure of the Subsystem here should be.
*/

public class PickupArm extends PIDSubsystem {
  //Referencing a single SparkMax, the motor controller for the Neo.
  private final CANSparkMax armMotor = new CANSparkMax (Constants.armMotorID, MotorType.kBrushless);

  public PickupArm() {
    //This is the PID Controller we use to control the Pickup Arm. 
    //We are trying to set the motor at a set RPM by using percent-output. (Notice we only use P here. Not I or D just because it's not necessary)
    //Math goes: P term * (setpoint - current measurement). here our P term is the only one definite.
    //btw "super" refers to the empty PIDController in PIDSystem, hence we must define it here before using PID.
    super(new PIDController(0.003, 0, 0));

    //setpoint is 4000 rpm.
    setSetpoint(1365);  
  }

//useOutput Method
//This method is how we'll get the motor output.
//We also add a set amount to allow the motor to overcome possible resistances ahead of time.
@Override
protected void useOutput(double output, double setpoint) {
    armMotor.set(output);
}

//getMeasurement Method
//This method returns a double value we want to control.
//In this case, we constantly return the RPM of the motor.
@Override
protected double getMeasurement() {
    return armMotor.getEncoder().getPosition();
}

}

