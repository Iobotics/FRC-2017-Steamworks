package org.usfirst.frc.team2438.robot.commands.auto;

import org.usfirst.frc.team2438.robot.commands.CommandBase;

/**
 * Drive using timer
 */
public class AutoDriveTimed extends CommandBase {
	
	public static final double DEFAULT_POWER = 0.35;
	
	private final double _leftPower;
	private final double _rightPower;
		
	/**
	 * @param timeout
	 */
	public AutoDriveTimed(double timeout) {
    	this(timeout, DEFAULT_POWER, DEFAULT_POWER);
    }

	/**
	 * @param timeout
	 * @param power
	 */
	public AutoDriveTimed(double timeout, double power) {
    	this(timeout, power, power);
    }
	
	/**
	 * @param timeout
	 * @param leftPower
	 * @param rightPower
	 */
	public AutoDriveTimed(double timeout, double leftPower, double rightPower) {
    	requires(drivetrain);
    	//requires(navsensor);
    	_leftPower  = leftPower;
    	_rightPower = rightPower;
    	this.setTimeout(timeout);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	drivetrain.setTank(_leftPower, _rightPower);
    }

	protected void execute() {	
	}
	
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	drivetrain.setTank(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
