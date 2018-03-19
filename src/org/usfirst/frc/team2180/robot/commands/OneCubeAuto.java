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
	    		addSequential(new RunForward(120));
//				addSequential(new Turn(-52));
//				addSequential(new RunForward(55));
//				addSequential(new Turn(47));
////				addSequential(new RunForward(35));
//				addSequential(new DischargeCube());
			} else if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R') {
//				addSequential(new OpenGrabber());
//				addParallel(new MoveElevator(1.1)); // not exact
	    		addSequential(new RunForward(120));
//				addSequential(new Turn(43));
//				addSequential(new RunForward(40));
//				addSequential(new Turn(-43));
////				addSequential(new RunForward(28));
//				addSequential(new DischargeCube());+
    		}
    	}
    }
}