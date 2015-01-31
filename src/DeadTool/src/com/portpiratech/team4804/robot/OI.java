
package com.portpiratech.team4804.robot;

import com.portpiratech.team4804.robot.commands.ExtendPiston;
import com.portpiratech.team4804.robot.commands.LoadTote;
import com.portpiratech.team4804.robot.commands.RetractPiston;
import com.portpiratech.team4804.robot.commands.UnloadTote;
import com.portpiratech.xbox360.XboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private static final int OPERATOR_CONTROLLER_PORT = 1;
	private static final int DRIVER_CONTROLLER_PORT = 0;
	private XboxController driverController = new XboxController(DRIVER_CONTROLLER_PORT);
    private XboxController operatorController = new XboxController(OPERATOR_CONTROLLER_PORT);

    public OI() {
        // Connect the buttons to commands
<<<<<<< HEAD
    	operatorController.getAButton().whileHeld(new ExtendPiston());
    	operatorController.getBButton().whileHeld(new RetractPiston());
    	operatorController.getXButton().whenPressed(new LoadContainer());
        operatorController.getYButton().whenPressed(new ExtendPiston());
=======
    	xbox.getAButton().whileHeld(new ExtendPiston());
        xbox.getBButton().whileHeld(new RetractPiston());
        xbox.getXButton().whenPressed(new LoadTote());
        xbox.getYButton().whileHeld(new UnloadTote());
>>>>>>> 3e8eef7da34d34c50830ccae585ba7e655b4340d
    }
    
    /**
     * Method to retrieve First XBox Controller
     * 
     * @return XBox Controller One
     */
    public XboxController getDriverController() {
        return driverController;
    }
    public XboxController getOperatorController() {
        return operatorController;
    }
}
