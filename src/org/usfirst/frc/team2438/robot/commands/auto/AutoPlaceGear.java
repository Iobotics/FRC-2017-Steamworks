package org.usfirst.frc.team2438.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * FIXME - WIP
 * Places gear onto rung
 * @author darrenk4290
 */
public class AutoPlaceGear extends CommandGroup {
	
	public enum GearPosition {
		LEFT,
		CENTER,
		RIGHT
	}

    public AutoPlaceGear(GearPosition position) {        
        switch(position) {
			case LEFT:
				this.addSequential(new AutoDriveStraight(-35));
				this.addSequential(new AutoTurn(-60));
				this.addSequential(new AutoDriveStraight(-8));
				this.addSequential(new AutoDriveStraight(8));
				break;
			case CENTER:
				this.addSequential(new AutoDriveStraight(-114));
				this.addSequential(new AutoDriveStraight(30));
				break;
			case RIGHT:
				this.addSequential(new AutoDriveStraight(-35));
				this.addSequential(new AutoTurn(60));
				this.addSequential(new AutoDriveStraight(-8));
				this.addSequential(new AutoDriveStraight(4));
				break;
        }
    }
    
}
