package org.usfirst.frc.team2438.robot;

import org.usfirst.frc.team2438.robot.commands.CalibrateNavigationSensor;
import org.usfirst.frc.team2438.robot.commands.CommandBase;
import org.usfirst.frc.team2438.robot.subsystems.Shooter;
import org.usfirst.frc.team2438.robot.util.Utility;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot2017 extends IterativeRobot {
	PowerDistributionPanel _pdp;
	Preferences            _prefs;
	
	Shooter shooter;
	
	final String defaultAuto = "Default";
	final String customAuto = "Auto 1";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		_pdp = new PowerDistributionPanel();
    	
    	_pdp.clearStickyFaults();
    	
    	_prefs = Preferences.getInstance();
        
    	CommandBase.init();
    	new CalibrateNavigationSensor().start();
    	
    	this.displayPreferences();
		
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("Auto 1", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
	}
	
	/**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit() {
    	SmartDashboard.putData(Scheduler.getInstance());
    }
    
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard.
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	@Override
	public void teleopInit() {
		CommandBase.shooter.resetShooter();
		this.setPreferences();
		this.displayPreferences();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		this.debugStuff();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	/**
	 * Subsystem debuggers
	 */
	public void debugStuff() {
		CommandBase.shooter.debug();
		CommandBase.agitator.debug();
		CommandBase.intake.debug();
		CommandBase.winch.debug();
		CommandBase.navsensor.debug();
		
		SmartDashboard.putNumber("Battery voltage", DriverStation.getInstance().getBatteryVoltage());
		SmartDashboard.putNumber("Total current", _pdp.getTotalCurrent());
	}
	
	/**
	 * Display robot preferences
	 */
	public void displayPreferences() {
		shooter = CommandBase.shooter;
		
		_prefs.putDouble("Shooter RPM", shooter.getShooterRPM());
		_prefs.putDouble("iZone", shooter.getIZone());
		_prefs.putDouble("kF", shooter.getF());
		_prefs.putDouble("kP", shooter.getP());
		_prefs.putDouble("kI", shooter.getI());
		_prefs.putDouble("kD", shooter.getD());
	}
	
	/**
	 * Set robot preferences
	 */
	public void setPreferences() {
		double rpm = _prefs.getDouble("Shooter RPM", shooter.getShooterRPM());
		int iZone = _prefs.getInt("iZone", shooter.getIZone());
		double kF = _prefs.getDouble("kF", shooter.getF());
		double kP = _prefs.getDouble("kP", shooter.getP());
		double kI = _prefs.getDouble("kI", shooter.getI());
		double kD = _prefs.getDouble("kD", shooter.getD());
		
		rpm = Utility.window(rpm, 0, shooter.MAX_RPM);
		iZone = Utility.window(iZone, 0, shooter.MAX_IZONE);
		kF = Utility.window(kF, 0, shooter.MAX_KF);
		kP = Utility.window(kP, 0, shooter.MAX_KP);
		kI = Utility.window(kI, 0, shooter.MAX_KI);
		kD = Utility.window(kD, 0, shooter.MAX_KD);
		
		shooter.setShooterRPM(rpm);
		shooter.setIZone(iZone);
		shooter.setF(kF);
		shooter.setP(kP);
		shooter.setI(kI);
		shooter.setD(kD);
	}
}

