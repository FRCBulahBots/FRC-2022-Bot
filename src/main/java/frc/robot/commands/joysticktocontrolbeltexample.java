package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Magazine;

public class joysticktocontrolbeltexample extends CommandBase{
    
    public Magazine mag;

    public joysticktocontrolbeltexample(Magazine mag) {
        this.mag = mag;
        addRequirements(mag);
    }

    @Override
    public void initialize() {
        mag.testPWM.set(0.3);
    }

    @Override
    public void end(boolean interrupted) { 
        mag.testPWM.set(0);
    }

    @Override
    public boolean isFinished() {
        return !(mag.laser.get());
    }





}
