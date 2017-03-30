package org.usfirst.frc.team2438.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ReadLIDAR extends CommandBase {
	
	public ReadLIDAR() {
		requires(lidar);
	}
	
	// Called just before this Command runs the first time
    protected void initialize() {
    }
	
	protected void execute() {
		SmartDashboard.putNumber("LIDAR distance", lidar.getDistance());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() { }
	
	protected void interrupted() {
		this.end();
	}
}
