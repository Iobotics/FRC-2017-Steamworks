package org.usfirst.frc.team2438.robot.commands.auto;

import org.usfirst.frc.team2438.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Shoots ball
 */
public class AutoShootBall extends CommandBase {
    
	/**
	 * Shoots balls for specified amount of seconds
	 * @param timeout
	 */
    public AutoShootBall(double timeout) {
    	requires(shooter);
    	
    	if(timeout > 0) {
    		this.setTimeout(timeout);
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shooter.resetShooter();
    	//shooter.runShooter();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Shooter RPM", shooter.getShooterRPM());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	shooter.stopShooter();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
