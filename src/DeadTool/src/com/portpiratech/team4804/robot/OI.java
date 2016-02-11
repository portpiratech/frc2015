
package com.portpiratech.team4804.robot;

import com.portpiratech.team4804.robot.commands.BridgeDown;
import com.portpiratech.team4804.robot.commands.BridgeDrop;
import com.portpiratech.team4804.robot.commands.BridgeReset;
import com.portpiratech.team4804.robot.commands.BridgeUp;
import com.portpiratech.team4804.robot.commands.DriveToggle;
import com.portpiratech.team4804.robot.commands.ExtendLift;
import com.portpiratech.team4804.robot.commands.RetractLift;
import com.portpiratech.team4804.robot.commands.SpeedDown;
import com.portpiratech.team4804.robot.commands.SpeedUp;
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
    public static final int DRIVEMOTOR1_PORT = 0; //Talon
    public static final int DRIVEMOTOR2_PORT = 1; //Talon
    public static final int BRIDGEMOTOR1_PORT = 2; //Talon
    public static final int BRIDGEMOTOR2_PORT = 3; //Talon
    public static final int CONVEYORMOTOR_PORT = 4; //VictorSP
    public static final int LIMITSWITCH1_PORT = 2; //DigitalInput
    public static final int LIMITSWITCH2_PORT = 3; //DigitalInput
    public static final int SOLENOID1_PORT1 = 0; //DoubleSolenoid
    public static final int SOLENOID1_PORT2 = 1; //DoubleSolenoid
    public static final int SOLENOID2_PORT1 = 2; //DoubleSolenoid
    public static final int SOLENOID2_PORT2 = 4; //DoubleSolenoid
    
    public OI() {
    	switch(Robot.currentMode) {
    	case NORMAL_MODE:
	        // Connect the buttons to commands
	    	operatorController.getAButton().whileHeld(new ExtendLift());
	    	operatorController.getBButton().whileHeld(new RetractLift());
	    	operatorController.getXButton().whenPressed(new BridgeUp());
	    	operatorController.getYButton().whenPressed(new BridgeDown());
	    	operatorController.getRightBumper().whenPressed(new BridgeReset());
	    	operatorController.getLeftBumper().whenPressed(new BridgeDrop());
	    	//driverController.getRightBumper().whenPressed(new ArmsUp());
	    	//driverController.getLeftBumper().whenPressed(new ArmsDown());
	    	break;
    	case DUMMY_MODE:
    		//Connect the buttons to commands (only on driver controller, no operator)
    		driverController.getAButton().whenPressed(new DriveToggle());
    		driverController.getLeftBumper().whileHeld(new ExtendLift());
    		driverController.getRightBumper().whileHeld(new RetractLift());
	    	driverController.getXButton().whenPressed(new SpeedUp());
	    	driverController.getYButton().whenPressed(new SpeedDown());
	    	
	    	//kill switch
	    	//operatorController.getAButton().whenPressed(new Disable());
    		break;
    	}
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
