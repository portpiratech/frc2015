package com.portpiratech.team4804.robot.subsystems;

import com.portpiratech.team4804.robot.Robot;
import com.portpiratech.team4804.robot.commands.VisionProcess;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors, a left and right encoder
 * and a gyro.
 */
public class VisionSubsystem extends Subsystem {
	
	public static boolean visionProcessing;
	
	public VisionSubsystem() {
		visionProcessing = false;
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new VisionProcess());
	}

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */ 
	public void log() {
	}
	
	public void frameProcess() {
		Robot.vision.frameProcess();
	}
}
