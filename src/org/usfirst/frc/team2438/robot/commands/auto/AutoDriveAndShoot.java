package org.usfirst.frc.team2438.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * FIXME - WIP
 */
public class AutoDriveAndShoot extends CommandGroup {

    public AutoDriveAndShoot() {
    	this.addSequential(new AutoDriveStraight(10, 0.35, 10));
    	this.addSequential(new AutoTurn(-90));
        this.addSequential(new AutoDriveStraight(10, 0.35, 10));
        this.addSequential(new AutoShootBall(6));
    }
    
}
