package frc.robot.commands.AutonCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;


public class AutonCommand extends CommandGroupBase {

    private Drivetrain drive;

    
    public AutonCommand(Drivetrain drive){
        this.drive = drive;
    }

    @Override
    public void addCommands(Command... commands) {
        new WaitCommand(1);
        new RawDriveForwardTimed(3, 0.3);
        
    }
}
