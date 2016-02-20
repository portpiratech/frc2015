/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.portpiratech.team4804.robot;

import com.portpiratech.team4804.robot.commands.Autonomous;
import com.portpiratech.team4804.robot.subsystems.DriveTrain;
import com.portpiratech.team4804.robot.subsystems.EncoderSubsystem;
import com.portpiratech.team4804.robot.subsystems.PistonSubsystem;
import com.portpiratech.team4804.robot.subsystems.ToteBridgePosSubsystem;
import com.portpiratech.team4804.robot.subsystems.ToteConveyorSubsystem;
import com.portpiratech.team4804.robot.subsystems.VisionSubsystem;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
// #####################################################################################################################
// ### Set robot mode here ### * Normal mode: operates normally														  ##
// ########################### * Dummy mode: only uses one controller; only low-speed driving and pistons are enabled.##
	
	public static RobotModes currentMode = RobotModes.DUMMY_MODE;
//	public static RobotModes currentMode = RobotModes.NORMAL_MODE;
	
	
  //Initialization
    Command autonomousCommand;
    
    //Subsystems and classes
    public static PistonSubsystem pistonSubsystem;
    public static DriveTrain driveTrain;
    public static ToteConveyorSubsystem toteConveyorSubsystem;
    public static ToteBridgePosSubsystem toteBridgePosSubsystem;
    public static VisionSubsystem visionSubsystem;
    public static EncoderSubsystem encoderSubsystem;
    public static OI oi;
    public static VisionDeadTool vision;
    
    public static CANTalon cannonEncoderMotor;
    //CameraServer server;
    
    public Robot() {
        /*server = CameraServer.getInstance();
        server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture("cam0");
        server = CameraServer.getInstance();
        server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture("cam0");*/
    }
   
    /**
     * start up automatic capture you should see the video stream from the
     * webcam in your FRC PC Dashboard.
     */
    public void operatorControl() {

        while (isOperatorControl() && isEnabled()) {
            /** robot code here! **/
            Timer.delay(0.005);		// wait for a motor update time
        }
    }

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        switch(currentMode) {
        case NORMAL_MODE:
	    	// Initialize all subsystems
	        driveTrain = new DriveTrain();
	        pistonSubsystem = new PistonSubsystem();
	        toteBridgePosSubsystem = new ToteBridgePosSubsystem();
	        toteConveyorSubsystem = new ToteConveyorSubsystem();
	        visionSubsystem = new VisionSubsystem();
	        oi = new OI();
	        vision = new VisionDeadTool();
	        
	        // instantiate the command used for the autonomous period
	        autonomousCommand = new Autonomous();
	
	        // Show what command your subsystem is running on the SmartDashboard
	        
	        break;
	        
        case DUMMY_MODE:
        	driveTrain = new DriveTrain();
        	pistonSubsystem = new PistonSubsystem();
        	visionSubsystem = new VisionSubsystem();
        	encoderSubsystem = new EncoderSubsystem();
        	oi = new OI();
        	vision = new VisionDeadTool();
        	
        	//encoder thing
        	cannonEncoderMotor = new CANTalon(6);
        	
        	// instantiate the command used for the autonomous period
	        autonomousCommand = new Autonomous();
        	break;
        }
    }

    public void autonomousInit() {
        autonomousCommand.start(); // schedule the autonomous command (example)
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        log();
    }

    public void teleopInit() {
    	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        log();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
    private void log() {
       
        driveTrain.log();
    }
}
