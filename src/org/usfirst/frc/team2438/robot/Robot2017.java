package org.usfirst.frc.team2438.robot;

import org.usfirst.frc.team2438.robot.commands.CommandBase;
import org.usfirst.frc.team2438.robot.subsystems.Shooter;

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
    	//this.displayPreferences();
        
    	CommandBase.init();
		
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
		//CommandBase.shooter.resetShooter();
		//this.setPreferences();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		//CommandBase.shooter.debug();
		//CommandBase.agitator.debug();
		CommandBase.intake.debug();
		SmartDashboard.putNumber("Battery voltage", DriverStation.getInstance().getBatteryVoltage());
		SmartDashboard.putNumber("Total current", _pdp.getTotalCurrent());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	/**
	 * Display robot preferences
	 */
	public void displayPreferences() {
		_prefs.putDouble("Shooter RPM", Shooter.getShooterRPM());
		_prefs.putDouble("kF", Shooter.getF());
		_prefs.putDouble("kP", Shooter.getP());
		_prefs.putDouble("kI", Shooter.getI());
		_prefs.putDouble("kD", Shooter.getD());
	}
	
	/**
	 * Set robot preferences
	 */
	public void setPreferences() {
		Shooter.setShooterRPM(_prefs.getDouble("Shooter RPM", Shooter.getShooterRPM()));
		Shooter.setF(_prefs.getDouble("kF", Shooter.getF()));
		Shooter.setP(_prefs.getDouble("kP", Shooter.getP()));
		Shooter.setI(_prefs.getDouble("kI", Shooter.getI()));
		Shooter.setD(_prefs.getDouble("kD", Shooter.getD()));
	}
}

