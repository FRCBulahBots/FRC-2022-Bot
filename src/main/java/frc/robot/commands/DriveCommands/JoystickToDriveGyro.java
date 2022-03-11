// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DriveCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Drivetrain;


public class JoystickToDriveGyro extends CommandBase {

  Drivetrain drive;
  PIDController gyroCorrection = new PIDController(0.009, 0, 0);
  DoubleSupplier speed, rotation; 
  double targetAngleDegrees;
  double gyroCorrectedRotation;

  public JoystickToDriveGyro(Drivetrain drive, DoubleSupplier speed, DoubleSupplier rotation, double targetAngleDegrees) {
    this.drive = drive;
    this.speed = speed;
    this.rotation = rotation;
    
    this.targetAngleDegrees = targetAngleDegrees;
    //gyroCorrection.enableContinuousInput(-360, 360);
    addRequirements(drive);
  }

  @Override
  public void execute() {

    if(Math.abs(rotation.getAsDouble()) > 0.3){

      if ((rotation.getAsDouble() < -0.3)){
        gyroCorrectedRotation = drive.clamp(gyroCorrection.calculate(drive.getHeading(), targetAngleDegrees), -1, 1);
      } 
      
      if((rotation.getAsDouble() > 0.3)){
        gyroCorrectedRotation = drive.clamp(gyroCorrection.calculate(drive.getHeading(), -targetAngleDegrees), 1, -1);
      }

    } else {
      gyroCorrectedRotation = drive.clamp(rotation.getAsDouble(), -1, 1);
    }
    
    drive.arcadeDrive(speed.getAsDouble(), gyroCorrectedRotation);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}