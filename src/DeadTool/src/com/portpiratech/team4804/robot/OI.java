
package com.portpiratech.team4804.robot;

import com.portpiratech.xbox360.XboxController;
import com.portpiratech.team4804.robot.commands.ExtendPiston;
import com.portpiratech.team4804.robot.commands.RetractPiston;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private XboxController xbox = new XboxController(0);

    public OI() {
    	        
        // Connect the buttons to commands
    	if(xbox.isAButtonPressed() == true){
    		new ExtendPiston();
    		
    		
    	}
    	
    	if(xbox.isXButtonPressed() == true){
    		new RetractPiston();
    	}
       
       
    }
    
    public XboxController getJoystick() {
        return xbox;
    }

}

