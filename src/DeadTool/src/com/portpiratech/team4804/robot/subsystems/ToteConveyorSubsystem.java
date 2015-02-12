package com.portpiratech.team4804.robot.subsystems;

import com.portpiratech.team4804.robot.commands.ToteConveyor;
import com.portpiratech.xbox360.XboxController;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ToteConveyorSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// We will need a DigistalInput, Counter, Motor(Talon)
	
	Talon conveyorMotor = new Talon(3);
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new ToteConveyor());
    }
    
    
    public void conveyor(XboxController xbox) {
    	conveyor(xbox.getLeftStickYAxis());
    }
    
    public void conveyor(double speed) {
    	if(Math.abs(speed) > .1) {
        	SmartDashboard.putNumber("Bridge Speed Reference", speed);

    		conveyorMotor.set(speed);
    	} else {
    		conveyorMotor.set(0.0);
    	}
    }
    
    
    // Is switch thrown? (counter > 1)    
   
    public void stop() {
    	conveyorMotor.set(0.0);
    }
    
}

