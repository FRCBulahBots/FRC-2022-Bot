// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutonCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Drivetrain;

public class JoystickToDriveGyro extends CommandBase {

  Drivetrain drive;
  PIDController gyroCorrection = new PIDController(0.03, 0.019, 0.00069);
  DoubleSupplier speed, rotation;
  double targetAngleDegrees;
  boolean Auton, gyroNeedsReset;
  boolean lastIsDriverSteering;

  public JoystickToDriveGyro(Drivetrain drive, DoubleSupplier speed, DoubleSupplier rotation, boolean Auton) {
    this.drive = drive;
    this.speed = speed;
    this.rotation = rotation;
    this.Auton = Auton;

    // gyroCorrection.enableContinuousInput(-360, 360);
    addRequirements(drive);
  }

  @Override
  public void initialize() {
    // drive.resetGyro();
  }

  @Override
  public void execute() {
    double finalCalcSteering;
    boolean isDriverSteering = Math.abs(rotation.getAsDouble()) > 0.2;

    if (isDriverSteering) {
      finalCalcSteering = rotation.getAsDouble();
    } else {
      if (lastIsDriverSteering) {
        targetAngleDegrees = drive.getHeading();
      }
      double unclampedSteer = gyroCorrection.calculate(drive.getHeading(), targetAngleDegrees);
      finalCalcSteering = drive.clamp(unclampedSteer, -1, 1);
    }
    drive.arcadeDrive(speed.getAsDouble(), finalCalcSteering);
    lastIsDriverSteering = isDriverSteering;
  }

}