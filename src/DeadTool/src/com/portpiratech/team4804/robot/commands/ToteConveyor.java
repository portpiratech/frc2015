package com.portpiratech.team4804.robot.commands;

import com.portpiratech.team4804.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ToteConveyor extends Command {

    public ToteConveyor() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.toteConveyorSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putString("ToteConveyor", "init");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.toteConveyorSubsystem.conveyor(Robot.oi.getOperatorController());	
    	SmartDashboard.putString("ToteConveyor", "execute");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.toteConveyorSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
