package com.portpiratech.team4804.robot.commands;

import com.portpiratech.team4804.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ToteBridge extends Command {

    public ToteBridge() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.toteBridgeSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.toteBridgeSubsystem.bridge(Robot.oi.getOperatorController());	
    	SmartDashboard.putString("LoadTote", "execute");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return Robot.toteBridgeSubsystem.getSwitchStatus();
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.toteBridgeSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
