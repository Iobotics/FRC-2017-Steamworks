package org.usfirst.frc.team2438.robot.subsystems;

import org.usfirst.frc.team2438.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Shooter
 */
public class Shooter extends Subsystem {

	private CANTalon _shooter;
	
	private static final int ENCODER_TICKS_PER_REV = 1024;
	
	public void init(){
		_shooter = new CANTalon(RobotMap.shooterTalon);
		
		_shooter.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		_shooter.configEncoderCodesPerRev(ENCODER_TICKS_PER_REV);
		
		//_shooter.reverseSensor(true);
		_shooter.setInverted(true);
		
		_shooter.setPosition(0.0);
		
		_shooter.configNominalOutputVoltage(+0.0f, -0.0f);
		_shooter.configPeakOutputVoltage(+12.0f, -12.0f);
		_shooter.setProfile(1);
    	_shooter.setF(0.03);
    	_shooter.setP(0.18);
    	_shooter.setI(0);
    	_shooter.setD(0);
    	
    	_shooter.changeControlMode(TalonControlMode.Speed);
	}
	
    public void initDefaultCommand() { }
    
    public void runShooter() {
    	_shooter.set(3100.0); //TODO - Change shooter power
    }
    
    public void stopShooter() {
    	_shooter.set(0.0);
    }
    
    public void resetShooter() {
    	_shooter.setPosition(0.0);
    }
    
    public void debug() {
    	SmartDashboard.putNumber("Ticks", _shooter.getEncPosition());
    	SmartDashboard.putNumber("Power", _shooter.getSpeed());
    }
}

