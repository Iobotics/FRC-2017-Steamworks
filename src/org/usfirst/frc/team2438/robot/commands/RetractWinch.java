package org.usfirst.frc.team2438.robot.commands;

import org.usfirst.frc.team2438.robot.commands.CommandBase;

/**
 * Retract the winch
 */
public class RetractWinch extends CommandBase {

    public RetractWinch() {
        requires(winch);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	winch.setPower(-1.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	winch.setPower(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
