package com.portpiratech.team4804.robot.commands;

import com.portpiratech.team4804.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LoadContainer extends Command {

    public LoadContainer() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.loadingSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.loadingSubsystem.initializeCounter();
    	Robot.loadingSubsystem.load();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.loadingSubsystem.getSwitchStatus();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.loadingSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
