package frc.robot.commands.MagazineCommands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Magazine;

public class JoystickToContinuouslyIndexMagazine extends CommandBase{
    
    Magazine mag;
    boolean topBelt, bottomBelt;
    BooleanSupplier reverse;
    
    public JoystickToContinuouslyIndexMagazine(Magazine mag, BooleanSupplier reverse){
        this.mag = mag;
        this.reverse = reverse;
        addRequirements(mag);
    }
    
 
    @Override
    public void execute() {
        if(topBelt && !reverse.getAsBoolean()){
            if(bottomBelt){
                mag.setBelt2(0);
                return;
            }
            mag.setBelt2(0);
            return;
        } 


        if (reverse.getAsBoolean()){
            mag.setBelt2(0.5);
            mag.setBelt1(0.5);
            return;
        }     

        mag.setBothBelts(-0.5);
        topBelt = mag.getLaser2State();
        bottomBelt = mag.getLaser1State();
    }

    

    @Override
    public void end(boolean interrupted) {
        mag.setBothBelts(0);
    }



}
