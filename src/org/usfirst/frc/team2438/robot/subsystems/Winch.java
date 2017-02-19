package org.usfirst.frc.team2438.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2438.robot.RobotMap;

public class Winch extends Subsystem{

	private CANTalon _winch;
	
	public void init(){
		_winch = new CANTalon(RobotMap.winchTalon);
	}
	
	public void initDefaultCommand() { }
	
	public void setPower(double power){
		_winch.set(power);
	}
}
