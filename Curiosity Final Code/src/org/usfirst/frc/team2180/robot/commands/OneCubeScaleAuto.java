package org.usfirst.frc.team2180.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OneCubeScaleAuto extends CommandGroup {

int position;
	
    public OneCubeScaleAuto(int position) {
    	this.position = position;
    	
    	if (position == 2) {
    		if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L') {
    			
			} else if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R') {
				
    		}
    	}
    }
}
