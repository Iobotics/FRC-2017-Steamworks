package org.usfirst.frc.team2438.robot.commands;

/**
 *
 */
public class ReverseShoot extends CommandBase {

    public ReverseShoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() { }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooter.setShooterRPM(-shooter.MAX_RPM - (-oi.getRightStick().getZ() * 125.0));
    	shooter.runShooter();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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