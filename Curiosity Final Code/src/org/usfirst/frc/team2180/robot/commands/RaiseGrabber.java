package org.usfirst.frc.team2180.robot.commands;

import org.usfirst.frc.team2180.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RaiseGrabber extends Command {
	
	Timer timer;
	
    public RaiseGrabber() {
        timer = new Timer();
    }

    protected void initialize() {
    	timer.start();
    }

    protected void execute() {
    	Robot.grabberFlipperTalon.set(0.5);
    }

    protected boolean isFinished() {
    	if (timer.get() > 0.5) {
    		return true;
    	}
        return false;
    }

    protected void end() {
    	timer.stop();
    	Robot.grabberFlipperTalon.set(0.0);
    }

    protected void interrupted() {
    	end();
    }
}
