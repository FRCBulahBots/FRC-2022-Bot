package frc.robot.commands.MagazineCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Magazine;

public class JoystickToBelt1 extends CommandBase {
    
    public Magazine mag;

    public JoystickToBelt1(Magazine mag) {
        this.mag = mag;
        addRequirements(mag);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void end(boolean interrupted) { 
        //mag.belt1Speed(false);
        mag.setBelt1(0);
    }

    @Override
    public boolean isFinished() {
        return mag.getLaser1State();
    }




}
