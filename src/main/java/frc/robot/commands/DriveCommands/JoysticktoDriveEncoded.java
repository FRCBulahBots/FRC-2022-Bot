// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DriveCommands;

import frc.robot.subsystems.Drivetrain;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj2.command.CommandBase;



public class JoysticktoDriveEncoded extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Drivetrain landing_gear;
  private double avgEncoderPos;
  private double targetEncoderCount;
  private double speed;



  public JoysticktoDriveEncoded(Drivetrain landing_gear, double distanceToTravelInInches, boolean forward) {
    this.landing_gear = landing_gear;
    speed = forward ? -0.4: 0.4;
    targetEncoderCount = 668 * distanceToTravelInInches;
    addRequirements(landing_gear);

  }


  @Override
  public void initialize() {
    landing_gear.setDriveType(NeutralMode.Brake);
    landing_gear.resetEncoders();
  }


  @Override
  public void execute() {
    double leftEncoderVal = landing_gear.getLeftEncoder();
    ///double rightEncoderVal = landing_gear.getRightEncoder();

    avgEncoderPos = (leftEncoderVal);

    landing_gear.arcadeDrive(speed, 0);
  }

  @Override
  public void end(boolean interrupted) {
    landing_gear.arcadeDrive(0, 0);
    landing_gear.setAllNeutralOutput();
  }
  @Override
  public boolean isFinished() {
    return Math.abs(avgEncoderPos) >= targetEncoderCount;
  }

}
