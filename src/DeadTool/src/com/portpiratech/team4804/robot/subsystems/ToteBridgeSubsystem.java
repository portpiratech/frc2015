package com.portpiratech.team4804.robot.subsystems;

import com.portpiratech.team4804.robot.commands.ToteBridge;
import com.portpiratech.xbox360.XboxController;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ToteBridgeSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// We will need a DigistalInput, Counter, Motor(Talon)
	
	DigitalInput limitSwitch = new DigitalInput(4);
	Counter counter = new Counter(limitSwitch);
	VictorSP motorController = new VictorSP(4);
	Talon leftMotor = new Talon(2);
	Talon rightMotor = new Talon(7);
	double multiplier = 0.5;
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new ToteBridge());
    }
    
    // Initialize Counter
    public void initializeCounter() {
    	counter.reset();
    }
    
    // Method Load this will execute the motor to drive 
    //public void load(XboxController xbox) {	
    //	if(isSwitchPressed() == false) { 
	//  		leftMotor.set(-xbox.getRightStickYAxis()*speed);
	//  		rightMotor.set(-xbox.getRightStickYAxis()*speed);    		
    //	}
	   
    //}
    
    //public void unload(XboxController xbox) {
    //	leftMotor.set(xbox.getRightStickYAxis()*speed);
	//	rightMotor.set(xbox.getRightStickYAxis()*speed);
    //}
    
    public void bridge(XboxController xbox) {
    	bridge(xbox.getRightStickYAxis());
    }
    
    public void bridge(double speed) {
    	SmartDashboard.putNumber("speed",multiplier*speed);
    	if(Math.abs(multiplier*speed) > .1) {
    		//leftMotor.set(multiplier*speed);
    		//rightMotor.set(multiplier*speed);
    		motorController.set(multiplier*speed);
    	}else{
    		motorController.set(0.0);
    		//leftMotor.set(0.0);
    		//rightMotor.set(0.0);
    	}
    }
    // Is switch thrown? (counter > 1)    
    public boolean getSwitchStatus() {
    	if(isSwitchPressed()) {
    		return true;
    	}
    	return counter.get() > 0;
    }
    
    public boolean isSwitchPressed() {
    	return limitSwitch.get();
    }
    
    // Stop loading
    public void stop() {
    	leftMotor.set(0.0);
		rightMotor.set(0.0);
    }
    
}

