package org.usfirst.frc.team2438.robot.commands.auto;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2438.robot.commands.CommandBase;

/**
 * Drive straight using encoders and gyro
 */
public class AutoDriveStraight extends CommandBase implements PIDOutput, PIDSource {
	
	
	private final double  _distance;
	private PIDController _pid;
	
	private static final double kP = 0.40;
	private static final double kI = 0.0;
	private static final double kD = 0;
	
	private static final double MAX_GYRO = 0.25;
	private static final double kTurn = 0.01;
	private static final double DEFAULT_POWER = 0.35;
	
	/**
	 * @param inches
	 */
	public AutoDriveStraight(double inches) {
    	this(inches, DEFAULT_POWER, -1);
    }
	/**
	 * @param inches
	 * @param power
	 */
	public AutoDriveStraight(double inches, double power) {
    	this(inches, power, -1);
    }
	
	/**
	 * @param inches
	 * @param power
	 * @param timeout
	 */
    public AutoDriveStraight(double inches, double power, double timeout) {
    	requires(drivetrain);
    	requires(navsensor);
    	//_distance = -inches * (12.0 / 13.0);
    	_distance = inches;
    	if(timeout > 0) {
    		this.setTimeout(timeout);
    	}
    	_pid = new PIDController(kP, kI, kD, this, this);
		//_pid.setAbsoluteTolerance(1.0); // 2 inch tolerance //
    	_pid.setTolerance(new PIDController.Tolerance() {
				@Override
				public boolean onTarget() {
					return  Math.abs(_pid.getError()) < 0.5;
				}	
    		});
		//_pid.setToleranceBuffer(2);
    	_pid.setOutputRange(-power, power);
    }

    public void pidWrite(double leftPower) {
    	double gyro = kTurn * navsensor.getGyro();
    	if(gyro > MAX_GYRO) {
    		gyro = MAX_GYRO;
    	} else if(gyro < -MAX_GYRO) {
    		gyro = -MAX_GYRO;
    	}
    	
    	double rightPower = leftPower + gyro; 
    	leftPower = leftPower - gyro;
    	
    	drivetrain.setTank(leftPower, rightPower);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putData("DrivePID", _pid);
    	drivetrain.setLeftEncoderDistance(0.0);
    	navsensor.zeroGyro();
    	_pid.setSetpoint(_distance);
    	_pid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("drive-auto-error", _pid.getError());
    	SmartDashboard.putBoolean("drive-auto-ontarget", _pid.onTarget());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return _pid.onTarget() || this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	_pid.disable();
    	drivetrain.setTank(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		throw new RuntimeException("Not Implemented");
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		return drivetrain.getLeftEncoderDistance();
	}
}
