package com.portpiratech.team4804.robot.subsystems;

import com.portpiratech.team4804.robot.commands.ReadEncoder;
import com.portpiratech.xbox360.XboxController;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *	THIS WILL EVENTUALLY BECOME THE BRIDGE SUBSYSTEM.
 *  THIS WILL HAPPEN WHEN THE ENCODERS ARE HOOKED UP TO THE ROBOT.
 *  WHEN THAT HAPPENDS DELETE THE PRE-EXISTING BRIDGESUBSYSTEM AND
 *  RENAME THIS ONE AND DELETE THIS TEXT.
 *
 */

public class EncoderSubsystem extends Subsystem {
	
	DigitalInput limitSwitch;
	Counter counter;
	Encoder encoder;
	VictorSP motorController;
	private final DigitalInput aChannel = new DigitalInput(0);
	private final DigitalInput bChannel = new DigitalInput(1);
	private int position = 0;
	private final int maxPosition = 4;
	private final int minPosition = 0;
	//private double downSpeed = 0.3;
	//private double upSpeed = -0.5;
	private double pos = 0;
	//private double lockSpeed = 0;
	private double speedInc = 0.05;
	// 497 pulses maximum
	// 0.724 degree per 1 pulse
	// 1.38 pulses per 1 degree
	private final double home = 0;
	//private final double pos1 = 24.85;
	//private final double pos2 = 49.7;
	private final double pos1 = 45.00; //test value only
	private final double pos2 = 65.00; //test value only
	private final double pos3 = 74.55;
	private final double pos4 = 99.4;
	//private final boolean SWITCHPRESSED = true;
	private boolean isResetting = false;
	
	public EncoderSubsystem() {
		super();
		motorController = new VictorSP(4);
		encoder = new Encoder(aChannel, bChannel, true);
		limitSwitch = new DigitalInput(2);
		counter = new Counter(limitSwitch);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
       setDefaultCommand(new ReadEncoder());
       SmartDashboard.putString("Encoder Command", "idle");
    }
    
    public void test(XboxController xbox) {
    	test(xbox.getLeftStickYAxis());
    }
    
    public void test(double speed) {
    	if(Math.abs(speed) > .1) {
    		motorController.set(speed);
    	}else{
    		motorController.set(0.0);
    	}
    }
    
    public void startReset() {
    	if (isResetting) {
    		return;
    	}
    	isResetting = true;
    	SmartDashboard.putString("Encoder Subsystem", "reset method called");
    	SmartDashboard.putBoolean("Encoder Subsystem Switch", isSwitchPressed());
    	if (isSwitchPressed() == false) {
	    	motorController.set(-0.5);
    	}
    }
    
    public void finishReset() {
    	initializePosition();
    	stop();
    	initializeCounter();
    	SmartDashboard.putNumber("Encoder Position In finishReset", encoder.get());
    	position = 0;
    	isResetting = false;
    }
    
    public void stop() {
    	motorController.set(0.0);
    	initializePosition();
    }
    
    public double readEncoder() {
    	return encoder.get();
    }
        
    public void initializePosition() {
    	//sets to default position
    	encoder.reset();
    }
    
    public void positionUp() {
    	//moves toward home pos from floor
    	if(!isResetting && position < maxPosition) {
    		SmartDashboard.putString("Encoder Command", "Position Up Set Mode");
    		position++;
    		//motorController.set(upSpeed);
    		//while(encoder.get() >= getTargetPosition()) {
    		//}
    		SmartDashboard.putNumber("Encoder Pos before correction", encoder.get());
    		SmartDashboard.putString("Encoder Command", "Position Up Correction Mode");
    		// high speed overshoots target position, so move it back at a slow speed
    		//if(encoder.get() < getTargetPosition()) motorController.set(downSpeed);
    		//while(encoder.get() <= getTargetPosition()) {
    		//}
    		//motorController.set(-0.2);
    		setLockSpeed();
    		
    	}
		SmartDashboard.putString("Encoder Command", "idle");
    }
    
    public void positionDown() {
    	//moves away from home pos toward floor
    	if(!isResetting && position > minPosition) {
    		SmartDashboard.putString("Encoder Command", "Position Down Set Mode");
    		position--;
    		//motorController.set(downSpeed);
    		//while(encoder.get() <= getTargetPosition()) {
    		//}
    		SmartDashboard.putNumber("Encoder Pos before correction", encoder.get());
    		SmartDashboard.putString("Encoder Command", "Position Down Correction Mode");
    		// high speed overshoots target position, so move it back at a slow speed
    		//if(encoder.get() > getTargetPosition()) motorController.set(upSpeed);
    		//while(encoder.get() >= getTargetPosition()) {
    		//}
    		//motorController.set(-0.2);    	
    		setLockSpeed();
    	}
		SmartDashboard.putString("Encoder Command", "idle");
    }
    
    public void initializeCounter() {
    	counter.reset();
    }
    
    public boolean getSwitchStatus() {
    	if(isSwitchPressed()) {
    		return true;
    	}
    	return counter.get() > 0;
    }
    
    public boolean isSwitchPressed() {
    	return limitSwitch.get();
    }
    
    
    public void setLockSpeed() {
    	double currentSpeed = 1;
    	double finalSpeed = 0;
    	double posError = 1;
    	while(Math.abs(posError) > 0) {
    		// for debugging purposes, error bounds are zero so loop runs constantly
	    	currentSpeed = motorController.getSpeed();
	    	//finalSpeed = currentSpeed;
	    	//double posError = getTargetPosition() - encoder.get();
	    	posError = getTargetPosition() - encoder.get();
	    	SmartDashboard.putNumber("Position Error", posError);
	    		    	
	    	if(posError >= 1) {
	    		// increase speed toward floor if current position is lower than target
	    		SmartDashboard.putString("Encoder Command", "Lock Speed Mode - Incrementing");
	    		finalSpeed = (currentSpeed + speedInc);	    		
	    	}
  
	    	if(posError <= -1) {
	    		// increase speed toward robot if current position is greater than target
	    		SmartDashboard.putString("Encoder Command", "Lock Speed Mode - Decrementing");
	    		finalSpeed = (currentSpeed - speedInc);
	    	}
	    	
	    	if(posError < 1 && posError > -1) {
	    			// do nothing if the current position is within reasonable bounds
	    			SmartDashboard.putString("Encoder Command", "Lock Speed Mode - Doing Nothing");
	    			finalSpeed = currentSpeed;
	    	}
	    	
	    	SmartDashboard.putString("Encoder Command", "Lock Speed Mode - Setting Speed");
	    	motorController.set(finalSpeed);
	    	
	    	Timer.delay(0.1);
	    	
	    	SmartDashboard.putNumber("Final Speed", finalSpeed);
	    	SmartDashboard.putNumber("Encoder Position Actual", readEncoder());
	    	SmartDashboard.putNumber("Encoder Position Target", getTargetPosition());
	    	SmartDashboard.putString("Encoder Command", "Lock Speed Mode");
    	}
    	return;
    }
    
    public double getTargetPosition() {
    	
    	//positions of bridge
    	switch(position) {
    	
    	case 0: 
    		pos = home;
    		break;
    		
    	case 1: 
    		pos = pos1;
    		break;
    		
    	case 2: 
    		pos = pos2;
    		break;
    		
    	case 3: 
    		pos = pos3;
    		break; 
    		
    	case 4: 
    		pos = pos4;
    		break;
    	}	
    	return pos;
    	
    }
        
    
 }

