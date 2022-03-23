package frc.robot.commands.AutonCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.ScheduleCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;


public class AutonCommand extends CommandBase {

    private Drivetrain drive;
    private StartEndCommand driveForward = new StartEndCommand(() -> drive.arcadeDrive(0.3, 0), () -> drive.arcadeDrive(0,0), drive);
    private StartEndCommand turntoOneDirection = new StartEndCommand(() -> drive.arcadeDrive(0, 0.3), () -> drive.arcadeDrive(0,0), drive);
    
    public AutonCommand(Drivetrain drive){
        this.drive = drive;
    }

    @Override
    public void initialize() {
        //new SequentialCommandGroup(new WaitCommand(2), driveForward, turntoOneDirection, driveForward);

        new SequentialCommandGroup(new WaitCommand(2),new ScheduleCommand(driveForward).withTimeout(2), new ScheduleCommand(turntoOneDirection).withTimeout(2), new ScheduleCommand(driveForward.withTimeout(2)));
    }

    @Override
    public void end(boolean interrupted) {
        //this.cancel();
    }
}
