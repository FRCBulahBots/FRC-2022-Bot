package frc.robot.commands.AutonCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;


public class AutonCommand extends SequentialCommandGroup {
    
    public AutonCommand(Drivetrain drive){
    
        addCommands(new WaitCommand(2), 
                    new RawDriveForwardTimed(drive, 2, 1),
                    new WaitCommand(2),
                    new RawDriveForwardTimed(drive, 2, -1));
    }


}