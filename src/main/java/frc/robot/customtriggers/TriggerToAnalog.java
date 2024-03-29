// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.customtriggers;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.button.Trigger;


//Custom "trigger" subclass to make the Joystick trigger a button rather than an axis.
//These comments are meant to help rookies understand this particular subclass works.

public class TriggerToAnalog extends Trigger{
    //we only need the trigger value of what specific trigger we need so we reference only that.
    private DoubleSupplier triggerValue;

//Constructor method assigns our triggerValue to a specific trigger value we want.
public TriggerToAnalog(DoubleSupplier triggerValue){
    this.triggerValue = triggerValue;
}

//A get method allows us to turn a trigger into a "button" of sorts.
//Returns true if the trigger is pressed 60% of the way down, and false if not.
@Override
public boolean get() {
    return (triggerValue.getAsDouble() >= 0.6);
}

}
