package org.usfirst.frc.team2180.robot.commands;

import org.usfirst.frc.team2180.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoCubeAuto extends CommandGroup {
	
	int position;
	
    public TwoCubeAuto(int position) {
    	this.position = position;
    	
    	if (position == 1) {
    		if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L') {
    			if (DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'L') {
    				// 1LL
    				addSequential(new DriveForward(Robot.inchesToTicks(129)));
				addParallel(new MoveElevator(2000)); // not exact
				addSequential(new Turn(90));
				addSequential(new MoveForward(Robot.inchesToTicks(44)));
				addSequential(new DischargeCube());
				addSequential(new MoveForward(-Robot.inchesToTicks(44)));
				addSequential(new Turn(-72));
				addSequential(new MoveForward(Robot.inchesToTicks(78)));
				addParallel(new MoveElevator(-2000))// not exact;
				addSequential(new Turn(128));
				addSequential(new MoveForward(Robot.inchesToTicks(35)));
				addSequential(new CaptureCube());
				addSequential(new MoveForward(Robot.inchesToTicks(101)));
				addSequential(new Turn(90));
				addSequential(new MoveElevator(10000));// not exact
				addSequential(new MoveForward(Robot.inchesToTicks(19)));
				addSequential(new DischargeCube());
				addSequential(new MoveForward(-Robot.inchesToTicks(19)));
				addSequential(new MoveElevator(-10000));// not exact
    			} else if (DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'R') {
    				// 1LR
    				
    			}
    		} else if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R') {
    			if (DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'L') {
        			// 1RL
    				
    			} else if (DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'R') {
    				// 1RR
    				
    			}
    		}
     	} 
// 	    else if (position == 3) {
//     		if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L') {
//     			if (DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'L') {
//     				// 3LL
//     				addSequential(new OneCubeAuto(3));
//     				addParallel(new MoveElevator(-2000));
//     				addSequential(new RunForward(-Robot.inchesToTicks(10)));
//     				addParallel(new RunForward(Robot.inchesToTicks(10)));
//     				addSequential(new CaptureCube());
//     				addSequential(new RunForward(-Robot.inchesToTicks(10)));
//     				addParallel(new MoveElevator(10000));
//     				addSequential(new Turn(135));
//     				addSequential(new RunForward(Robot.inchesToTicks(90)));
//     				addSequential(new Turn(135));
//     				addSequential(new DischargeCube());
//     			} else if (DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'R') {
//     				// 3LR
//     				addParallel(new MoveElevator(10000)); //calculate precisely later
//     				addParallel(new OpenGrabber());
//     				addSequential(new RunForward(Robot.inchesToTicks(262.65)));
//     				addSequential(new Turn(-45));
//     				addSequential(new RunForward(Robot.inchesToTicks(5)));
//     				addSequential(new DischargeCube());
//     				addParallel(new MoveElevator(-10000));
//     				addSequential(new Turn(45));
//     				addSequential(new RunForward(-Robot.inchesToTicks(80)));
//     				addSequential(new Turn(-90));
//     				addSequential(new RunForward(Robot.inchesToTicks(176.75)));
//     				addSequential(new Turn(-90));
//     				addSequential(new RunForward(Robot.inchesToTicks(10)));
//     				addSequential(new CaptureCube());
//     				addSequential(new MoveElevator(2000));
//     				addSequential(new DischargeCube());
//     			}
//     		} else if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R') {
//     			if (DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'L') {
//         			// 3RL
//     				addParallel(new MoveElevator(2000));
//     				addParallel(new OpenGrabber());
//     				addSequential(new RunForward(Robot.inchesToTicks(93)));
//     				addSequential(new Turn(-45));
//     				addSequential(new RunForward(Robot.inchesToTicks(5)));
//     				addSequential(new DischargeCube());
//     				addSequential(new Turn(45));
//     				addSequential(new RunForward(Robot.inchesToTicks(126.235)));
//     				addSequential(new Turn(-90));
//     				addParallel(new MoveElevator(-2000));
//     				addSequential(new RunForward(Robot.inchesToTicks(176.75)));
//     				addSequential(new Turn(-90));
//     				addSequential(new RunForward(Robot.inchesToTicks(10)));
//     				addSequential(new CaptureCube());
//     				addParallel(new MoveElevator(10000));
//     				addSequential(new RunForward(-Robot.inchesToTicks(10)));
//     				addSequential(new Turn(-45));
//     				addSequential(new RunForward(Robot.inchesToTicks(130)));
//     				addSequential(new Turn(-45));
//     				addSequential(new RunForward(Robot.inchesToTicks(12)));
//     				addSequential(new DischargeCube());
//     			} else if (DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'R') {
//     				// 3RR
//     				addParallel(new MoveElevator(10000)); //calculate precisely later
//     				addParallel(new OpenGrabber());
//     				addSequential(new RunForward(Robot.inchesToTicks(262.65)));
//     				addSequential(new Turn(-45));
//     				addSequential(new DischargeCube());
//     				addParallel(new MoveElevator(-10000));
//     				addSequential(new Turn(-90));
//     				addSequential(new RunForward(Robot.inchesToTicks(50)));
//     				addSequential(new CaptureCube());
//     				addSequential(new MoveElevator(2000));
//     				addSequential(new DischargeCube());
//     			}
//     		}
//     	}
    }
}
