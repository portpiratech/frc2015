package com.portpiratech.team4804.robot.subsystems;

import com.portpiratech.team4804.robot.commands.ReadEncoder;
import com.portpiratech.xbox360.XboxController;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	THIS WILL EVENTUALLY BECOME THE BRIDGE SUBSYSTEM.
 *  THIS WILL HAPPEN WHEN THE ENCODERS ARE HOOKED UP TO THE ROBOT.
 *  WHEN THAT HAPPENDS DELETE THE PRE-EXISTING BRIDGESUBSYSTEM AND
 *  RENAME THIS ONE AND DELETE THIS TEXT.
 *
 */

public class EncoderSubsystem extends Subsystem {
	
	Encoder encoder;
	VictorSP motorController;
	private final DigitalInput aChannel = new DigitalInput(0);
	private final DigitalInput bChannel = new DigitalInput(1);
	private int position = 0;
	private final int maxPosition = 4;
	private final int minPosition = 0;
	
	public EncoderSubsystem() {
		super();
		motorController = new VictorSP(4);
		encoder = new Encoder(aChannel, bChannel, true);
		
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
       setDefaultCommand(new ReadEncoder());
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
    
    public double readEncoder() {
    	return encoder.get();
    }
    
    public void initializePosition() {
    	//sets to default position
    	encoder.reset();
    }
    
    public void positionUp() {
    	if(position < maxPosition) {
    		position++;
    		while(encoder.get() < getPosition()) {
    			motorController.set(.1);
    		}
    		motorController.set(0.0);
    	}
    	
    }
    
    public void positionDown() {
    	if(position > minPosition) {
    		position--;
    		while(encoder.get() > getPosition()) {
    			motorController.set(-.1);
    		}
    		motorController.set(0.0);    	}
    }
    
    public double getPosition() {
    	double pos = 0;
    	//positions of bridge
    	switch(position) {
    	
    	case 0: 
    		pos = 0;
    		break;
    		
    	case 1: 
    		pos = 20;
    		break;
    		
    	case 2: 
    		pos = 40;
    		break;
    		
    	case 3: 
    		pos = 60;
    		break; 
    		
    	case 4: 
    		pos = 80;
    		break;
    	}	
    	return pos;
    	
    }
        
    
 }

