package org.usfirst.frc.team2438.robot.subsystems;

import org.usfirst.frc.team2438.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

/**
 * Agitator
 */
public class Agitator extends Subsystem {

    private CANTalon _agitator;

	private static final int ENCODER_TICKS_PER_REV = 1024;
	
	public void init(){
		_agitator = new CANTalon(RobotMap.agitatorTalon);
		
		_agitator.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		_agitator.changeControlMode(TalonControlMode.Speed);
		_agitator.configEncoderCodesPerRev(ENCODER_TICKS_PER_REV);
		
		_agitator.setPosition(0.0);
		
		// TODO - Tune
		_agitator.setIZone(0);
		_agitator.setF(0.4);
		_agitator.setP(0.0);
		_agitator.setI(0.0);
		_agitator.setD(0.0);
		
		_agitator.set(0.0);
	}
	
	public void initDefaultCommand() { }
	
	public void setAgitatorRPM(double rpm){
		_agitator.set(rpm);
	}
	
	public void debug() {
		SmartDashboard.putNumber("Agitator RPM", _agitator.getSpeed());
		SmartDashboard.putNumber("Agitator Current", _agitator.getOutputCurrent());
		SmartDashboard.putNumber("Agitator Voltage", _agitator.getOutputVoltage());
	}
}

