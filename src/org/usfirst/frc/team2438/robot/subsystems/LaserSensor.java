package org.usfirst.frc.team2438.robot.subsystems;

//import org.usfirst.frc.team2438.robot.commands.ReadLIDAR;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * LIDAR
 */
public class LaserSensor extends Subsystem {

    private final int LIDAR_ADDRESS = 0x62;
    private final int REGISTER_MEASURE = 0x00;
    private final int MEASURE_VALUE = 0x04;
    private final int REGISTER_DISTANCE = 0x8f;
    
    private final int BYTES_READ = 2;
	
	private I2C _i2c;
	
	public void init() {
		_i2c = new I2C(Port.kMXP, LIDAR_ADDRESS);
		Timer.delay(0.1);
		
	}
	
	public int getDistance() {
		boolean nackack = false;
		
		while(!nackack) {
			nackack = _i2c.write(REGISTER_MEASURE, MEASURE_VALUE);
			System.out.print(".");
			Timer.delay(0.001);
		}
		
		// Array to store high and low bytes
		byte[] distanceArray = new byte[2];
		
		nackack = false;
		while(!nackack) {
			// Read two bytes from register 0x8f
			nackack = _i2c.read(REGISTER_DISTANCE, BYTES_READ, distanceArray);
			Timer.delay(0.001);
		}
		// Shift high byte and add to low byte
		int distance = (distanceArray[0] << 8) + distanceArray[1];
		
		return distance;
	}
	
	public void initDefaultCommand() {
		//this.setDefaultCommand(new ReadLIDAR());
	}
}

