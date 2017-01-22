package org.usfirst.frc.team2438.robot.subsystems;

import org.usfirst.frc.team2438.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Intake
 */
public class Intake extends Subsystem {
	
	private CANTalon _intake;

	public void init(){
		_intake = new CANTalon(RobotMap.intake);
		_intake.setInverted(true);
	}

    public void initDefaultCommand() { }
    
    public void setPower(double power) {
    	_intake.set(power);
    }
}

