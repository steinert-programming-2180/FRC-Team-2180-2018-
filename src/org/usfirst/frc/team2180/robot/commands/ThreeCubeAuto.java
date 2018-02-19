package org.usfirst.frc.team2180.robot.commands;

import org.usfirst.frc.team2180.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ThreeCubeAuto extends CommandGroup {
	
	int position;
	
    public ThreeCubeAuto() {
    	setPosition(Robot.robotPositionChooser.getSelected());
    	
    	if (position == 1) {
    		if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L') {
    			if (DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'L') {
    				// 1LL
    				addSequential(new TwoCubeAuto());
    				addSequential(new RunForward(-Robot.inchesToTicks(10)));
    				addParallel(new MoveElevator(-2000));
    				addSequential(new Turn(-15));
    				addParallel(new RunForward(Robot.inchesToTicks(15)));
    				addSequential(new CaptureCube());
    				addParallel(new MoveElevator(10000));
    				addSequential(new RunForward(-Robot.inchesToTicks(100)));
    				addSequential(new Turn(-150));
    				addSequential(new RunForward(Robot.inchesToTicks(20)));
    				addSequential(new DischargeCube());
    			} else if (DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'R') {
    				// 1LR
    				addSequential(new TwoCubeAuto());
    			}
    		} else if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R') {
    			if (DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'L') {
    				// 1RL
    				addSequential(new TwoCubeAuto());
    			} else if (DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'R') {
    				// 1RR
    				addSequential(new TwoCubeAuto());
    			}
    		}
    	} else if (position == 3) {
    		if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L') {
    			if (DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'L') {
    				// 3LL
    				addSequential(new TwoCubeAuto());
    			} else if (DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'R') {
    				// 3LR
    				addSequential(new TwoCubeAuto());
    			}
    		} else if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R') {
    			if (DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'L') {
    				// 3RL
    				addSequential(new TwoCubeAuto());
    			} else if (DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'R') {
    				// 3RR
    				addSequential(new TwoCubeAuto());
    				addSequential(new RunForward(-Robot.inchesToTicks(10)));
    				addParallel(new MoveElevator(-2000));
    				addSequential(new Turn(15));
    				addParallel(new RunForward(Robot.inchesToTicks(15)));
    				addSequential(new CaptureCube());
    				addParallel(new MoveElevator(10000));
    				addSequential(new RunForward(-Robot.inchesToTicks(100)));
    				addSequential(new Turn(150));
    				addSequential(new RunForward(Robot.inchesToTicks(20)));
    				addSequential(new DischargeCube());
    			}
    		}
    	}
    }
    
    public void setPosition(int position) {
    	this.position = position;
    }
}
