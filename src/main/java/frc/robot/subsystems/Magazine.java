package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.motorcontrol.DMC60;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.led.CANdle.VBatOutputMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Magazine extends SubsystemBase {

//private CANSparkMax Belt1 = new CANSparkMax(9, MotorType.kBrushless);
//private CANSparkMax Belt2 = new CANSparkMax(8, MotorType.kBrushless);

private Relay belt1 = new Relay(0, Direction.kReverse);
private Relay belt2 = new Relay(1, Direction.kForward);

private DigitalInput laser1 = new DigitalInput(Constants.laser1port);
private DigitalInput laser2 = new DigitalInput(Constants.laser2port);
public DMC60 testPWM = new DMC60(Constants.PWNTest);

public Magazine(){}
 
@Override
public void periodic() {
    SmartDashboard.putBoolean("Ball in lowermagazine?", laser1.get());
    SmartDashboard.putBoolean("Ball in uppermagazine?", laser2.get());
}
public void belt1Speed(boolean onOrOff) {
    if (onOrOff) belt1.set(Value.kOn);
    else {
        belt1.set(Value.kOff);
    }
}

public void belt2Speed(boolean onOrOff){
    if (onOrOff) belt2.set(Value.kOn);
    else {
        belt2.set(Value.kOff);
    }
}


public boolean getLaser1State(){
    return !(laser1.get());
}

public boolean getLaser2State(){
    return !(laser2.get());
}

}
