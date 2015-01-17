package com.portpiratech.team4804.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LoadingSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// We will need a DigitalInput, Counter, Motor(Talon)
	
	DigitalInput limitSwitch = new DigitalInput(0);
	Counter counter = new Counter(limitSwitch);
	Talon motor = new Talon(2);
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    // Initialize Counter
    public void initializeCounter() {
    	counter.reset();
    }
    
    // Method Load this will execute the motor to drive 
    public void load() {
    	if(isSwitchPressed() == false) { 
    		motor.set(0.5);
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
    public void stop(){
    	motor.set(0.0);
    }
    
}

