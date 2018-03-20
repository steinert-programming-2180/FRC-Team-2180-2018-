package org.usfirst.frc.team2180.robot.commands;

import org.usfirst.frc.team2180.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class OneCubeAuto extends CommandGroup {
	
	int position;
	
    public OneCubeAuto(int position) {
    	this.position = position;
    	
    	if (position == 2) {
    		if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L') {
//    			addSequential(new OpenGrabber());
//    			addParallel(new MoveElevator(1.1)); // not exact
	    		addSequential(new RunForward(20, 0.1, 1));
	    		addSequential(new Turn(30, 1.0, 1));
	    		addSequential(new RunForward(20, 0.1, 1));
	    		addSequential(new Turn(-30, 1.0, 1));
	    		addSequential(new RunForward(10, 0.1, 0));
//				addSequential(new Turn(-52));
//				addSequential(new RunForward(55));
//				addSequential(new Turn(47));
////				addSequential(new RunForward(35));
//				addSequential(new DischargeCube());
			} else if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R') {
//				addSequential(new OpenGrabber());
//				addParallel(new MoveElevator(1.1)); // not exact
	    		addSequential(new RunForward(40, 0.25, 0.25));
				addSequential(new Turn(43, 1.0, 0.25));
				addSequential(new RunForward(40, 0.25, 0.25));
				addSequential(new Turn(-43, 1.0, 0.5));
				addSequential(new RunForward(15, 0.25, 0.25));
//				addSequential(new DischargeCube());
    		}
    	}
    }
}