package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.ProtoClimb;

public class JoystickToClimb extends CommandBase {
    
    private ProtoClimb climb;
    private double speed, encoderValue;

    public JoystickToClimb(ProtoClimb climb, double speed, double encoderValue) {
        this.climb = climb;
        this.speed = speed;
        addRequirements(climb);
    }

    @Override
    public void initialize() {
        climb.setClimberEncoder(0);

        climb.setClimbState(speed);
    }

    @Override
    public void end(boolean interrupted) { 
        climb.setClimbState(0);
    }

    @Override
    public boolean isFinished() {
        return climb.getClimberEncoder() >= encoderValue;
    }

}
