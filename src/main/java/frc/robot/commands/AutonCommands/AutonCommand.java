package frc.robot.commands.AutonCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.DriveCommands.JoystickToDrive;
import frc.robot.commands.MagazineCommands.JoystickToFeed;
import frc.robot.commands.MagazineCommands.JoystickToMoveBothBelts;
import frc.robot.commands.ShootCommands.JoystickToShoot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;


public class AutonCommand extends SequentialCommandGroup {
    
    public AutonCommand(Drivetrain drive, Magazine mag, Shooter shoot){
        addCommands(new WaitCommand(1), 
                    new JoystickToDrive(drive, () -> 1.0, () -> 0.0, true).withTimeout(0.5),
                    new WaitCommand(1),
                    new ParallelCommandGroup(new JoystickToFeed(mag, -1), new JoystickToShoot(shoot, -.9), new JoystickToMoveBothBelts(mag, -0.5)).withTimeout(4));
    }


}