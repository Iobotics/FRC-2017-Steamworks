package org.usfirst.frc.team2438.robot;

import org.usfirst.frc.team2438.robot.commands.CalibrateNavigationSensor;
import org.usfirst.frc.team2438.robot.commands.CommandBase;
import org.usfirst.frc.team2438.robot.commands.auto.AutoDriveAndShoot;
import org.usfirst.frc.team2438.robot.commands.auto.AutoDriveStraight;
import org.usfirst.frc.team2438.robot.commands.auto.AutoGearAndLine;
import org.usfirst.frc.team2438.robot.commands.auto.AutoGearAndShoot;
import org.usfirst.frc.team2438.robot.commands.auto.AutoGearShootAndLine;
import org.usfirst.frc.team2438.robot.commands.auto.AutoPlaceGear;
import org.usfirst.frc.team2438.robot.commands.auto.AutoPlaceGear.GearPosition;
import org.usfirst.frc.team2438.robot.commands.auto.AutoTurn;
import org.usfirst.frc.team2438.robot.subsystems.Shooter;
import org.usfirst.frc.team2438.robot.util.Utility;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot2017 extends IterativeRobot {
	Command _autoCommand;
	PowerDistributionPanel _pdp;
	Shooter 			   _shooter;
	Preferences            _prefs;

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
		
		int autonum = _prefs.getInt("auto-program-number", 0);
    	SmartDashboard.putNumber("auto-num", autonum);
    	
    	this.debugStuff();
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
		CommandBase.drivetrain.setLeftEncoderDistance(0.0);
		CommandBase.drivetrain.setRightEncoderDistance(0.0);
		int autonum = _prefs.getInt("auto-program-number", 0);
    	SmartDashboard.putNumber("auto-num", autonum);
    	// pick auto command via program number //
    	switch(autonum) {
    		case 0: _autoCommand = new PrintCommand("Nihilism: Never Not Nothing"); break;
    		case 1: _autoCommand = new AutoDriveStraight(290); break;
    		case 2: _autoCommand = new AutoPlaceGear(GearPosition.CENTER); break;
    		case 3: _autoCommand = new AutoGearAndLine(GearPosition.CENTER); break;
    		case 4: _autoCommand = new AutoDriveAndShoot(); break;
    		case 5: _autoCommand = new AutoGearAndLine(GearPosition.RIGHT); break;
    		case 6: _autoCommand = new AutoDriveStraight(266); break;
    		case 7: _autoCommand = new AutoGearAndShoot(GearPosition.LEFT); break;
    		case 8: _autoCommand = new AutoGearShootAndLine(); break;
    		case 9: _autoCommand = new AutoPlaceGear(GearPosition.RIGHT); break;
    		case 10: _autoCommand = new AutoTurn(30); break;
    		default: _autoCommand = null; break;
    	}
    	if(_autoCommand != null) _autoCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		
		this.debugStuff();
	}
	
	@Override
	public void teleopInit() {
		System.out.println("hello");
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (_autoCommand != null) _autoCommand.cancel();
		
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
		CommandBase.drivetrain.debug();
		CommandBase.shooter.debug();
		CommandBase.agitator.debug();
		CommandBase.intake.debug();
		CommandBase.winch.debug();
		CommandBase.navsensor.debug();
	}
	
	/**
	 * Display robot preferences
	 */
	public void displayPreferences() {
		_shooter = CommandBase.shooter;
		
		_prefs.putDouble("Shooter RPM", _shooter.getShooterRPM());
		_prefs.putDouble("iZone", _shooter.getIZone());
		_prefs.putDouble("kF", _shooter.getF());
		_prefs.putDouble("kP", _shooter.getP());
		_prefs.putDouble("kI", _shooter.getI());
		_prefs.putDouble("kD", _shooter.getD());
	}
	
	/**
	 * Set robot preferences
	 */
	public void setPreferences() {
		double rpm = _prefs.getDouble("Shooter RPM", _shooter.getShooterRPM());
		int iZone = _prefs.getInt("iZone", _shooter.getIZone());
		double kF = _prefs.getDouble("kF", _shooter.getF());
		double kP = _prefs.getDouble("kP", _shooter.getP());
		double kI = _prefs.getDouble("kI", _shooter.getI());
		double kD = _prefs.getDouble("kD", _shooter.getD());
		
		rpm = Utility.window(rpm, 0, _shooter.MAX_RPM);
		iZone = Utility.window(iZone, 0, _shooter.MAX_IZONE);
		kF = Utility.window(kF, 0, _shooter.MAX_KF);
		kP = Utility.window(kP, 0, _shooter.MAX_KP);
		kI = Utility.window(kI, 0, _shooter.MAX_KI);
		kD = Utility.window(kD, 0, _shooter.MAX_KD);
		
		_shooter.setShooterRPM(rpm);
		_shooter.setIZone(iZone);
		_shooter.setF(kF);
		_shooter.setP(kP);
		_shooter.setI(kI);
		_shooter.setD(kD);
	}
}

