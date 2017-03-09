package org.usfirst.frc.team2438.robot.commands.auto;

import org.usfirst.frc.team2438.robot.commands.CommandBase;

/**
 * Uses encoder for dead reckoning
 */
public class AutoDriveDeadReckon extends CommandBase {
	
	public static final double DEFAULT_POWER = 0.35;
	
	private final double _leftInches;
	private final double _leftPower;
	private final double _rightPower;
		
	/**
	 * @param leftInches
	 */
	public AutoDriveDeadReckon(double leftInches) {
    	this(leftInches, DEFAULT_POWER, DEFAULT_POWER,  -1);
    }
	
	/**
	 * @param leftInches
	 * @param leftPower
	 * @param rightPower
	 */
	public AutoDriveDeadReckon(double leftInches, double leftPower, double rightPower) {
    	this(leftInches, leftPower, rightPower,  -1);
    }
	
	/**
	 * @param leftInches
	 * @param timeout
	 */
	public AutoDriveDeadReckon(double leftInches, double timeout) {
    	this(leftInches, DEFAULT_POWER, DEFAULT_POWER, timeout);
    }
	
	/**
	 * @param leftInches
	 * @param leftPower
	 * @param rightPower
	 * @param timeout
	 */
    public AutoDriveDeadReckon(double leftInches, double leftPower, double rightPower, double timeout) {
    	requires(drivetrain);
    	//requires(navsensor);
    	_leftInches = leftInches;
    	_leftPower  = leftPower;
    	_rightPower = rightPower;
    	if(timeout > 0) {
    		this.setTimeout(timeout);
    	}
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	drivetrain.setLeftEncoderDistance(0.0);
    	drivetrain.setTank(_leftPower, _rightPower);
    }

	protected void execute() {	
	}
	
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return drivetrain.getLeftEncoderDistance() >= _leftInches || this.isTimedOut();
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
