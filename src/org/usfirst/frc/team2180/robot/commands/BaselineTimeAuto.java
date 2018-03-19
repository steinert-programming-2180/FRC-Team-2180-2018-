package org.usfirst.frc.team2180.robot.commands;

import org.usfirst.frc.team2180.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BaselineTimeAuto extends Command {
	
	Timer timer;
    public BaselineTimeAuto() {
        timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.talon1.set(ControlMode.PercentOutput, 0);
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.talon1.set(0.6);
    	Robot.regTalon1.set(0.6);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (timer.get() > 2) {
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.talon1.set(0.0);
    	Robot.regTalon1.set(0.0);
    	timer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
