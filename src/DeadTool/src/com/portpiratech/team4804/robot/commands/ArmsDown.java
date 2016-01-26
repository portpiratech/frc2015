/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portpiratech.team4804.robot.commands;
import com.portpiratech.team4804.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class ArmsDown extends Command {
    
    public ArmsDown() {
        requires(Robot.pistonSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //Robot.pistonSubsystem.retractArms();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; // Runs until interrupted, Xbox controller determines when finished
    }

    // Called once after isFinished returns true
    protected void end() {
        //Robot.pistonSubsystem.stopArms();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
