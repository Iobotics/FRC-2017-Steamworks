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
				this.addSequential(new AutoDriveStraight(-160));
				this.addSequential(new AutoTurn(-60));
				this.addSequential(new AutoDriveStraight(-20));
				this.addSequential(new AutoDriveStraight(20));
				break;
			case CENTER:
				this.addSequential(new AutoDriveStraight(-160, 0.35));
				break;
			case RIGHT:
				this.addSequential(new AutoDriveStraight(-160));
				this.addSequential(new AutoTurn(60));
				this.addSequential(new AutoDriveStraight(-20));
				this.addSequential(new AutoDriveStraight(10));
				break;
        }
    }
    
}
