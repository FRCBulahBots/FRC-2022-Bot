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
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Shooter;
import frc.robot.commands.AutonCommands.AutonCommand;
import frc.robot.commands.AutonCommands.JoystickToDriveGyro;
import frc.robot.commands.ClimbCommands.JoystickToClimb;
import frc.robot.commands.DriveCommands.JoystickToDrive;
import frc.robot.commands.MagazineCommands.JoystickToLowerBelt;
import frc.robot.commands.MagazineCommands.JoystickToMoveBothBelts;
import frc.robot.commands.MagazineCommands.JoystickToUpperBelt;
import frc.robot.commands.ShootCommands.JoystickToFeed;
import frc.robot.commands.ShootCommands.JoystickToShoot;
import frc.robot.customtriggers.ShooterTrigger;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


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
  private final Climb mount = new Climb();
  
  boolean manualBelt;

  //Joystick reference.
  private final Joystick cockpit = new Joystick(Constants.usbport);

  SendableChooser<Command> driveTypeChooser = new SendableChooser<>();

  private Command gyroDrive = new JoystickToDriveGyro(landingGear, () -> cockpit.getRawAxis(1), () -> cockpit.getRawAxis(4), false);
  private Command normalDrive = new JoystickToDrive(landingGear, () -> cockpit.getRawAxis(1), () -> cockpit.getRawAxis(4));


  public RobotContainer() {
    // Configures the button bindings
    configureButtonBindings();

      CameraServer.startAutomaticCapture(0);
      CameraServer.startAutomaticCapture(1);

    SmartDashboard.putBoolean("Gyro Active", gyroDrive.isScheduled());


  }

  /*
  configureButtonBindings method.
  Straight forward in what it does. 
  it's binding buttons to our Commands. Duh.
  All non-default commands should be assigned here.
  */
  private void configureButtonBindings() {

    //Default command for our drive system to ALWAYS DRIVE IF WE DON'T ASK ANYTHING ELSE OF IT.
    landingGear.setDefaultCommand(normalDrive);

    mount.setDefaultCommand(new JoystickToClimb(mount, 168.44, () -> cockpit.getRawButton(6), () -> cockpit.getRawButton(5)));

    //Shooter toggle on the right trigger. Press once to enable, and another to disable.
    new ShooterTrigger(() -> cockpit.getRawAxis(3))
      .toggleWhenActive(new JoystickToShoot(catapult, -0.9));

    //Belt loader toggle on the left trigger. Press once to enable, and another to disable.
    new ShooterTrigger(() -> cockpit.getRawAxis(2))
      .whileActiveContinuous(new JoystickToFeed(catapult, -1.0));

    //A and B button to manually control the belt without sensors.  
    new Button(() -> cockpit.getRawButton(2))
      .toggleWhenPressed(new JoystickToMoveBothBelts(cargoBay, 0.5));

    new Button(() -> cockpit.getRawButton(1))
      .toggleWhenPressed(new JoystickToMoveBothBelts(cargoBay, -0.5));

    //Y Button to "index" balls by referring to the IR Sensor(s) near the belts.
    new Button(() -> cockpit.getRawButton(3))
      .toggleWhenActive(new SequentialCommandGroup(new JoystickToUpperBelt(cargoBay).withInterrupt(() -> cockpit.getRawButton(4)), new JoystickToLowerBelt(cargoBay).withInterrupt(() -> cockpit.getRawButton(4))));
    
    new Button(() -> cockpit.getRawButton(7))
      .toggleWhenPressed(gyroDrive);
    
    
  
  }
  /*
  getAutonomousCommand.
  Also very straight forward in what it does.
  It calls our Auton command.
  Our Autonomous Command should be here.
  */
  public Command getAutonomousCommand() {
    return new AutonCommand(landingGear);
  }
}
