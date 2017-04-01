package org.usfirst.frc.team2438.robot.commands.auto;

import org.usfirst.frc.team2438.robot.commands.auto.AutoPlaceGear.GearPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Place gear, shoot, and cross the line (aka Jesus auto)
 */
public class AutoGearShootAndLine extends CommandGroup {

    public AutoGearShootAndLine() {
        this.addSequential(new AutoGearAndShoot(GearPosition.CENTER));
        this.addSequential(new AutoDriveStraight(100));
    }
}
