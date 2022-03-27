// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DriveCommands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;



public class JoysticktoDriveEncoded extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Drivetrain landing_gear;
  private final double distanceInCounts;


  public JoysticktoDriveEncoded(Drivetrain landing_gear, double distanceInInches) {
    this.landing_gear = landing_gear;
    distanceInCounts = (distanceInInches / 3) / (2 *Math.PI) * 2048;

    addRequirements(landing_gear);
  }


  @Override
  public void initialize() {
    landing_gear.resetEncoder();
  }


  @Override
  public void execute() {
    landing_gear.setTest(0.3);
    if (landing_gear.checkTestEncoder() >= distanceInCounts ) landing_gear.setTest(0);
  }

  @Override
  public void end(boolean interrupted) {
    landing_gear.setTest(0);
  }
  @Override
  public boolean isFinished() {
    return landing_gear.checkTestEncoder() >= distanceInCounts;
  }

}
