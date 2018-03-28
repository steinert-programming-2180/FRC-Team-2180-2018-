package org.usfirst.frc.team2180.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class OneCubeAuto extends CommandGroup {
	
	int position;
	
    public OneCubeAuto(int position) {
    	this.position = position;
    	
    	if (position == 2) {
    		if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L') {
    			addSequential(new OpenGrabber());
	    		addSequential(new RunForward(10, 0.5, 0));
				addSequential(new Turn(-48, 1.0, 0.25));
				addSequential(new RunForward(47, 0.5, 0));
				addSequential(new Turn(48, 1.0, 0.5));
				addSequential(new ApproachWhileRaising(10, 0.25, 1.0, 0.25));
				addSequential(new DischargeCube());
			} else if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R') {
				addSequential(new OpenGrabber());
	    		addSequential(new RunForward(10, 0.5, 0));
				addSequential(new Turn(43, 1.0, 0.25));
				addSequential(new RunForward(35, 0.5, 0));
				addSequential(new Turn(-43, 1.0, 0.5));
				addSequential(new ApproachWhileRaising(12, 0.25, 0.8, 0));
				addSequential(new DischargeCube());
    		}
    	}
    }
}