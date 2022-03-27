package frc.robot.commands.ClimbCommands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Climb;

public class JoystickToClimb extends CommandBase {
    
    private Climb climb;
    private double encoderValue;
    private BooleanSupplier upButton, downButton;

    public JoystickToClimb(Climb climb, Double encoderMaxValue, BooleanSupplier upButton, BooleanSupplier downButton) {
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
            climb.setClimbState(0.9);
            return;
        }
        if (downButton.getAsBoolean() && climb.getClimberEncoder() > 0){
            climb.setClimbState(-0.9);
            return;
        }
        climb.setClimbState(0);
    }

}
