package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climb extends SubsystemBase{

    private CANSparkMax climb = new CANSparkMax(Constants.armMotorID, MotorType.kBrushless);

    public Climb(){
        climb.setIdleMode(IdleMode.kBrake);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Climb encoder value", this.getClimberEncoder());   
    }
    public void setClimbState(double speedToSet){
        climb.set(speedToSet);
    }

    public void setClimberEncoder(double encoderValueToSet){
        climb.getEncoder().setPosition(encoderValueToSet);
    }

    public double getClimberEncoder(){
        return climb.getEncoder().getPosition();
    }

}
