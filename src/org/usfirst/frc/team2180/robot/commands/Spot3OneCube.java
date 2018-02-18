package org.usfirst.frc.team2180.robot.commands;

import org.usfirst.frc.team2180.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Spot3OneCube extends CommandGroup {

    public Spot3OneCube() {
    	if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R') {
    		addSequential(new RunForward(Robot.inchesToTicks(87)));
            addSequential(new Turn(-90));
            addParallel(new MoveElevator(2000)); // calculate the exact number of ticks later
            addParallel(new OpenGrabber());
            addSequential(new RunForward(Robot.inchesToTicks(16)));
            addSequential(new SpitOutCube());
    	} else if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L') {
    		addSequential(new RunForward(Robot.inchesToTicks(219.235)));
            addSequential(new Turn(-90));
            addParallel(new MoveElevator(2000)); // calculate the exact number of ticks later
            addParallel(new OpenGrabber());
            addSequential(new RunForward(Robot.inchesToTicks(176.75)));
            addSequential(new Turn(-90));
            addSequential(new RunForward(Robot.inchesToTicks(10)));
            addSequential(new Turn(-90));
            addSequential(new SpitOutCube());
    	}
    }
}
