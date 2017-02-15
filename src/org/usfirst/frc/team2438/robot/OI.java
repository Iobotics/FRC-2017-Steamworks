package org.usfirst.frc.team2438.robot;

import org.usfirst.frc.team2438.robot.commands.*;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
//import edu.wpi.first.wpilibj.buttons.InternalButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//private static final int XSTICK_LTRIGGER_AXIS = 2;
	//private static final int XSTICK_RTRIGGER_AXIS = 3;

	// persistent virtual button used to store gunner mode state //
	//private final InternalButton _gunnerModeState = new InternalButton();
	
	// joysticks //
    private final Joystick _lStick = new Joystick(1);
    private final Joystick _rStick = new Joystick(2);
    private final Joystick _xStick = new Joystick(3);
    
    // driver buttons //
    //private final Button _intakeButton = new JoystickButton(_rStick, 4);
    //private final Button _outtakeButton = new JoystickButton(_rStick, 5);
    
    /*private final Button _ballOperateButton   = new JoystickButton(_lStick, 2);
    private final Button _intakeOperateButton = new JoystickButton(_lStick, 5);
    private final Button _auxOperateButton    = new JoystickButton(_lStick, 4);
    
    private final Button _homePositionButton  = new JoystickButton(_rStick, 3);
    private final Button _loadPositionButton  = new JoystickButton(_lStick, 3);
    
    // auto test buttons //
    private final Button _autoTestButton0  = new JoystickButton(_rStick, 7);
    private final Button _autoTestButton1  = new JoystickButton(_rStick, 8);
    private final Button _autoTestButton2  = new JoystickButton(_rStick, 9);
    private final Button _autoTestButton3  = new JoystickButton(_rStick, 10);
    private final Button _autoTestButton4  = new JoystickButton(_rStick, 11);
    private final Button _autoTestButton5  = new JoystickButton(_rStick, 6);*/
    
	//private final Button _agitatorButton = new JoystickButton(_rStick, 3);
	
	// TODO - Assign buttons for winch //
	//private final Button _retractWinchButton = new JoystickButton();
	//private final Button _operateWinchButton = new JoystickButton();
	
	// TODO - Assign buttons for shooter //
	private final Button _shooterButton 	  = new JoystickButton(_rStick, 4);
	private final Button _shooterToggleButton = new JoystickButton(_xStick, 3);
    
    public OI() {
        //_intakeButton.toggleWhenPressed(new IntakeBalls());
        //_outtakeButton.toggleWhenPressed(new OuttakeBalls());

        //_agitatorButton.toggleWhenPressed(new ActivateAgitator());
        
        //_retractWinchButton.whenPressed(new RetractWinch());
        //_operateWinchButton.whenPressed(new OperateWinch());
        
        _shooterButton.whenPressed(new ShootBall());
        _shooterToggleButton.toggleWhenPressed(new ShootBall());
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
    
    /*public Button getBallOperateButton() {
    	return _ballOperateButton;
    }

    public Button getAuxOperateButton() {
    	return _auxOperateButton;
    }
    
    public boolean getGunnerControlEnabled() {
    	return _gunnerModeState.get();
    }
    
    public void setGunnerControlEnabled(boolean enabled) {
    	_gunnerModeState.setPressed(enabled);
    }
    
    public Joystick getGunnerStick() {
    	return _xStick;
    }*/
}