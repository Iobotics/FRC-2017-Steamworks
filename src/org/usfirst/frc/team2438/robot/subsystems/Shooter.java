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
	
	private double shooterRPM = 2350.0;
	
	private int iZone = 0;
	private double kF = 0.035;
	private double kP = 0.045;
	private double kI = 0.0;
	private double kD = 0.0;
	
	private final double MAX_RPM = 3100.0;
	
	private final int MAX_IZONE = (int) Math.round(iZone * 1.2);
	private final double MAX_KF = kF * 1.5;
	private final double MAX_KP = kP * 1.2;
	private final double MAX_KI = kI * 1.2;
	private final double MAX_KD = kD * 1.2;
	
	private static final int ENCODER_TICKS_PER_REV = 1024;
	
	public void init(){
		_shooter = new CANTalon(RobotMap.shooterTalon);
		
		_shooter.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		_shooter.changeControlMode(TalonControlMode.Speed);
		_shooter.configEncoderCodesPerRev(ENCODER_TICKS_PER_REV);
		
		_shooter.setPosition(0.0);
		
		_shooter.configNominalOutputVoltage(+0.0f, -0.0f);
		_shooter.configPeakOutputVoltage(+12.0f, -12.0f);
		
		_shooter.setProfile(1);
		_shooter.setIZone(iZone);
    	_shooter.setF(kF);
    	_shooter.setP(kP);
    	_shooter.setI(kI);
    	_shooter.setD(kD);
	}
	
    public void initDefaultCommand() { }
    
    public void runShooter() {
    	_shooter.set(this.shooterRPM);
    }
    
    public void stopShooter() {
    	_shooter.set(0.0);
    }
    
    public void resetShooter() {
    	_shooter.setPosition(0.0);
    }
    
    public void setShooterRPM(double shooterRPM) {
    	this.shooterRPM = shooterRPM;
    }
    
    public void setIZone(int iZone) {
		this.iZone = iZone;
		_shooter.setIZone(this.iZone);
	}
    
    public void setF(double kF) {
    	this.kF = kF;
    	_shooter.setF(this.kF);
    }
    
    public void setP(double kP) {
    	this.kP = kP;
    	_shooter.setP(this.kP);
    }
    
    public void setI(double kI) {
    	this.kI = kI;
    	_shooter.setI(this.kI);
    }

	public void setD(double kD) {
		this.kD = kD;
		_shooter.setD(this.kD);
	}
	
	public double getShooterRPM() {
    	return this.shooterRPM;
    }
	
	public int getIZone() {
		return this.iZone;
	}
	
	public double getF() {
		return this.kF;
	}
	
	public double getP() {
		return this.kP;
	}
	
	public double getI() {
		return this.kI;
	}
	
	public double getD() {
		return this.kD;
	}
	
	public double getMaxRPM() {
		return this.MAX_RPM;
	}
	
	public int getMaxIZone() {
		return this.MAX_IZONE;
	}
	
	public double getMaxF() {
		return this.MAX_KF;
	}
	
	public double getMaxP() {
		return this.MAX_KP;
	}
	
	public double getMaxI() {
		return this.MAX_KI;
	}
	
	public double getMaxD() {
		return this.MAX_KD;
	}
	
    public void debug() {
    	SmartDashboard.putNumber("Ticks", _shooter.getEncPosition());
    	SmartDashboard.putNumber("RPM", _shooter.getSpeed());
    }
}

