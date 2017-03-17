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
		if(lidar.isReady()) {
			byte[] data = lidar.serialGet();
			SmartDashboard.putString("serial_data", Integer.toString(data[1] * 256 + data[0]));
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void end() {
		
	}
	
	protected void interrupted() {
		this.end();
	}

}
