package org.usfirst.frc.team2438.robot.commands;

/**
 * Command to outtake balls
 */
public class OuttakeBalls extends CommandBase {
	
	private final double MAX_POWER = -16.0;
	
    public OuttakeBalls() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.requires(intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() { }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Outtake throttle range between 0.0A and -16.0A
    	intake.setPower((1.0 - oi.getLeftStick().getZ()) * (MAX_POWER / 2));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	intake.setPower(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
