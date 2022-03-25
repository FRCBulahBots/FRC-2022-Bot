package frc.robot.commands.ClimbCommands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.ProtoClimb;

public class JoystickToClimb extends CommandBase {
    
    private ProtoClimb climb;
    private double encoderValue;
    private BooleanSupplier upButton, downButton;

    public JoystickToClimb(ProtoClimb climb, Double encoderMaxValue, BooleanSupplier upButton, BooleanSupplier downButton) {
        this.climb = climb;
        this.upButton = upButton;
        this.downButton = downButton;
        this.encoderValue = encoderMaxValue;
        addRequirements(climb);
    }

    @Override
    public void initialize() {
        climb.setClimberEncoder(0);
    }

    @Override
    public void execute() {
        if (upButton.getAsBoolean() && climb.getClimberEncoder() < encoderValue){
            climb.setClimbState(0.5);
            return;
        }
        if (downButton.getAsBoolean() && climb.getClimberEncoder() > 0){
            climb.setClimbState(-0.5);
            return;
        }
        climb.setClimbState(0);
    }

}
