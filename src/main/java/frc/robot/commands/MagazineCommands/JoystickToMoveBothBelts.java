package frc.robot.commands.MagazineCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Magazine;

public class JoystickToMoveBothBelts extends CommandBase{
    
    private Magazine mag;
    private double speed;

    public JoystickToMoveBothBelts(Magazine mag, double speed) {
        this.mag = mag;
        this.speed = speed;
        addRequirements(mag);
    }

    @Override
    public void initialize() {
        mag.setBothBelts(speed);
    }

    @Override
    public void end(boolean interrupted) { 
        mag.setBothBelts(0);
    }



}
