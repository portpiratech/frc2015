package com.portpiratech.xbox360;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class XboxController extends Joystick{

	/**
	 * Class which adds helper methods to better use an XBox Controller without having
	 * to do so much configuration within a FRC 2015 Robot.
	 * 
	 * @param port that Xbox Controller is configured on.
	 */
	public XboxController(int port) {
		super(port);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return a double value representing the position of the Left Stick on the XBox Controller.
	 */
	public double getLeftStickYAxis(){
		return getY();
	}
	
	/**
	 * 
	 * @return a double value representing the position of the Right Stick on the XBox Controller.
	 */
	public double getRightStickYAxis(){
		return getRawAxis(5);
	}
	
	/**
	 * This method returns the A Button from the XBox Controller
	 * @return
	 */
	public JoystickButton getAButton(){
		return new JoystickButton(this,1);
	}
	
	/**
	 * This method returns the B Button from the XBox Controller
	 * @return JoystickButton Mapped to Button B on Xbox Controller
	 */
	public JoystickButton getBButton(){
		return new JoystickButton(this, 2);
	}
	
	
}
