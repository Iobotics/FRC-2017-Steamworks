package org.usfirst.frc.team2438.robot;

import org.usfirst.frc.team2438.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {	
	// joysticks //
    private final Joystick _lStick = new Joystick(1);
    private final Joystick _rStick = new Joystick(2);
    private final Joystick _xStick = new Joystick(3);
    
    // driver buttons //
    private final Button _intakeButton = new JoystickButton(_rStick, 1);
    private final Button _outtakeButton = new JoystickButton(_lStick, 4);
    
    // auto test buttons //
    /*private final Button _autoTestButton0  = new JoystickButton(_rStick, 7);
    private final Button _autoTestButton1  = new JoystickButton(_rStick, 8);
    private final Button _autoTestButton2  = new JoystickButton(_rStick, 9);
    private final Button _autoTestButton3  = new JoystickButton(_rStick, 10);
    private final Button _autoTestButton4  = new JoystickButton(_rStick, 11);
    private final Button _autoTestButton5  = new JoystickButton(_rStick, 6);*/
    
	private final Button _agitatorButton = new JoystickButton(_lStick, 1);
	private final Button _agitatorRevButton = new JoystickButton(_lStick, 5);
	
	// TODO - Assign buttons for winch //
	private final Button _retractWinchButton = new JoystickButton(_lStick, 10);
	private final Button _operateWinchButton = new JoystickButton(_lStick, 3);
	
	private final Button _shooterButton = new JoystickButton(_rStick, 3);
	private final Button _shooterRevButton = new JoystickButton(_rStick, 2);
    
    //private final Button _slowDriveButton = new JoystickButton(_rStick, 3);
    
    public OI() {
        _intakeButton.whileHeld(new IntakeBalls());
        //_outtakeButton.toggleWhenPressed(new OuttakeBalls());

        _agitatorButton.whileHeld(new ActivateAgitator(-100.0));
        _agitatorRevButton.toggleWhenPressed(new ActivateAgitator(100.0));
        
        _retractWinchButton.whileHeld(new RetractWinch());
        _operateWinchButton.whileHeld(new OperateWinch());
        
        _shooterButton.whileHeld(new ShootBall());
        _shooterRevButton.whileHeld(new ReverseShooter());
        
        //_slowDriveButton.whenPressed(new OperateSlowTankDrive(0.7));
    }
    
    public Joystick getLeftStick()  {
        return _lStick;
    }
    
    public Joystick getRightStick() {
        return _rStick;
    }
    
    public Joystick getXStick() {
    	return _xStick;
    }
}