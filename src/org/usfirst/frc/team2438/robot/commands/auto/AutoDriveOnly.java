package org.usfirst.frc.team2438.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drives straight 200 inches
 */
public class AutoDriveOnly extends CommandGroup {
    
    public AutoDriveOnly() {
        addSequential(new AutoDriveStraight(200));
    }
}
