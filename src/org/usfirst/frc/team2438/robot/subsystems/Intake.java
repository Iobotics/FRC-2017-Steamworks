package org.usfirst.frc.team2438.robot.subsystems;

import org.usfirst.frc.team2438.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	
	private CANTalon _intake;

	public void init(){
		_intake = new CANTalon(RobotMap.intake);
		_intake.setInverted(true);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }
    
    public void setPower(double power) {
    	_intake.set(power);
    }
}

