package com.portpiratech.xbox360;

import edu.wpi.first.wpilibj.Joystick;

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
}
