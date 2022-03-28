package frc.robot.commands.AutonCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.MagazineCommands.JoystickToMoveBothBelts;
import frc.robot.commands.ShootCommands.JoystickToFeed;
import frc.robot.commands.ShootCommands.JoystickToShoot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;


public class AutonCommand extends SequentialCommandGroup {
    
    public AutonCommand(Drivetrain drive, Magazine mag, Shooter shoot){
    
        addCommands(new WaitCommand(2), 
                    new RawDriveForwardTimed(drive, 2, -1),
                    new WaitCommand(1),
                    new ParallelCommandGroup(new JoystickToFeed(shoot, -1), new JoystickToShoot(shoot, -.9), new JoystickToMoveBothBelts(mag, -0.5)).withTimeout(4));
    }


}