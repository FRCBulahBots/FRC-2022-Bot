package frc.robot.commands.MagazineCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Magazine;

public class JoystickToUpperBelt extends CommandBase {
    
    public Magazine mag;

    public JoystickToUpperBelt(Magazine mag) {
        this.mag = mag;
        addRequirements(mag);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void end(boolean interrupted) { 
        mag.setBelt1(0);
    }

    @Override
    public boolean isFinished() {
        return mag.getLaser1State();
    }




}
