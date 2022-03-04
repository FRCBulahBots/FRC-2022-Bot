package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


//Potential Climbing Subsystem.
//May or may not be used in final version but a "prototype" of it.
//hence the name.
public class ProtoClimb extends SubsystemBase{

    private DoubleSolenoid climb1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
    private DoubleSolenoid climb2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);

    private Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);

    public ProtoClimb(){
        compressor.enableDigital();
    }

    public void setClimbState(boolean stateToSet){
        if(stateToSet){
            climb1.set(Value.kForward);
            climb2.set(Value.kForward);
        } else {
            climb1.set(Value.kReverse);
            climb2.set(Value.kReverse);
        }
    }

}
