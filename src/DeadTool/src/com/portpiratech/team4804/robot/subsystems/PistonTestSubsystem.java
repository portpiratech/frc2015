package com.portpiratech.team4804.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors, a left and right encoder
 * and a gyro.
 */
public class PistonTestSubsystem extends Subsystem {
	
	private DoubleSolenoid solenoid;
			

	public PistonTestSubsystem() {
		super();
		solenoid = new DoubleSolenoid(0,1);
	}


	public void initDefaultCommand() {
		//setDefaultCommand(new *);
	}

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	public void log() {
	}

	public void extend() {
		solenoid.set(Value.kForward);
	}
	
	public void retract(){
		solenoid.set(Value.kReverse);
		
	}
	
	public void stop(){
		solenoid.set(Value.kOff);
	}

	/**
	 * Reset the robots sensors to the zero states.
	 */
	public void reset() {
	}
}
