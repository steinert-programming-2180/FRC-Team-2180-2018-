package org.usfirst.frc.team2180.robot.commands;

import org.usfirst.frc.team2180.robot.Constants;
import org.usfirst.frc.team2180.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveElevator extends Command {

	double seconds;
	Timer timer;
	
    public MoveElevator(double seconds) {
    	this.seconds = seconds;
    	timer = new Timer();
    }

    protected void initialize() {
//    	Robot.elevatorTalon.configPeakOutputForward(Constants.elevatorAutonSpeed, 10);
//		Robot.elevatorTalon.configPeakOutputReverse(-Constants.elevatorAutonSpeed, 10);
//		Robot.elevatorTalon.setSelectedSensorPosition(0, 0, 10);
//		Robot.elevatorTalon.setInverted(false);
    	timer.start();
    }

    protected void execute() {
    	Robot.elevatorTalon.set(ControlMode.PercentOutput, -1.0);
    }

    protected boolean isFinished() {
//    	if (ticks - Robot.elevatorTalon.getSelectedSensorPosition(0) < 100) {
//    		return true;
//    	}
    	if (timer.get() > seconds) {
    		return true;
    	}
    	
        return false;
    }

    protected void end() {
    	Robot.elevatorTalon.set(ControlMode.PercentOutput, 0.0);
    	timer.stop();
    }

    protected void interrupted() {
    	end();
    }
}
