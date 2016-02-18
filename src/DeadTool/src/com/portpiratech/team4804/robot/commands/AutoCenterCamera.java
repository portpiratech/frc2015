package com.portpiratech.team4804.robot.commands;

import com.portpiratech.team4804.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoCenterCamera extends Command {
	
    public AutoCenterCamera() {
        requires(Robot.driveTrain);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        SmartDashboard.putNumber("Proportional (p) constant", Robot.driveTrain.p);
    	SmartDashboard.putNumber("Proportional (i) constant", Robot.driveTrain.i);
    	SmartDashboard.putNumber("Proportional (d) constant", Robot.driveTrain.d);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.enablePID(true);
    	Robot.driveTrain.p = SmartDashboard.getNumber("Proportional (p) constant");
    	Robot.driveTrain.i = SmartDashboard.getNumber("Proportional (i) constant");
    	Robot.driveTrain.d = SmartDashboard.getNumber("Proportional (d) constant");
    	Robot.driveTrain.getPIDController().setPID(Robot.driveTrain.p, Robot.driveTrain.i, Robot.driveTrain.d);
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	cancel();
    	end();
    }
}
