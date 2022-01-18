// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ExampleSubsystem extends SubsystemBase {
  private TalonFX left1, left2, right1, right2;
  private DifferentialDrive drive;

  public ExampleSubsystem(int left1ID, int left2ID, int right1ID, int right2ID) {
    left1 = new TalonFX(left1ID);
    left2 = new TalonFX(left2ID);
    right1 = new TalonFX(right1ID);
    right2 = new TalonFX(right2ID);

   left2.follow(left1); 
   right2.follow(right1);

   left2.setInverted(true);
   right2.setInverted(true);

    drive = new DifferentialDrive(left1, right1);

  }

  public void arcadeDrive(double speed, double rotation){
    drive.arcadeDrive(speed, rotation);
  }


  @Override`
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
