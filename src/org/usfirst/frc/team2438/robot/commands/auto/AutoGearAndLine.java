package org.usfirst.frc.team2438.robot.commands.auto;

import org.usfirst.frc.team2438.robot.commands.auto.AutoPlaceGear.GearPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Place gear and cross the line
 */
public class AutoGearAndLine extends CommandGroup {

    public AutoGearAndLine(GearPosition position) {
    	this.addSequential(new AutoPlaceGear(position));
    	this.addSequential(new WaitCommand(6.0));
    	
    	switch(position) {
    		case LEFT:
    			this.addSequential(new AutoTurn(30));
    			this.addSequential(new AutoDriveStraight(60));
    			this.addSequential(new AutoTurn(90));
    			this.addSequential(new AutoDriveStraight(100));
    			break;
    		case CENTER:
    			this.addSequential(new AutoDriveStraight(30, 0.75));
    			this.addSequential(new AutoTurn(-90));
    			this.addSequential(new AutoDriveStraight(90, 0.75));
    			this.addSequential(new AutoTurn(-90));
    			this.addSequential(new AutoDriveStraight(95 + 35, 0.9));
    			break;
    		case RIGHT:
    			this.addSequential(new AutoTurn(-30));
    			this.addSequential(new AutoDriveStraight(120));
    			this.addSequential(new AutoTurn(90));
    			this.addSequential(new AutoDriveStraight(25));
    			break;
    	}
    }
}
