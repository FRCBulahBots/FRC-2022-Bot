// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.ProtoClimb;
import frc.robot.subsystems.Shooter;
import frc.robot.commands.ClimbCommands.JoystickToClimb;
import frc.robot.commands.DriveCommands.JoystickToDriveGyro;
import frc.robot.commands.MagazineCommands.JoystickToLowerBelt;
import frc.robot.commands.MagazineCommands.JoystickToMoveBothBelts;
import frc.robot.commands.MagazineCommands.JoystickToUpperBelt;
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
  private final ProtoClimb mount = new ProtoClimb();
 

  //Joystick reference.
  private final Joystick cockpit = new Joystick(Constants.usbport);

  public RobotContainer() {
    // Configures the button bindings
    configureButtonBindings();

    //Default command for our drive system to ALWAYS DRIVE IF WE DON'T ASK ANYTHING ELSE OF IT.
    landingGear.setDefaultCommand(new JoystickToDriveGyro(landingGear, () -> cockpit.getRawAxis(1), () -> cockpit.getRawAxis(4), landingGear.getHeading()));

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
      .toggleWhenActive(new JoystickToShoot(catapult, -0.7));

    //Y Button to "index" balls by referring to the IR Sensor(s) near the belts.
    new Button(() -> cockpit.getRawButton(4))
      .toggleWhenActive(new SequentialCommandGroup(new JoystickToUpperBelt(cargoBay).withInterrupt(() -> cockpit.getRawButton(5)), new JoystickToLowerBelt(cargoBay).withInterrupt(() -> cockpit.getRawButton(5))));

    //left and right bumpers to manually mvoe belts.
    new Button(() -> cockpit.getRawButton(5))
      .whenHeld(new JoystickToMoveBothBelts(cargoBay, -0.5));

    new Button(() -> cockpit.getRawButton(6))
      .whenHeld(new JoystickToMoveBothBelts(cargoBay, 0.5));

    // up and down to control climb
    new POVButton(cockpit, 0)
      .whenHeld(new JoystickToClimb(mount, 0.3));
    
    new POVButton(cockpit, 180)
      .whenHeld(new JoystickToClimb(mount, -0.3));

    //new Button(() -> cockpit.getRawButton(2))
      //.whenPressed(new JoysticktoDriveEncoded(landingGear, 144));
    
    //new Button(() -> cockpit.getRawButton(3))
      //.whenPressed(new InstantCommand(() -> landingGear.resetEncoder(), landingGear));
    
  
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
