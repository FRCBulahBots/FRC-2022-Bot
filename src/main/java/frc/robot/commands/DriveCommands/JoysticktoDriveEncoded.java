// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DriveCommands;

import frc.robot.subsystems.Drivetrain;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;


/* 
This "JoystickToDrive" command was written to connect the Joystick inputs to the Drivetrain subsystem.
It uses the arcadeDrive method from DifferentialDrive in Drivetrain to control the motors of the drivetrain.
These comments are meant for rookies to learn what the basic structure of the Command here should be.
*/

public class JoysticktoDriveEncoded extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  //Referencing the Drivetrain (we'll need a Drivetrain to drive... right?) and two Double Suppliers to refer to the continuously changing Joystick inputs.
  private final Drivetrain landing_gear;
  private final double distanceInCounts;


  //Constructor with references of the drivetrain and joystick suppliers.
  public JoysticktoDriveEncoded(Drivetrain landing_gear, double distanceInInches) {
    this.landing_gear = landing_gear;
    distanceInCounts = (distanceInInches / 3) / (2 *Math.PI) * 2048;

    //Forcing the computer to check if this subsystem is ready and able to be used.
    addRequirements(landing_gear);
  }


  @Override
  public void initialize() {
    landing_gear.resetEncoder();
  }

  //Execute method
  //This method is CONTINUALLY called. Meaning it's used for adjusting to constantly changing inputs
  //Ex. Using the output of a control loop, or in this use case: matching joystick inputs. 
  @Override
  public void execute() {
    landing_gear.setTest(0.3);

    if (landing_gear.checkTestEncoder() >= distanceInCounts ) landing_gear.setTest(0);
  }

  //End method
  //This method is called ONLY ONCE, but ONLY AFTER THE COMMAND IS INTERRUPTED OR ENDS. Meaning it's used for one-time actions after the command is over.
  //Ex. Resetting solenoids, motors, and actuators.
  @Override
  public void end(boolean interrupted) {
    landing_gear.setTest(0);
  }

  //IsFinished method
  //This method actually determines the end-condition for the Command.
  //It should be false for most times as we typically "interrupt" our Commands to end them, but use it to set custom parameters you need to happen.
  //Ex. Forcing a autonomous command to end when you drive a certain distance, or forcing a motor to stop if it gets too hot.
  @Override
  public boolean isFinished() {
    return landing_gear.checkTestEncoder() >= distanceInCounts;
  }

}
