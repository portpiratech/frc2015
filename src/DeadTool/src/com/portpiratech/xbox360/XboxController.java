package com.portpiratech.xbox360;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Class which adds helper methods to better use an XBox Controller without having
 * to do so much configuration within a FRC 2015 Robot.
 * @author PortPiratech
 *
 */
public class XboxController extends Joystick{

	/**
	 * Constructor which takes the XBox Controller port and calls super class.
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
	
	/**
	 * This method returns the X Button from the XBox Controller
	 * @return JoystickButton Mapped to Button X on Xbox Controller
	 */
	public JoystickButton getXButton(){
		return new JoystickButton(this, 3);
	}
	
	/**
<<<<<<< HEAD
	 * This method returns the Y Button from the XBox Controller
=======
	 * This method returns the X Button from the XBox Controller
>>>>>>> 3e8eef7da34d34c50830ccae585ba7e655b4340d
	 * @return JoystickButton Mapped to Button X on Xbox Controller
	 */
	public JoystickButton getYButton(){
		return new JoystickButton(this, 4);
	}
}
