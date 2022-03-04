package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Magazine extends SubsystemBase {

//private CANSparkMax belt1 = new CANSparkMax(Constants.shooterMotorID, MotorType.kBrushless);
//private CANSparkMax belt2 = new CANSparkMax(Constants.armMotorID, MotorType.kBrushless);

private WPI_TalonSRX belt1 = new WPI_TalonSRX(Constants.belt1ID);
private WPI_TalonSRX belt2 = new WPI_TalonSRX(Constants.belt2ID);

private DigitalInput laser1 = new DigitalInput(Constants.laser1port);
private DigitalInput laser2 = new DigitalInput(Constants.laser2port);

private ColorSensorV3 color1 = new ColorSensorV3(Port.kOnboard);


public Magazine(){
    belt1.setInverted(true);
}
 
@Override
public void periodic() {
    SmartDashboard.putBoolean("Ball in Lower Belt?", laser1.get());
    SmartDashboard.putBoolean("Ball in Upper Belt?", laser2.get());

    SmartDashboard.putNumber("Lower Belt Status ", belt1.get());
    SmartDashboard.putNumber("Upper Belt Status ", belt2.get());
}


public void setBelt1(double speed){
    belt1.set(speed);
}

public void setBelt2(double speed){
    belt2.set(speed);
}

public boolean getLaser1State(){
    return !(laser1.get());
}

public boolean getLaser2State(){
    return !(laser2.get());
}

}
