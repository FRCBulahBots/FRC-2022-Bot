// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class JoystickToShoot extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private Shooter catapult = new Shooter();

  public JoystickToShoot(Shooter catapult) {
    this.catapult = catapult;
    addRequirements(catapult);
  }

  
  
  @Override
  public void initialize() {
      catapult.enable();
  }

  
  
  @Override
  public void end(boolean interrupted) {
      catapult.disable();
  }

}
