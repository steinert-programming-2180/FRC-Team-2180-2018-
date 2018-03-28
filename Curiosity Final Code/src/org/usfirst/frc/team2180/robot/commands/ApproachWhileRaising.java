package org.usfirst.frc.team2180.robot.commands;

import org.usfirst.frc.team2180.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ApproachWhileRaising extends Command {
	
	double ticks, driveSpeed, elevateTime, delay, elevatorSpeed;
	Timer timer;
    public ApproachWhileRaising(double distance, double driveSpeed, double elevateTime, double delay) {
        this.ticks = Robot.inchesToTicks(distance);
        this.driveSpeed = driveSpeed;
        this.elevateTime = elevateTime;
        this.delay = delay;
        timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.talon1.set(ControlMode.Position, 0);
    	
    	Robot.talon1.configPeakOutputForward(Math.abs(driveSpeed), 10);
		Robot.talon1.configPeakOutputReverse(-Math.abs(driveSpeed), 10);
		
		Robot.talon1.setInverted(true);
		Robot.talon2.setInverted(true);
		Robot.talon3.setInverted(true);
		
		Robot.regTalon1.setInverted(false);
		Robot.regTalon2.setInverted(false);
		Robot.regTalon3.setInverted(false);
    	
    	Robot.talon1.setSelectedSensorPosition(0, 0, 10);
    	
    	Robot.gyro.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.talon1.set(ControlMode.Position, ticks);
    	
    	
    	if (!Robot.limitBottom.get()) {
			elevatorSpeed = Math.min(1.0, 0);
			Robot.elevatorTalon.set(elevatorSpeed);
		} else if (!Robot.limitTop.get()) {
			elevatorSpeed = Math.max(-1.0, 0);
			Robot.elevatorTalon.set(elevatorSpeed);
		} else {
			Robot.elevatorTalon.set(-1.0);
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if ((Math.abs(ticks) - (Math.abs(Robot.talon1.getSelectedSensorPosition(0))) < 100) && (timer.get() > elevateTime)) {
    		SmartDashboard.putNumber("Our Last Resort Encoder Reading", ticks - (Robot.talon1.getSelectedSensorPosition(0)));
    		Robot.regTalon1.set(0.0);
    		Robot.elevatorTalon.set(0.0);
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("PID Status", "Done");
    	Robot.talon1.setSelectedSensorPosition(0, 0, 10);
    	Robot.talon1.set(ControlMode.Position, 0.0);
    	Robot.elevatorTalon.set(0.0);
    	Timer.delay(delay);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
