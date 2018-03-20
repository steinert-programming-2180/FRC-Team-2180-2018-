package org.usfirst.frc.team2180.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestLegIssueCommand extends CommandGroup {

    public TestLegIssueCommand() {
        addSequential(new PseudozeroLegTesterCommand(2000));
        addSequential(new PseudozeroLegTesterCommand(-2000));
        addSequential(new PseudozeroLegTesterCommand(2000));
        addSequential(new PseudozeroLegTesterCommand(-2000));
        addSequential(new PseudozeroLegTesterCommand(2000));
    }
}
