package org.usfirst.frc.team2438.robot.subsystems;

import org.usfirst.frc.team2438.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.CANTalon;

/**
 * Agitator
 */
public class Agitator extends Subsystem {

    private CANTalon _agitator;
	
    private final double power = 1.0; 
	
	public void init(){
		_agitator = new CANTalon(RobotMap.agitatorTalon);
	}
	
	public void initDefaultCommand() { }
	
	public void runAgitator(){
		_agitator.set(power);
	}
	
	public void stopAgitator() {
		_agitator.set(0.0);
	}
	
	public double getCurrent() {
		return _agitator.getOutputCurrent();
	}
	
	public void debug() {
		SmartDashboard.putNumber("Agitator Current", _agitator.getOutputCurrent());
	}
}

