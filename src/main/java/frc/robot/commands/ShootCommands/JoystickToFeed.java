package frc.robot.commands.ShootCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class JoystickToFeed extends CommandBase{
    private Shooter shoot;
    private double speed;
    public JoystickToFeed(Shooter shoot, double speed){
        this.shoot = shoot;
        this.speed = speed;
    }   

    @Override
    public void initialize() {
        shoot.beltLoaderVroom(speed);
    }

    @Override
    public void end(boolean interrupted) {
        shoot.beltLoaderVroom(0);
    }
}
