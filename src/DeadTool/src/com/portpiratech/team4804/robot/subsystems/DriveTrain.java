package com.portpiratech.team4804.robot.subsystems;

import com.portpiratech.team4804.robot.OI;
import com.portpiratech.team4804.robot.Robot;
import com.portpiratech.team4804.robot.commands.TankDriveWithJoystick;
import com.portpiratech.xbox360.XboxController;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors, a left and right encoder
 * and a gyro.
 */
public class DriveTrain extends Subsystem {
	private SpeedController left_motor,
							right_motor;
	private RobotDrive drive;
	
	static double speedMult; //speed multiplier
	static double lowSpeed;
	static double highSpeed;

	public DriveTrain() {
		super();
		left_motor = new Talon(OI.DRIVEMOTOR2_PORT);
		right_motor = new Talon(OI.DRIVEMOTOR1_PORT);
		
		drive = new RobotDrive(left_motor, right_motor);
		
		switch(Robot.currentMode) {
		case NORMAL_MODE:
			lowSpeed = 0.65;
			highSpeed = 0.8;
			break;
			
		case DUMMY_MODE:
			lowSpeed = 0.55;
			highSpeed = 0.6;
			break;
		}
		
		speedMult = highSpeed;
	}

	/**
	 * When no other command is running let the operator drive around
	 * using the PS3 joystick.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new TankDriveWithJoystick());
	}

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	public void log() {
	}
	
	/**
	 * Increases speed multiplier
	 */
	public void speedUp() {
		speedMult = highSpeed;
	}
	
	/**
	 * Decreases speed multiplier
	 */
	public void speedDown() {
		speedMult = lowSpeed;
	}

	/**
	 * Tank style driving for the DriveTrain. 
	 * @param left Speed in range [-1,1]
	 * @param right Speed in range [-1,1]
	 */
	public void drive(double left, double right) {
		drive.tankDrive(-left*speedMult, -right*speedMult);
	}

	/**
	 * @param joy The ps3 style joystick to use to drive tank style.
	 */
	public void drive(XboxController xbox) {
		//drive(-joy.getY(), -joy.getAxis(AxisType.kThrottle));
		drive(xbox.getLeftStickYAxis(),xbox.getRightStickYAxis());
	}

	/**
	 * Reset the robots sensors to the zero states.
	 */
	public void reset() {
	}
}
