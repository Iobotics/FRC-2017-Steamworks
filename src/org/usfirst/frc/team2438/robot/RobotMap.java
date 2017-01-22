package org.usfirst.frc.team2438.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    // drive //
    public static final int driveLeftMain      = 3;
    public static final int driveLeftSlave1    = 2;
    public static final int driveLeftSlave2    = 1;
    public static final int driveRightMain     = 6;
    public static final int driveRightSlave1   = 5;
    public static final int driveRightSlave2   = 4;
    
    // Agitator //
    
    // intake //
    
    public static final int intake = 13;
    
    // shooter //
    
    // camera & distance //
	
	// winch //
    
	public static final int winchTalon = 7; //TODO - Change ID
	
	// gear system //
    
    // hopper //
	
    public static final int agitatorTalon = 7; // TODO - Change ID
}