// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    //Can IDs for DriveMotors
    public static int leftMasterDriveID = 10;
    public static int leftFollowerDriveID = 1;
    public static int rightMasterDriveID = 2;
    public static int rightFollowerDriveID = 3;
    public static int belt1ID = 4;
    public static int belt2ID = 5;
    public static int beltLoaderID = 6;

    //SparkMaxIDs, aptly named
    public static int shooterMotorID = 7;
    public static int armMotorID = 8;

    //Gyro ID
    public static int gyroID = 9;
    //public static int climbID = 10;

    //Joystick Port
    public static int usbport = 0;

    //PWM Port
    public static int PWNTest = 2;

    //DIO Ports
    public static int laser1port = 8;
    public static int laser2port = 9;


}

