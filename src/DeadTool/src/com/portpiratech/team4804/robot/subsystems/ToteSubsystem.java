package com.portpiratech.team4804.robot.subsystems;

import com.portpiratech.xbox360.XboxController;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ToteSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// We will need a DigistalInput, Counter, Motor(Talon)
	
	DigitalInput limitSwitch = new DigitalInput(0);
	Counter counter = new Counter(limitSwitch);
	Talon leftMotor = new Talon(3);
	Talon rightMotor = new Talon(4);
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    // Initialize Counter
    public void initializeCounter() {
    	counter.reset();
    }
    
    // Method Load this will execute the motor to drive 
    public void load(XboxController xbox) {
    	if(isSwitchPressed() == false) { 
    		leftMotor.set(xbox.getRightStickYAxis());
    		rightMotor.set(-xbox.getRightStickYAxis());
    	}
    }
    
    public void unload(XboxController xbox) {
    	leftMotor.set(-xbox.getRightStickYAxis());
		rightMotor.set(xbox.getRightStickYAxis());
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

