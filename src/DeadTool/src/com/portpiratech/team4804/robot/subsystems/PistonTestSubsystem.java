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

	/**
	 * When no other command is running let the operator drive around
	 * using the PS3 joystick.
	 */
	public void initDefaultCommand() {
		//setDefaultCommand(new *);
	}

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	public void log() {
	}

	/**
	 * Tank style driving for the DriveTrain. 
	 * @param left Speed in range [-1,1]
	 * @param right Speed in range [-1,1]
	 */
	
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
