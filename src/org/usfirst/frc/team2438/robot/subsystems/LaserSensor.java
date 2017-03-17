package org.usfirst.frc.team2438.robot.subsystems;

import java.io.UnsupportedEncodingException;

import org.usfirst.frc.team2438.robot.commands.ReadLIDAR;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LaserSensor extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
private SerialPort _serial;
	
	public void init() {
		try {
            /* Communicate w/navX MXP via the MXP SPI Bus.                                     */
            /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
            /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
            _serial = new SerialPort(115200, SerialPort.Port.kUSB2);
			//_ahrs = new AHRS(SerialPort.Port.kMXP);
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating serial USB:  " + ex.getMessage(), true);
        }
	}
	
	public boolean isReady() {
		return _serial.getBytesReceived() > 0;
	}
	
	public byte[] serialGet() {
		return _serial.read(2);
	}
	
	public void initDefaultCommand() {
		this.setDefaultCommand(new ReadLIDAR());
	}

    
}

