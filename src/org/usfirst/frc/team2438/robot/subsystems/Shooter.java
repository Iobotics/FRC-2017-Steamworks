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
	
	private static double shooterRPM = 3100.0;
	
	private static double kF = 0.045;
	private static double kP = 0.033;
	private static double kI = 0.0;
	private static double kD = 0.0;
	
	private static final int ENCODER_TICKS_PER_REV = 1024;
	
	public void init(){
		_shooter = new CANTalon(RobotMap.shooterTalon);
		
		_shooter.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		_shooter.configEncoderCodesPerRev(ENCODER_TICKS_PER_REV);
		
		_shooter.setPosition(0.0);
		
		_shooter.configNominalOutputVoltage(+0.0f, -0.0f);
		_shooter.configPeakOutputVoltage(+12.0f, -12.0f);
		_shooter.setProfile(1);
    	_shooter.setF(kF);
    	_shooter.setP(kP);
    	_shooter.setI(kI);
    	_shooter.setD(kD);
    	
    	_shooter.changeControlMode(TalonControlMode.Speed);
	}
	
    public void initDefaultCommand() { }
    
    public void runShooter() {
    	_shooter.set(Shooter.shooterRPM);
    }
    
    public void stopShooter() {
    	_shooter.set(0.0);
    }
    
    public void resetShooter() {
    	_shooter.setPosition(0.0);
    }
    
    public static void setShooterRPM(double shooterRPM) {
    	Shooter.shooterRPM = shooterRPM;
    }
    
    public static double getShooterRPM() {
    	return Shooter.shooterRPM;
    }
    
    public static void setF(double kF) {
    	Shooter.kF = kF;
    }
    
    public static void setP(double kP) {
    	Shooter.kP = kP;
    }
    
    public static void setI(double kI) {
    	Shooter.kI = kI;
    }

	public static void setD(double kD) {
		Shooter.kD = kD;
	}
	
	public static double getF() {
		return Shooter.kF;
	}
	
	public static double getP() {
		return Shooter.kP;
	}
	
	public static double getI() {
		return Shooter.kI;
	}
	
	public static double getD() {
		return Shooter.kD;
	}
    
    public void debug() {
    	SmartDashboard.putNumber("Ticks", _shooter.getEncPosition());
    	SmartDashboard.putNumber("Power", _shooter.getSpeed());
    	//SmartDashboard.putNumber("Shooter Current", _shooter.getOutputCurrent());
    }
}

