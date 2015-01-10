
package com.portpiratech.team4804.robot;

import com.portpiratech.xbox360.XboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private XboxController xbox = new XboxController(0);

    public OI() {
    	        
        // Connect the buttons to commands
       
       
    }
    
    public XboxController getJoystick() {
        return xbox;
    }
}

