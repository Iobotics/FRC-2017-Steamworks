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
	
	private CANTalon _intake;
	private CANTalon _intakeSlave;

	public void init(){
		_intake = new CANTalon(RobotMap.intakeMain);
		_intake.changeControlMode(TalonControlMode.Current);
		_intake.set(0.0);
		
		_intakeSlave = new CANTalon(RobotMap.intakeSlave);
		_intakeSlave.changeControlMode(TalonControlMode.Current);
		_intakeSlave.set(0.0);
		//_intakeSlave.setInverted(true);
		
		_intake.setF(0.06);
		_intakeSlave.setF(0.06);
		_intake.setP(0.0);
		_intakeSlave.setP(0.0);
	}

    public void initDefaultCommand() { }
    
    public void setPower(double power) {
    	_intake.set(power);
    	_intakeSlave.set(-power);
    }
    
    public void debug() {
    	SmartDashboard.putNumber("Intake current", _intakeSlave.getOutputCurrent());
    	SmartDashboard.putNumber("Intake PercentVBus", (double)(_intake.getOutputVoltage()/_intake.getBusVoltage()));
    }
}

