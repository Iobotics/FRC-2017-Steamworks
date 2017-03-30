package org.usfirst.frc.team2438.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    // Drive //
    public static final int driveLeftMain      = 10;
    public static final int driveLeftSlave1    = 9;
    public static final int driveLeftSlave2    = 7;
    
    public static final int driveRightMain     = 2;
    public static final int driveRightSlave1   = 3;
    public static final int driveRightSlave2   = 4;
    
    // Agitator //
    
    public static final int agitatorTalon = 6;
    
    // Intake //
    
    public static final int intakeLeft  = 8;
    public static final int intakeRight = 5;
    
    // Shooter //
    
    public static final int shooterTalon = 1;
	
	// Winch //
    
	public static final int winchTalon = 11;     
}