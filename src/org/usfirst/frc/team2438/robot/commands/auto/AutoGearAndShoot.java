package org.usfirst.frc.team2438.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * FIXME - WIP
 */
public class AutoGearAndShoot extends CommandGroup {

    public AutoGearAndShoot() {
        this.addSequential(new AutoPlaceGear());
        this.addSequential(new AutoTurn(-90));
        this.addSequential(new AutoDriveStraight(10, 0.35, 10));
        this.addSequential(new AutoShootBall(6));
    }
}
