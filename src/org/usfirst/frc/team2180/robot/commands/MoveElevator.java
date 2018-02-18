package org.usfirst.frc.team2180.robot.commands;

import org.usfirst.frc.team2180.robot.Constants;
import org.usfirst.frc.team2180.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveElevator extends Command {

	int ticks;
	
    public MoveElevator(int ticks) {
    	this.ticks = ticks;
    }

    protected void initialize() {
    	Robot.elevatorTalon.configPeakOutputForward(Constants.elevatorAutonSpeed, 10);
		Robot.elevatorTalon.configPeakOutputReverse(-Constants.elevatorAutonSpeed, 10);
		Robot.elevatorTalon.setSelectedSensorPosition(0, 0, 10);
    }

    protected void execute() {
    	Robot.elevatorTalon.set(ControlMode.Position, ticks);
    }

    protected boolean isFinished() {
    	if (ticks - Robot.elevatorTalon.getSelectedSensorPosition(0) < 20) {
    		return true;
    	}
        return false;
    }

    protected void end() {
    	Robot.elevatorTalon.set(ControlMode.PercentOutput, 0.0);
    }

    protected void interrupted() {
    	end();
    }
}
