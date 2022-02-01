// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
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

  //Constructor with only a reference of the shooter.
  //Basically "we need THIS thing specifically."
  public JoystickToShoot(Shooter catapult) {
    this.catapult = catapult;
    addRequirements(catapult);
  }


  //A initialize method to enable the shooter since we only want to call the enable method once.
  @Override
  public void initialize() {
      catapult.enable();
  }

  //And upon wanting to end the PID control, we disable it and reset the setpoint.
  @Override
  public void end(boolean interrupted) {
      catapult.disable();
  }

  

}
