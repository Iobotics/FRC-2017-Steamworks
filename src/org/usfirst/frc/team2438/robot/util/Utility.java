package org.usfirst.frc.team2438.robot.util;

public class Utility {

	

    public static double sign(double input) {
        if(input == 0.0) return 0.0;
        return (input > 0.0) ? 1.0 : -1.0; 
    }

    public static double limit(double num) {
        if (num > 1.0) {
            return 1.0;
        }
        if (num < -1.0) {
            return -1.0;
        }
        return num;
    }
}
