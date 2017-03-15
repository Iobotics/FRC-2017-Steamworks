package org.usfirst.frc.team2438.robot.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2438.robot.RobotMap;

public class Winch extends Subsystem{

	private CANTalon _winch;
	
	public void init(){
		_winch = new CANTalon(RobotMap.winchTalon);
		_winch.changeControlMode(TalonControlMode.PercentVbus);
	}
	
	public void initDefaultCommand() { }
	
	public void setPower(double power){
		_winch.set(power);
	}
	
	public void debug() {
		SmartDashboard.putNumber("Winch power", _winch.get());
		SmartDashboard.putNumber("Winch current", _winch.getOutputCurrent());
		SmartDashboard.putNumber("Winch voltage", _winch.getOutputVoltage());
	}
}
