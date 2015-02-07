
package com.portpiratech.team4804.robot;

import com.portpiratech.team4804.robot.commands.BridgeDown;
import com.portpiratech.team4804.robot.commands.BridgeReset;
import com.portpiratech.team4804.robot.commands.BridgeUp;
import com.portpiratech.team4804.robot.commands.ExtendPiston;
import com.portpiratech.team4804.robot.commands.RetractPiston;
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
    	operatorController.getAButton().whileHeld(new ExtendPiston());
    	operatorController.getBButton().whileHeld(new RetractPiston());
    	operatorController.getXButton().whenPressed(new BridgeUp());
    	operatorController.getYButton().whenPressed(new BridgeDown());
    	operatorController.getRightBumper().whenPressed(new BridgeReset());
    	// driverController.getXButton().whenPressed(new ReadEncoder());
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
