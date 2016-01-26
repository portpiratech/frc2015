package com.portpiratech.team4804.robot.subsystems;

import com.portpiratech.team4804.robot.OI;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors, a left and right encoder
 * and a gyro.
 */
public class PistonSubsystem extends Subsystem {
	
	/*private DoubleSolenoid solenoid1;
	private DoubleSolenoid solenoid2;

	public PistonSubsystem() {
		super();
		solenoid1 = new DoubleSolenoid(OI.SOLENOID1_PORT1,OI.SOLENOID1_PORT2);
		solenoid2 = new DoubleSolenoid(OI.SOLENOID2_PORT1,OI.SOLENOID1_PORT2);
	}*/


	public void initDefaultCommand() {
		//setDefaultCommand(new *);
	}

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */ 
	/*public void log() {
	}

	public void extendLift() {
		solenoid1.set(Value.kForward);
	}
	
	public void retractLift(){
		solenoid1.set(Value.kReverse);
	}
	
	public void stopLift() {
		solenoid1.set(Value.kOff);
	}
	
	public void extendArms() {
		solenoid2.set(Value.kForward);
	}
	
	public void retractArms() {
		solenoid2.set(Value.kReverse);
	}
	
	public void stopArms() {
		solenoid2.set(Value.kOff);
	}*/
}
