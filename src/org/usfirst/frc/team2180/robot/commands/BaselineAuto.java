package org.usfirst.frc.team2180.robot.commands;

import org.usfirst.frc.team2180.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BaselineAuto extends CommandGroup {

    public BaselineAuto() {
        addSequential(new RunForward(Robot.inchesToTicks(10)));
    }
}
