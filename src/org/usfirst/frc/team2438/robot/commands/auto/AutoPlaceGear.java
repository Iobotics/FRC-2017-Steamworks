package org.usfirst.frc.team2438.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * FIXME - WIP
 * Places gear onto rung
 * @author darrenk4290
 */
public class AutoPlaceGear extends CommandGroup {

    public AutoPlaceGear() {
    	this.addSequential(new AutoDriveDeadReckon(10, 10));
    	this.addSequential(new AutoTurn(90));
        this.addSequential(new AutoDriveDeadReckon(5, 10));
        this.addSequential(new WaitCommand(1.0));
        this.addSequential(new AutoDriveDeadReckon(-5, 10));
    }
    
}
