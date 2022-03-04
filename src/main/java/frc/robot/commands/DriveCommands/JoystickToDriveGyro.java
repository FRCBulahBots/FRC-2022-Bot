// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DriveCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Drivetrain;


public class JoystickToDriveGyro extends PIDCommand {

  Drivetrain drive;

  public JoystickToDriveGyro(DoubleSupplier speed, double targetAngleDegrees, Drivetrain drive) {
    super(
        new PIDController(0.009,0,0),
        drive::getHeading,
        targetAngleDegrees,
        output -> drive.arcadeDrive(speed.getAsDouble(), drive.clamp(output,-1,1)),
        drive);

    getController().enableContinuousInput(-180, 180);
    
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}