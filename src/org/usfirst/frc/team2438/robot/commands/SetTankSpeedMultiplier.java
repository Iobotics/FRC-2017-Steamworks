package org.usfirst.frc.team2438.robot.commands;

/**
 * Toggles tank speed multiplier
 */
public class SetTankSpeedMultiplier extends CommandBase {
	
	private double multiplier;

    public SetTankSpeedMultiplier() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	multiplier = drivetrain.getMultiplier();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(multiplier != 1.0) {
    		drivetrain.setMultiplier(1.0);
    	} else {
    		drivetrain.setMultiplier(0.7);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}