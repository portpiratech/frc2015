/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portpiratech.team4804.robot.commands;

import com.portpiratech.team4804.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RetractPiston extends Command {
    
    public RetractPiston() {
        requires(Robot.pistonTestSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	 SmartDashboard.putString("RetractPiston", "Initialized");
    }
   

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("RetractPiston", "Execute");
        Robot.pistonTestSubsystem.retract();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; // Runs until interrupted
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("RetractPiston", "End");
        Robot.pistonTestSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
