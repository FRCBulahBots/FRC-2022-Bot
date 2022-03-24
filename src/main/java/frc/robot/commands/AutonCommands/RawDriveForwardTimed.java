package frc.robot.commands.AutonCommands;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;

public class RawDriveForwardTimed extends WaitCommand {

    private Drivetrain drive;
    private double speed;

    public RawDriveForwardTimed(double seconds, double speed) {
        super(seconds);
        speed = this.speed;
    }
    
    @Override
    public void execute() {
        drive.arcadeDrive(speed, 0);
    }

    @Override
    public void end(boolean interrupted) {
        drive.arcadeDrive(0, 0);
    }
}
