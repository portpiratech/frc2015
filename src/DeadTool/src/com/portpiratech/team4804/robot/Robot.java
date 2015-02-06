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
import com.portpiratech.team4804.robot.subsystems.PistonTestSubsystem;
import com.portpiratech.team4804.robot.subsystems.ToteBridgeSubsystem;
import com.portpiratech.team4804.robot.subsystems.ToteConveyorSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    Command autonomousCommand;
    
    public static PistonTestSubsystem pistonTestSubsystem;
    public static DriveTrain drivetrain;
    public static ToteBridgeSubsystem toteBridgeSubsystem;
    public static ToteConveyorSubsystem toteConveyorSubsystem;
    public static EncoderSubsystem encoderSubsystem;
    public static OI oi;
    
    

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // Initialize all subsystems
        drivetrain = new DriveTrain();
        pistonTestSubsystem = new PistonTestSubsystem();
        toteBridgeSubsystem = new ToteBridgeSubsystem();
        toteConveyorSubsystem = new ToteConveyorSubsystem();
        encoderSubsystem = new EncoderSubsystem();
        oi = new OI();

        
        // instantiate the command used for the autonomous period
        autonomousCommand = new Autonomous();

        // Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData(drivetrain);
    
       
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
       
        drivetrain.log();
    }
}
