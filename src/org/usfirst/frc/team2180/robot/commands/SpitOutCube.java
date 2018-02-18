package org.usfirst.frc.team2180.robot.commands;

import org.usfirst.frc.team2180.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpitOutCube extends Command {
	
	Timer timer;
	
    public SpitOutCube() {
        timer = new Timer();
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.grabberTalon1.set(1.0);
    	Robot.grabberTalon2.set(1.0);
    }

    protected boolean isFinished() {
    	if (timer.get() > 0.5) {
    		return true;
    	}
        return false;
    }

    protected void end() {
    	Robot.grabberTalon1.set(0.0);
    	Robot.grabberTalon2.set(0.0);
    }

    protected void interrupted() {
    	end();
    }
}
