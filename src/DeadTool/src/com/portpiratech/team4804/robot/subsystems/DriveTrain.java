package com.portpiratech.team4804.robot.subsystems;

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

	public DriveTrain() {
		super();
		left_motor = new Talon(0);
		right_motor = new Talon(1);
		
		drive = new RobotDrive(left_motor, right_motor);
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
	 * Tank style driving for the DriveTrain. 
	 * @param left Speed in range [-1,1]
	 * @param right Speed in range [-1,1]
	 */
	public void drive(double left, double right) {
		drive.tankDrive(-left, -right);
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
