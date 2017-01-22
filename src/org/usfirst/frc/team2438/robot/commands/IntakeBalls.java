package org.usfirst.frc.team2438.robot.commands;

//import org.usfirst.frc.team2438.robot.util.Utility;

/**
 * Command to intake balls
 */
public class IntakeBalls extends CommandBase {
	
    public IntakeBalls() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.requires(intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	intake.setPower(0.5);
    	//intake.setPower(Utility.limit(oi.getRightStick().getRawAxis(2)));
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
