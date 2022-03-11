package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Magazine extends SubsystemBase {

//private CANSparkMax Belt1 = new CANSparkMax(9, MotorType.kBrushless);
//private CANSparkMax Belt2 = new CANSparkMax(8, MotorType.kBrushless);

//private Relay belt1Relay = new Relay(0, Direction.kReverse);
//private Relay belt2Relay = new Relay(1, Direction.kForward);

//private CANSparkMax belt1 = new CANSparkMax(Constants.shooterMotorID, MotorType.kBrushless);

private WPI_TalonSRX upperBelt = new WPI_TalonSRX(Constants.upperBeltID);
private WPI_TalonSRX lowerBelt = new WPI_TalonSRX(Constants.lowerBeltID);

private DigitalInput laser1 = new DigitalInput(Constants.laser1port);
private DigitalInput laser2 = new DigitalInput(Constants.laser2port);

private ColorSensorV3 color1 = new ColorSensorV3(Port.kOnboard);


public Magazine(){
    upperBelt.setInverted(true);
}
 
@Override
public void periodic() {
    //SmartDashboard.putBoolean("Ball in Lower Belt?", laser1.get());
    //SmartDashboard.putBoolean("Ball in Upper Belt?", laser2.get());

    SmartDashboard.putNumber("Lower Belt Status ", upperBelt.get());
    SmartDashboard.putNumber("Upper Belt Status ", lowerBelt.get());
}

public void moveBothBelts(double speed){
    upperBelt.set(speed);
    lowerBelt.set(speed);
}


public void setBelt1(double speed){
    upperBelt.set(speed);
}

public void setBelt2(double speed){
    lowerBelt.set(speed);
}

public boolean getLaser1State(){
    return !(laser1.get());
}

public boolean getLaser2State(){
    return !(laser2.get());
}

}
