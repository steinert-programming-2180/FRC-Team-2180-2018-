package org.usfirst.frc.team2180.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestLegIssueCommand extends CommandGroup {

    public TestLegIssueCommand() {
        addSequential(new RunForward(40));
        Timer.delay(2);
//        addSequential(new Turn(10));
//        Timer.delay(2);
        addSequential(new RunForward(40));
        Timer.delay(2);
//        addSequential(new Turn(-10));
//        Timer.delay(2);
        addSequential(new RunForward(40)); // pls work
    }
}
