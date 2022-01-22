package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class ShooterTrigger extends Trigger{
private double triggerValue;

public ShooterTrigger(double triggerValue){
this.triggerValue = triggerValue;
}
@Override
public boolean get() {
    return triggerValue >= 0.6;
}

}
