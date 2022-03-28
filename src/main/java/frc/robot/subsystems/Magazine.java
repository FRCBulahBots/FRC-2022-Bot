package frc.robot.subsystems;

import edu.wpi.first.hal.MatchInfoData;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Magazine extends SubsystemBase {

private WPI_TalonSRX belt1 = new WPI_TalonSRX(Constants.belt1ID);
private WPI_TalonSRX belt2 = new WPI_TalonSRX(Constants.belt2ID);

private DigitalInput laser1 = new DigitalInput(Constants.laser1port);
private DigitalInput laser2 = new DigitalInput(Constants.laser2port);
private Debouncer debouncer = new Debouncer(0.1, DebounceType.kBoth);

private AddressableLED leds = new AddressableLED(0);
private AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(60);
int red, blue, green;

public Magazine(){
    belt1.setInverted(true);
}
 
@Override
public void periodic() {
    SmartDashboard.putBoolean("Ball in Lower Belt?", laser1.get());
    SmartDashboard.putBoolean("Ball in Upper Belt?", laser2.get());

    SmartDashboard.putNumber("Lower Belt Status:", belt1.get());
    SmartDashboard.putNumber("Upper Belt Status:", belt2.get());

    /*
    Alliance alliance = DriverStation.getAlliance();

    switch(alliance){
        case Red: 
            red = 100;
            green = 0;
            blue = 0;
            break;
        case Blue:
            red = 0;
            green = 0;
            blue = 100;
            break;
        default:
            break;
    }

    for (var i = 0; i < ledBuffer.getLength(); i++){
        ledBuffer.setRGB(i, red, green, blue);
    }

    leds.setData(ledBuffer);

    */
}

public void setBothBelts(double speed){
    belt1.set(speed);
    belt2.set(speed);
}


public void setBelt1(double speed){
    belt1.set(speed);
}

public void setBelt2(double speed){
    belt2.set(speed);
}

public boolean getLaser1State(){
    return debouncer.calculate(!laser1.get());
}

public boolean getLaser2State(){
    return debouncer.calculate(!(laser2.get()));
}

}
