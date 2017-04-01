package org.usfirst.frc.team2438.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive and shoot balls
 */
public class AutoDriveAndShoot extends CommandGroup {

    public AutoDriveAndShoot() {
    	this.addSequential(new AutoDriveStraight(107));
    	this.addSequential(new AutoTurn(90));
        this.addSequential(new AutoDriveStraight(107));
        this.addSequential(new AutoTurn(90));
        this.addSequential(new AutoDriveStraight(87));
        this.addSequential(new AutoShootBall(10));
    }
    
}
