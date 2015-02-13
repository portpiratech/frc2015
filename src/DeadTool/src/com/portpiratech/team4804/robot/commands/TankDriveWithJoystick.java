/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portpiratech.team4804.robot.commands;

import com.portpiratech.team4804.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Have the robot drive tank style using the Xbox Joystick until interrupted.
 */
public class TankDriveWithJoystick extends Command {
    
    public TankDriveWithJoystick() {
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.drivetrain.drive(Robot.oi.getDriverController());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; // Runs until interrupted
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drivetrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
