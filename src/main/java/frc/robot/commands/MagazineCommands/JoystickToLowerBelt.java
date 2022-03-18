package frc.robot.commands.MagazineCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Magazine;

public class JoystickToLowerBelt extends CommandBase{
    
    public Magazine mag;

    public JoystickToLowerBelt(Magazine mag) {
        this.mag = mag;
        addRequirements(mag);
    }

    @Override
    public void initialize() {
        mag.setBelt1(-0.3);
        mag.setBelt2(-0.3);
    }

    @Override
    public void end(boolean interrupted) { 
        mag.setBelt2(0);
    }

    @Override
    public boolean isFinished() {
        return mag.getLaser2State();
    }



}
