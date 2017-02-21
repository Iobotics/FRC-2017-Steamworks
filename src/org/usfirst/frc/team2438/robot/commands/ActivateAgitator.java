package org.usfirst.frc.team2438.robot.commands;

public class ActivateAgitator extends CommandBase {
	
	private double agitatorPower;
	
	public ActivateAgitator(double power) {
		this.requires(agitator);
		this.agitatorPower = power;
	}
	
	protected void initialize() { }

	protected void execute(){
		agitator.runAgitator(agitatorPower);
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
