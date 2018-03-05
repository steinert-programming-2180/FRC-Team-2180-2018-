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
    			addSequential(new RunForward(24));
			addSequential(new Turn(-65));
			addParallel(new MoveElevator(2000)); // not exact
			addSequential(new RunForward(62));
			addSequential(new Turn(66));
			addSequential(new RunForward(49));
			addSequential(new DischargeCube());
		} else if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R') {
			addSequential(new RunForward(24));
			addSequential(new Turn(65));
			addParallel(new MoveElevator(2000)); // not exact
			addSequential(new RunForward(62));
			addSequential(new Turn(-66));
			addSequential(new RunForward(49));
			addSequential(new DischargeCube());
    		}
    	}
    }
}
