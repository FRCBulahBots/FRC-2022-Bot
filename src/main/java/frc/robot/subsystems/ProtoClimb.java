package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ProtoClimb extends SubsystemBase{

    private WPI_TalonSRX climb = new WPI_TalonSRX(Constants.climbID);

    public ProtoClimb(){
        climb.setNeutralMode(NeutralMode.Brake);
    }

    public void setClimbState(double speed){
        climb.set(speed);
    }

}
