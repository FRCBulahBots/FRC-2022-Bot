// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ShootCommands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Shooter;

/* 
This "JoystickToShoot" command was written to connect the Joystick inputs to the Drivetrain subsystem.
It uses the PIDSubsystem enable and disable methods to toggle PID control on the Shooter Motor.
These comments are meant for rookies to learn what the basic structure of the Command here should be.
*/

public class JoystickToShoot extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  //referencing the Shooter, since we need it of course.
  private Shooter catapult;
  private double valueToSet;

  //Constructor with only a reference of the shooter.
  public JoystickToShoot(Shooter catapult, double valueToSet) {
    this.catapult = catapult;
    this.valueToSet = valueToSet;
    addRequirements(catapult);
  }

  //One time call for setting our motor's output; as of this commit 70%
  @Override 
  public void initialize() {
    catapult.setShooter(valueToSet);
  }

  //Then after command ends, reset the motor to 0.
  @Override
  public void end(boolean interrupted) {
    catapult.setShooter(0);
  
  }

}
