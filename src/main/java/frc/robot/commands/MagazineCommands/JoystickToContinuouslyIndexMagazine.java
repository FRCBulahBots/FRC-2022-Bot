package frc.robot.commands.MagazineCommands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Magazine;

public class JoystickToContinuouslyIndexMagazine extends CommandBase{
    
    Magazine mag;
    boolean topBelt, bottomBelt;
    BooleanSupplier feed;
    
    public JoystickToContinuouslyIndexMagazine(Magazine mag, BooleanSupplier feed){
        this.mag = mag;
        this.feed = feed;
        addRequirements(mag);
    }
    
 
    @Override
    public void execute() {
        if (!feed.getAsBoolean()){
            mag.setBelt2(-0.5);
            mag.setBelt1(-0.5);
            mag.beltLoaderVroom(-.9);
            return; 
        }     
        if(topBelt && feed.getAsBoolean()){
            if(bottomBelt){
                mag.setBelt1(0);
            }
            mag.setBelt2(0);
        } else {
            mag.setBothBelts(-0.5);
            mag.beltLoaderVroom(0);
        }        
        bottomBelt = mag.getLaser1State();
        topBelt = mag.getLaser2State();
    }

    

    @Override
    public void end(boolean interrupted) {
        mag.setBothBelts(0);
    }



}
