package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Magazine extends SubsystemBase {

private CANSparkMax Belt1 = new CANSparkMax(9, MotorType.kBrushless);
private CANSparkMax Belt2 = new CANSparkMax(8, MotorType.kBrushless);

public Magazine(){

}
 
public void belt1Speed(double speed) {
    Belt1.set(speed);
}

public void belt2Speed(double speed){
    Belt2.set(speed);
}

public double getBelt1(){
    return Belt1.get();
}


}
