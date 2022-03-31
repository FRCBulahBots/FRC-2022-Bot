package frc.robot.commands.AutonCommands;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;

public class RawDriveForwardTimed extends WaitCommand {

    private final Drivetrain drive;
    private final double speed;

    public RawDriveForwardTimed(Drivetrain drive, double seconds, double speed) {
        super(seconds);
        this.drive = drive;
        this.speed = speed;
    }
    
    @Override
    public void execute() {
        drive.arcadeDrive(-1, 0);
    }

    @Override
    public void end(boolean interrupted) {
        drive.arcadeDrive(0, 0);
    }
}
