package com.portpiratech.team4804.robot.subsystems;

import com.portpiratech.team4804.robot.OI;
import com.portpiratech.team4804.robot.commands.BridgeLock;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This subsystem constantly checks/regulates the position of the tote bridge.
 * It allows the bridge to lock into particular positions we need in order to operate.
 */
public class ToteBridgePosSubsystem extends Subsystem {
    
	DigitalInput primaryLimitSwitch;
	DigitalInput secondaryLimitSwitch;
	Counter primaryCounter;
	Counter secondaryCounter;
	Encoder encoder;
	Talon primaryMotorController;
	Talon secondaryMotorController;
	private final DigitalInput aChannel = new DigitalInput(0);
	private final DigitalInput bChannel = new DigitalInput(1);
	private int position = 0;
	private final int maxPosition = 4;
	private final int minPosition = 0;
	//private double downSpeed = 0.3;
	//private double upSpeed = -0.5;
	private double pos = 0;
	private final double posTolerance = 1;
	private final double maxSpeed = .65;
	//private double lockSpeed = 0;
	private double incGain = 0.001;
	// 497 pulses maximum
	// 0.724 degree per 1 pulse
	// 1.38 pulses per 1 degree
	private final double home = 50;
	//private final double pos1 = 24.85;
	//private final double pos2 = 49.7;
	private final double pos1 = 40*4; //test value only
	private final double pos2 = 70*4; //test value only
	private final double pos3 = 100*4;
	private final double pos4 = 130*4;
	
	public ToteBridgePosSubsystem() {
		super();
		primaryMotorController = new Talon(OI.BRIDGEMOTOR1_PORT);
		secondaryMotorController = new Talon(OI.BRIDGEMOTOR2_PORT);
		primaryLimitSwitch = new DigitalInput(OI.LIMITSWITCH1_PORT);
		secondaryLimitSwitch = new DigitalInput(OI.LIMITSWITCH2_PORT);
		primaryCounter = new Counter(primaryLimitSwitch);
		secondaryCounter = new Counter(secondaryLimitSwitch);
		encoder = new Encoder(aChannel, bChannel, true, EncodingType.k4X);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
       setDefaultCommand(new BridgeLock());
       SmartDashboard.putString("Encoder Command", "Locking Position");
    }
    
    public void startReset() {
    	SmartDashboard.putString("Encoder Subsystem", "reset method called");
    	SmartDashboard.putBoolean("Encoder Subsystem Switch", getSwitchStatus());
    	if (getSwitchStatus() == false) {
	    	setMotorSpeed(-0.4);
    	}
    }
    
    public void finishReset() {
    	stop();
    	initializePosition();
    	initializeCounter();
    	SmartDashboard.putNumber("Encoder Position In finishReset", encoder.getRaw());
    	position = 0;
    }
    
    public void stop() {
    	setMotorSpeed(0.0);
    }
    
    public double readEncoder() {
    	return encoder.getRaw();
    }
        
    public void initializePosition() {
    	//sets to default position
    	encoder.reset();
    }
    
    public void positionUp() {
    	//moves toward home pos from floor
    	if(position < maxPosition) {
    		position++;
    	}
    }
    
    public void positionDown() {
    	//moves away from home pos toward floor
    	if(position > minPosition) {
    		position--;
    	}  
    }
    
    public void initializeCounter() {
    	primaryCounter.reset();
    }
    
    public boolean getSwitchStatus() {
    	return getPrimSwitchStatus() && getSecSwitchStatus();
    }
    
    public boolean getPrimSwitchStatus() {
    	return primaryLimitSwitch.get() || (primaryCounter.get() > 0);
    }
    
    public boolean getSecSwitchStatus() {
    	return secondaryLimitSwitch.get() || secondaryCounter.get() > 0;
    }
    
    public void moveTowardTargetPosition() {
    	double currentSpeed = 1;
    	double finalSpeed = 0;
    	double posError = 10;
    	SmartDashboard.putString("Encoder Command", "Lock Speed Mode");

    	currentSpeed = getMotorSpeed();
    	posError = getTargetPosition() - encoder.getRaw();
        if (posError != 0.0) {
	    	currentSpeed = getMotorSpeed();
	    	posError = getTargetPosition() - encoder.getRaw();
	    	SmartDashboard.putNumber("Position Error", posError);
	    		    	
	    	if(posError >= posTolerance) {
	    		// increase speed toward floor if current position is lower than target (too close to the robot)
	    		SmartDashboard.putString("Lock Speed Command", "Incrementing Speed");
	    		finalSpeed = (currentSpeed + incGain*Math.abs(posError));
	    		finalSpeed = (finalSpeed * Math.abs(posError/(maxSpeed*100)));
	    	}
  
	    	if(posError <= -posTolerance) {
	    		// increase speed toward robot if current position is greater than target (too close to the floor)
	    		SmartDashboard.putString("Lock Speed Command", "Decrementing Speed");
	    		finalSpeed = (currentSpeed - incGain*Math.abs(posError));
	    		finalSpeed = (finalSpeed * Math.abs(posError/(maxSpeed*100)));

	    	}
	    	
	    	if(Math.abs(posError) < posTolerance) {
	    		// do nothing if the current position is within reasonable bounds
	    		SmartDashboard.putString("Lock Speed Command", "Doing Nothing");
	    		finalSpeed = currentSpeed;
	    	}
	    	
	    	SmartDashboard.putString("Lock Speed Command", "Setting Speed");
	    	if(Math.abs(finalSpeed) > maxSpeed) {
	    		int sign = (int) (Math.abs(finalSpeed)/finalSpeed);
	    		setMotorSpeed(sign*maxSpeed);
	    	}else{
	    		setMotorSpeed(finalSpeed);
	    	}
	    	
	    	//Timer.delay(0.005);
	    	
	    	SmartDashboard.putNumber("Final Speed", finalSpeed);
	    	SmartDashboard.putNumber("Encoder Position Actual", readEncoder());
	    	SmartDashboard.putNumber("Encoder Position Target", getTargetPosition());
	    	SmartDashboard.putString("Lock Speed Command", "Looping");
    	}
    	SmartDashboard.putString("Lock Speed Command", "idle");
    	return;
    }

	private void setMotorSpeed(double speed) {
		primaryMotorController.set(speed);
		secondaryMotorController.set(-speed);
	}

	private double getMotorSpeed() {
		return primaryMotorController.getSpeed();
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

