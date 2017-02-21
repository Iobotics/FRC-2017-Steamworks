package org.usfirst.frc.team2438.robot.subsystems;

import org.usfirst.frc.team2438.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Intake
 */
public class Intake extends Subsystem {
	
	private CANTalon _left;
	private CANTalon _right;

	public void init(){
		_left = new CANTalon(RobotMap.intakeLeft);
		_left.changeControlMode(TalonControlMode.Current);
		_left.set(0.0);
		
		_right = new CANTalon(RobotMap.intakeRight);
		_right.changeControlMode(TalonControlMode.Current);
		_right.set(0.0);
		
		_left.setF(0.04);
		_right.setF(0.05);
		_left.setP(0.22);
		_right.setP(0.1);//22);
	}

    public void initDefaultCommand() { }
    
    public void setPower(double power) {
    	_left.set(power);
    	_right.set(-power);
    }
    
    public void debug() {
    	SmartDashboard.putNumber("Left intake current", _left.getOutputCurrent());
    	SmartDashboard.putNumber("Right intake current", _right.getOutputCurrent());
    	SmartDashboard.putNumber("Left intake voltage", _left.getOutputVoltage());
    	SmartDashboard.putNumber("Right intake voltage", _right.getOutputVoltage());
    }
}

