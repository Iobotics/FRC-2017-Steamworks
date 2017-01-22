package org.usfirst.frc.team2438.robot.commands;

//import org.usfirst.frc.team2438.commands.CommandBase;

public class ActivateAgitator extends CommandBase {
	
	public ActivateAgitator() {
		this.requires(agitator);
	}
	
	protected void initialize() { }

	protected void execute(){
		agitator.runAgitator();
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		agitator.stopAgitator();
	}
	
	protected void interrupted() {
		this.end();
	}

}
