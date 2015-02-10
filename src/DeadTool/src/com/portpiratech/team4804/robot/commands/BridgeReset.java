package com.portpiratech.team4804.robot.commands;

import com.portpiratech.team4804.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BridgeReset extends Command {

	public static int numExecutions = 0;
	
    public BridgeReset() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.encoderSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	numExecutions++;
    	Robot.encoderSubsystem.initializeCounter();
    	Robot.encoderSubsystem.startReset();
    	SmartDashboard.putNumber("Bridge Reset Executions", numExecutions);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.encoderSubsystem.getSwitchStatus();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.encoderSubsystem.finishReset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
