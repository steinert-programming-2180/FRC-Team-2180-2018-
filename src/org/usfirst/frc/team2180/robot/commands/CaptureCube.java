package org.usfirst.frc.team2180.robot.commands;

import org.usfirst.frc.team2180.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CaptureCube extends Command {
	
	Timer timer;
	
    public CaptureCube() {
    	timer = new Timer();
    }

    protected void initialize() {
    	timer.start();
    }

    protected void execute() {
    	Robot.grabberTalon1.set(-1.0);
    	Robot.grabberTalon2.set(-0.7);
    }

    protected boolean isFinished() {
    	if (timer.get() > 1.0) {
    		return true;
    	}
        return false;
    }

    protected void end() {
    	Robot.grabberTalon1.set(0.0);
    	Robot.grabberTalon2.set(0.0);
    	timer.stop();
    }

    protected void interrupted() {
    	end();
    }
}
