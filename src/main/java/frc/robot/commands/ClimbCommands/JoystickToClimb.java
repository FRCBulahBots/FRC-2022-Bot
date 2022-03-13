package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.ProtoClimb;

public class JoystickToClimb extends CommandBase {
    
    private ProtoClimb climb;
    private double speed;

    public JoystickToClimb(ProtoClimb climb, double speed) {
        this.climb = climb;
        this.speed = speed;
        addRequirements(climb);
    }

    @Override
    public void initialize() {
        climb.setClimbState(speed);
    }

    @Override
    public void end(boolean interrupted) { 
        climb.setClimbState(0);
    }

}
