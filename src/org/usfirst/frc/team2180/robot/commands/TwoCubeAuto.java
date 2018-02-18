package org.usfirst.frc.team2180.robot.commands;

import org.usfirst.frc.team2180.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoCubeAuto extends CommandGroup {
	
	int position;
	
    public TwoCubeAuto() {
    	setPosition(Robot.robotPositionChooser.getSelected());
    	
    	if (position == 1) {
    		
    	} else if (position == 3) {
    		
    	}
    }
    
    public void setPosition(int position) {
    	this.position = position;
    }
}
