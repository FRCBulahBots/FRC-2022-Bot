package frc.robot.commands.MagazineCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Magazine;

public class JoystickToFeed extends CommandBase{
    private Magazine mag;
    private double speed;
    public JoystickToFeed(Magazine mag, double speed){
        this.mag = mag;
        this.speed = speed;
    }   

    @Override
    public void initialize() {
        mag.setBelt2(-0.5);
        mag.beltLoaderVroom(speed);
    }

    @Override
    public void end(boolean interrupted) {
        mag.setBelt2(0);
        mag.beltLoaderVroom(0);
    }
}
