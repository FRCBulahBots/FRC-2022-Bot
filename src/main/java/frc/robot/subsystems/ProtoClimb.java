package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


//Potential Climbing Subsystem.
//May or may not be used in final version but a "prototype" of it.
//hence the name.
public class ProtoClimb extends SubsystemBase{

    private WPI_TalonSRX climb = new WPI_TalonSRX(Constants.climbID);

    public ProtoClimb(){
        climb.setNeutralMode(NeutralMode.Brake);
    }

    public void setClimbState(double speedToSet){
        climb.set(speedToSet);
    }

}
