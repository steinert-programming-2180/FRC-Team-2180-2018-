package org.usfirst.frc.team2180.robot.commands;

import org.usfirst.frc.team2180.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestCommand extends CommandGroup {

    public TestCommand() {
        addSequential(new Turn(-90));
        addSequential(new RunForward(-Robot.inchesToTicks(10)));
    }
}
