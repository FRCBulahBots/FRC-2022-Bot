package frc.robot.commands;

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
        mag.belt2Speed(0.3);
    }

    @Override
    public void end(boolean interrupted) { 
        mag.belt2Speed(0);
    }





}
