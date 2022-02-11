// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;
import frc.robot.commands.DriveCommands.JoystickToDrive;
import frc.robot.commands.MagazineCommands.JoystickToBelt1;
import frc.robot.commands.MagazineCommands.JoystickToBelt2;
import frc.robot.commands.MagazineCommands.joysticktocontrolbeltexample;
import frc.robot.commands.ShootCommands.JoystickToShoot;
import frc.robot.customtriggers.ShooterTrigger;
import edu.wpi.first.wpilibj.Joystick;


/*
The Robot Container class is where all joysticks, subsystems, commands, and anything related to the Robot should be put in.
This class is built for structuring our prior logic in code, so no logic or code should be put here.
Makes it very easy to structure our robot up!
These comments are meant for rookies to learn what the basic structure of the RobotContainer should be.
*/

public class RobotContainer {

  //Subsystem references.
  private final Drivetrain landingGear = new Drivetrain();
  private final Shooter catapult = new Shooter();
  private final Magazine cargoBay = new Magazine();

  //Joystick reference.
  private final Joystick cockpit = new Joystick(Constants.usbport);

  public RobotContainer() {
    // Configures the button bindings
    configureButtonBindings();

    //Default command for our drive system to ALWAYS DRIVE IF WE DON'T ASK ANYTHING ELSE OF IT.
    landingGear.setDefaultCommand(new JoystickToDrive(landingGear, () -> cockpit.getRawAxis(1), () -> cockpit.getRawAxis(4)));

  }


  /*
  configureButtonBindings method.
  Straight forward in what it does. 
  it's binding buttons to our Commands. Duh.
  All non-default commands should be assigned here.
  */
  private void configureButtonBindings() {

    //Shooter toggle on the right trigger. Press once to enable, and another to disable.
    new ShooterTrigger(() -> cockpit.getRawAxis(3))
      .toggleWhenActive(new JoystickToShoot(catapult));

    /*
    new Button(() -> cockpit.getRawButton(1))
      .whenHeld(new JoystickToBelt1(cargoBay));

    new Button(() -> cockpit.getRawButton(2))
      .whenHeld(new JoystickToBelt2(cargoBay));
    */

    new Button(() -> cockpit.getRawButton(1))
      .whenHeld(new StartEndCommand(() -> cargoBay.belt1Speed(true), () -> cargoBay.belt1Speed(false), cargoBay));
      
    new Button(() -> cockpit.getRawButton(2))
      .whenHeld(new StartEndCommand(() -> cargoBay.belt2Speed(true), () -> cargoBay.belt2Speed(false), cargoBay));

    new Button(() -> cockpit.getRawButton(3))
      .whenHeld(new joysticktocontrolbeltexample(cargoBay));

    //new Button(() -> cockpit.getRawButton(4))
      //.whenPressed(new ConditionalCommand(new JoystickToBelt1(cargoBay), new JoystickToBelt2(cargoBay), () -> cargoBay.getLaser2State()));

  }

  /*
  getAutonomousCommand.
  Also very straight forward in what it does.
  It calls our Auton command.
  Our Autonomous Command should be here.
  */
  public Command getAutonomousCommand() {
    return null;
  }
}
