package org.usfirst.frc.team2438.robot.commands;

import org.usfirst.frc.team2438.robot.commands.CommandBase;

/**
 * Operates winch
 */
public class OperateWinch extends CommandBase {
	
	private final double WINCH_POWER = 1.0;

    public OperateWinch() {
        requires(winch);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	winch.setPower(WINCH_POWER);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	winch.setPower(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
