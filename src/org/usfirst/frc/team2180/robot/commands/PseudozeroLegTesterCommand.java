package org.usfirst.frc.team2180.robot.commands;

import org.usfirst.frc.team2180.robot.Constants;
import org.usfirst.frc.team2180.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PseudozeroLegTesterCommand extends Command {
	
	int ticks;
    public PseudozeroLegTesterCommand(int ticks) {
        this.ticks = ticks;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	SmartDashboard.putString("PID Status", "");
    	
//    	if (Robot.autoLegCounter < 2) {
//    		Robot.setupDrivetrainPID(0);
//    	} else if (Robot.autoLegCounter < 4) {
//    		Robot.setupDrivetrainPID(1);
//    	} else if (Robot.autoLegCounter < 6) {
//    		Robot.setupDrivetrainPID(2);
//    	} else {
//    		end();
//    	}
    	
    	Robot.talon1.set(ControlMode.Position, 0);
    	
    	Robot.talon1.configPeakOutputForward(Constants.autonSpeed, 10);
		Robot.talon1.configPeakOutputReverse(-Constants.autonSpeed, 10);
		
		Robot.talon1.setInverted(true);
    	
    	Robot.talon1.setSelectedSensorPosition(0, 0, 10);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.talon1.set(ControlMode.Position, ticks);
    	
    	SmartDashboard.putNumber("Auto Position", Robot.talon1.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Error", (ticks - (Robot.talon1.getSelectedSensorPosition(0))));
    	
    	SmartDashboard.putBoolean("Is Done", isFinished());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if ((Math.abs(ticks) - (Math.abs(Robot.talon1.getSelectedSensorPosition(0))) < 100)) {
    		SmartDashboard.putNumber("Our Last Resort Encoder Reading", ticks - (Robot.talon1.getSelectedSensorPosition(0)));
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("PID Status", "Done");
    	Robot.talon1.setSelectedSensorPosition(0, 0, 10);
    	Robot.talon1.set(ControlMode.Position, 0.0);
    	Timer.delay(2);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}