package com.portpiratech.xbox360;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class XboxController extends Joystick{

	public XboxController(int port) {
		super(port);
		// TODO Auto-generated constructor stub
	}
	
	public double getLeftStickYAxis(){
		return getY();
	}
	
	public double getRightStickYAxis(){
		return getRawAxis(5);
	}
	
	public JoystickButton getAButton(){
		return new JoystickButton(this,1);
	}
	
	public JoystickButton getBButton(){
		return new JoystickButton(this, 2);
	}
	
	
}
