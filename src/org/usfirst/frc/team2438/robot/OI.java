package org.usfirst.frc.team2438.robot;

import org.usfirst.frc.team2438.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
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
    //private final Joystick _xStick = new Joystick(3);
    
    // driver buttons //
    /*private final Button _intakeButton = new JoystickButton(_rStick, 1);
    private final Button _outakeButton = new JoystickButton(_lStick, 1);
    
    private final Button _ballOperateButton   = new JoystickButton(_lStick, 2);
    private final Button _intakeOperateButton = new JoystickButton(_lStick, 5);
    private final Button _auxOperateButton    = new JoystickButton(_lStick, 4);
    
    private final Button _homePositionButton  = new JoystickButton(_rStick, 3);
    private final Button _loadPositionButton  = new JoystickButton(_lStick, 3);
    
    private final Button _gunnerLowButton  = new JoystickButton(_rStick, 4);
    private final Button _gunnerMidButton  = new JoystickButton(_rStick, 2);
    private final Button _gunnerHighButton = new JoystickButton(_rStick, 5);
    
    // gunner buttons //
    private final Button _gunnerFastButton = new ConditionalButton(_gunnerModeState, new JoystickButton(_xStick, 3));
    private final Button _gunnerSlowButton = new ConditionalButton(_gunnerModeState, new JoystickButton(_xStick, 4));
    private final Button _gunnerSpinButton = new ConditionalButton(_gunnerModeState, 
    			new JoystickAxisThresholdButton(_xStick, XSTICK_LTRIGGER_AXIS, 0.25, 1)
    		);
    private final Button _gunnerFireButton = new ConditionalButton(_gunnerModeState, 
			new JoystickAxisThresholdButton(_xStick, XSTICK_RTRIGGER_AXIS, 0.25, 1)
		);
    private final Button _gunnerHomeButton   = new ConditionalButton(_gunnerModeState, new JoystickButton(_xStick, 5));
    private final Button _gunnerSuckButton   = new ConditionalButton(_gunnerModeState, new JoystickButton(_xStick, 6));
    private final Button _gunnerDriveButton  = new ConditionalButton(_gunnerModeState, new JoystickButton(_xStick, 1));
    private final Button _gunnerVisionButton = new ConditionalButton(_gunnerModeState, new JoystickButton(_xStick, 2));
    
    
    // auto test buttons //
    private final Button _autoTestButton0  = new JoystickButton(_rStick, 7);
    private final Button _autoTestButton1  = new JoystickButton(_rStick, 8);
    private final Button _autoTestButton2  = new JoystickButton(_rStick, 9);
    private final Button _autoTestButton3  = new JoystickButton(_rStick, 10);
    private final Button _autoTestButton4  = new JoystickButton(_rStick, 11);
    private final Button _autoTestButton5  = new JoystickButton(_rStick, 6);*/
    
    private final Button _intakeButton   = new JoystickButton(_rStick, 3);
    
    public OI() {
        _intakeButton.toggleWhenPressed(new IntakeBalls());
    }
    
    public Joystick getLeftStick()  {
        return _lStick;
    }
    
    public Joystick getRightStick() {
        return _rStick;
    }
    
    /*public Button getBallOperateButton() {
    	return _ballOperateButton;
    }
    
    public Button getIntakeOperateButton() {
    	return _intakeOperateButton;
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