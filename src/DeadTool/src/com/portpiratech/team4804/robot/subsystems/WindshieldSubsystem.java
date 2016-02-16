package com.portpiratech.team4804.robot.subsystems;

import com.portpiratech.team4804.robot.OI;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors, a left and right encoder
 * and a gyro.
 */
public class WindshieldSubsystem extends Subsystem {
	
	private CANTalon windshieldMotor = new CANTalon(OI.WINDSHIELD_ID);

	public void initDefaultCommand() {
		//setDefaultCommand(new *);
	}

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */ 
	public void log() {
	}
	
	public void setMotor(double speed) {
		windshieldMotor.set(speed);
	}
	
	public void decreaseSpeedSlowly() {
		
	}
}
