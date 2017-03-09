package org.usfirst.frc.team2438.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Drives straight 200 inches
 */
public class AutoDelayAndDrive extends CommandGroup {
    
    public AutoDelayAndDrive() {
    	addSequential(new WaitCommand(10));
        addSequential(new AutoDriveStraight(200));
    }
}
