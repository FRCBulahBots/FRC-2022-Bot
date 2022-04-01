package frc.robot.commands.AutonCommands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.DriveCommands.JoystickToDrive;
import frc.robot.commands.DriveCommands.JoysticktoDriveEncoded;
import frc.robot.commands.MagazineCommands.JoystickToFeed;
import frc.robot.commands.MagazineCommands.JoystickToMoveBothBelts;
import frc.robot.commands.ShootCommands.JoystickToShoot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;


public class AutonCommand extends SequentialCommandGroup {
    private Drivetrain drive;
    private Magazine mag;
    private Shooter shoot;
    
    public AutonCommand(Drivetrain drive, Magazine mag, Shooter shoot){
        this.drive = drive;
        this.mag = mag;
        this.shoot = shoot;


        addCommands(new WaitCommand(1), 
                    new JoysticktoDriveEncoded(drive, 17, false),
                    new WaitCommand(1),
                    new ParallelRaceGroup(new JoystickToShoot(shoot, -0.9), 
                                          new WaitCommand(4.5).andThen(
                                          new ParallelCommandGroup(new JoystickToFeed(mag, -1),
                                          new JoystickToMoveBothBelts(mag, -0.5)).withTimeout(4))),
                    new JoysticktoDriveEncoded(drive, 36, false));

    }

}