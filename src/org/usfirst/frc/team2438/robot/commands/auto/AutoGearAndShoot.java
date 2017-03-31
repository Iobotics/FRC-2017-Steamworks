package org.usfirst.frc.team2438.robot.commands.auto;

import org.usfirst.frc.team2438.robot.commands.auto.AutoPlaceGear.GearPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * FIXME - WIP
 */
public class AutoGearAndShoot extends CommandGroup {

    public AutoGearAndShoot(GearPosition position) {
    	this.addSequential(new AutoPlaceGear(position));
    	
    	switch(position) {
    		case LEFT:
    			this.addSequential(new AutoTurn(-30));
    			this.addSequential(new AutoDriveStraight(60));
    			this.addSequential(new AutoTurn(-90));
    			this.addSequential(new AutoDriveStraight(100));
    			break;
    		case CENTER:
    			this.addSequential(new AutoTurn(90));
    			this.addSequential(new AutoDriveStraight(120));
    			this.addSequential(new AutoTurn(-45));
    			this.addSequential(new AutoDriveStraight(40));
    			break;
    		case RIGHT:
    			this.addSequential(new AutoTurn(30));
    			this.addSequential(new AutoDriveStraight(120));
    			this.addSequential(new AutoTurn(-90));
    			this.addSequential(new AutoDriveStraight(25));
    			break;
    	}
    }
}
