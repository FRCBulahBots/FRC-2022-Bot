package frc.robot.commands.MagazineCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Magazine;

public class JoystickToBelt2 extends CommandBase{
    
    public Magazine mag;

    public JoystickToBelt2(Magazine mag) {
        this.mag = mag;
        addRequirements(mag);
    }

    @Override
    public void initialize() {
        mag.belt1Speed(true);
        mag.belt2Speed(true);
    }

    @Override
    public void end(boolean interrupted) { 
        mag.belt1Speed(false);
        mag.belt2Speed(false);
    }

    @Override
    public boolean isFinished() {
        return mag.getLaser2State();
    }



}