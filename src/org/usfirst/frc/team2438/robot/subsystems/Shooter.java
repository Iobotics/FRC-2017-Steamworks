package org.usfirst.frc.team2438.robot.subsystems;

import org.usfirst.frc.team2438.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Shooter
 */
public class Shooter extends Subsystem {

	private CANTalon _shooter;
	
	public void init(){
		_shooter = new CANTalon(RobotMap.shooterTalon);
	}
	
    public void initDefaultCommand() { }
    
    public void runShooter() {
    	_shooter.set(1.0); //TODO - Change shooter power
    }
    
    public void stopShooter() {
    	_shooter.set(0.0);
    }
}

