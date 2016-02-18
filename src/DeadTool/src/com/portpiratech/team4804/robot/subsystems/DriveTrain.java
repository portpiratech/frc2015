package com.portpiratech.team4804.robot.subsystems;

import com.portpiratech.team4804.robot.OI;
import com.portpiratech.team4804.robot.Robot;
import com.portpiratech.team4804.robot.commands.TankDriveWithJoystick;
import com.portpiratech.xbox360.XboxController;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem for controlling the two drive motors 
 */
public class DriveTrain extends PIDSubsystem {
	private SpeedController left_motor,
							right_motor;
	private RobotDrive drive;
	
	static double speedMult; //speed multiplier
	static double lowSpeed;
	static double highSpeed;
	static double dpadMult;
	
	static final double SPEED_TOLERANCE = 0.075;
	static int driveSetting = 0;
	
	public double p;
	public double i, d;

	public DriveTrain() {
		super(0.1, 0.0, 0.0);	//initial PID constants
		left_motor = new Talon(OI.DRIVEMOTOR2_PORT);
		right_motor = new Talon(OI.DRIVEMOTOR1_PORT);
		
		drive = new RobotDrive(left_motor, right_motor);
		
		switch(Robot.currentMode) {
		case NORMAL_MODE:
			lowSpeed = 0.65;
			highSpeed = 0.8;
			break;
			
		case DUMMY_MODE:
			lowSpeed = 0.4;
			highSpeed = 0.5;
			break;
		}
		
		speedMult = highSpeed;
		dpadMult = 0.8;
		
		getPIDController().setContinuous(false);
		getPIDController().setAbsoluteTolerance(0.05);
	}

	/**
	 * When no other command is running let the operator drive around
	 * using the Xbox joystick.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new TankDriveWithJoystick());
	}

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	public void log() {
	}
	
	/**
	 * Increases speed multiplier
	 */
	public void speedUp() {
		speedMult = highSpeed;
	}
	
	/**
	 * Decreases speed multiplier
	 */
	public void speedDown() {
		speedMult = lowSpeed;
	}

	/**
	 * Tank style driving for the DriveTrain. 
	 * @param left Speed in range [-1,1]
	 * @param right Speed in range [-1,1]
	 */
	/*public void drive(double left, double right) {
		drive.tankDrive(-left*speedMult, -right*speedMult);
	}*/

	/**
	 * @param joy The ps3 style joystick to use to drive tank style.
	 */
	/*public void drive(XboxController xbox) {
		//drive(-joy.getY(), -joy.getAxis(AxisType.kThrottle));
		drive(xbox.getLeftStickYAxis(),xbox.getRightStickYAxis());
	}*/
	
	/**
	 * Sets motor speeds.
	 * @param side String, either "L" or "R"
	 * @param speed Speed in range [-1,1]
	 */
    public void setMotor(String side, double speed) {
    	// side "L" = left
    	// side "R" = right
    	
    	if (Math.abs(speed) < SPEED_TOLERANCE) speed = 0; //controller axis always returns insignificant values, fixes creeping
    		
    	switch(side) {
    		case "L":
    			left_motor.set(-speed);
    			SmartDashboard.putNumber("Drive train left:", -speed);
    			break;
    		case "R": 
    			right_motor.set(speed);
    			SmartDashboard.putNumber("Drive train right:", speed);
    			break;
    	}
    }
    
    /**
     * Runs the robot from the xbox controller.
     * @param xbox Xbox controller input.
     */
    public void drive(XboxController xbox) {
    	
    	switch(driveSetting){
    	case 0:
    		tankDrive(xbox.getLeftStickYAxis(), xbox.getRightStickYAxis());
    		SmartDashboard.putString("Drive Setting", "Tank Drive");
    		break;
    	case 1:
    		jonnyDrive(xbox.getLeftStickYAxis(), xbox.getLeftStickXAxis(), xbox.getDPad(), xbox.getRightStickXAxis());
    		SmartDashboard.putString("Drive Setting", "Jonny Drive");
    		break;
    	case 2:
    		tommyDrive(xbox.getLeftStickXAxis(), xbox.getLeftStickYAxis(), xbox.getDPad());
    		SmartDashboard.putString("Drive Setting", "Tommy Drive");
    		break;
    	}
    }
    
    /**
     * Changes the drive mode. 0=tank, 1=jonny, 2=tommy.
     */
    public void toggleDriveSetting(){ //mapped to A button on driver's controller
    	driveSetting++;
    	if (driveSetting >= 3){
    		driveSetting = 0;
    	}
    }
    
    // uses two joysticks, left stick y-axis and right stick y-axis
    /**
     * Tank-style driving method.
     * @param leftY Left joystick's y-value. Speed in range [-1,1] controlling the left motors
     * @param rightY Right joystick's y-value. Speed in range [-1,1] controlling the right motors
     */
    public void tankDrive(double leftY, double rightY) {
    	setMotor("L", leftY*speedMult);
    	setMotor("R", rightY*speedMult);
    }
    
    // uses one joystick, left stick y-axis for magnitude, x-axis for direction. car style
    /**
     * Jonny's custom drive method. 
     * @param leftY Left joystick's y-value (in range [-1,1]).
     * @param leftX Left joystick's x-value (in range [-1,1]).
     * @param dpad Dpad input value 0, 2, 4, or 6. Strict directional driving (forward, turn right, backward, turn left).
     * @param rightX Right joystick's x-value. Speed in range [-1,1] controlling miniscule turning.
     */
    public void jonnyDrive(double leftY, double leftX, int dpad, double rightX){ //left stick's y value and left stick's x value
    											//xSpeed and ySpeed range from -1 to 1 based on % of max speed
    	if (dpad == -1){
	    	double leftMotorSpeed = leftY;
	    	double rightMotorSpeed = leftY;
	    	
	    	if (true){                   //increments motor speeds for turning
	    		leftMotorSpeed += -leftX;
	        	rightMotorSpeed += leftX;
	    	}else{
	    		leftMotorSpeed += leftX;
	        	rightMotorSpeed += -leftX;
	    	}
	    	
	    	if (leftMotorSpeed > 1.0)  leftMotorSpeed =  1.0;  //fixes values from being out of bounds
	    	if (leftMotorSpeed < -1.0) leftMotorSpeed = -1.0;
	    	if (rightMotorSpeed > 1.0)  rightMotorSpeed =  1.0;
	    	if (rightMotorSpeed < -1.0) rightMotorSpeed = -1.0;
	    	
	    	if (Math.abs(leftMotorSpeed) < SPEED_TOLERANCE && Math.abs(rightMotorSpeed) < SPEED_TOLERANCE){ //use the right stick for miniscule turning
	    		leftMotorSpeed = -rightX * dpadMult;
	    		rightMotorSpeed = rightX * dpadMult;
	    	}
	    	
	    	setMotor("L", leftMotorSpeed*speedMult);
	    	setMotor("R", rightMotorSpeed*speedMult);
    	}else{
    		switch(dpad){ //default dpad directions (perfectly straight, back, cw, ccw);
    					  //negative is forward specifically here... for some reason...
    		case 0: //forward
    			setMotor("L", -speedMult*dpadMult);
    			setMotor("R", -speedMult*dpadMult);
    			break;
    		case 2: //cw
    			setMotor("L", -speedMult*dpadMult);
    			setMotor("R", speedMult*dpadMult);
    			break;
    		case 4: //backward
    			setMotor("L", speedMult*dpadMult);
    			setMotor("R", speedMult*dpadMult);
    			break;
    		case 6: //ccw
    			setMotor("L", speedMult*dpadMult);
    			setMotor("R", -speedMult*dpadMult);
    			break;
    		}
    	}
    }
    
    // uses one joystick, left stick x- and y-axis, vector style
    /**
     * Tommy's custom drive method. Vector-style driving (based on angle + magnitude of the left joystick).
     * @param leftX Left joystick's x-value (in range [-1,1]).
     * @param leftY Left joystick's y-value (in range [-1,1]).
     * @param dpad Dpad input value 0, 2, 4, or 6. Strict directional driving (forward, turn right, backward, turn left).
     */
    public void tommyDrive(double leftX, double leftY, int dpad) {
    	
    	leftX *= -1; //corrects input value
    	
    	if(dpad == -1) {
    		//calculate the angle. arctangent returns values between -pi/2 and +pi/2, so correct it.
	    	double angle = Math.atan(leftY/leftX);
	    //	if(leftX>=0 && leftY>=0) angle = angle;		// quadrant I
	    	if(leftX< 0 && leftY>=0) angle += Math.PI;	// quadrant II
	    	if(leftX< 0 && leftY< 0) angle += Math.PI;	// quadrant III
	    	if(leftX>=0 && leftY< 0) angle += 2*Math.PI;	// quadrant IV
	    	
	    	double magnitude = Math.sqrt( Math.pow(leftX, 2) + Math.pow(leftY, 2) );
	    	
	    	double leftMotorSpeed = 0;
	    	double rightMotorSpeed = 0;
	    	int quadrant = 0;
	    	
	    	if(angle>=0 && angle<Math.PI/2) {
	    		leftMotorSpeed = 1;
	    		rightMotorSpeed = -Math.cos(2*angle);
	    		quadrant = 1;
	    	}
	    	if(angle>=Math.PI/2 && angle<Math.PI) {
	    		leftMotorSpeed = -Math.cos(2*angle);
	    		rightMotorSpeed = 1;
	    		quadrant = 2;
	    	}
	    	if(angle>=Math.PI && angle<Math.PI*3/2) {
	    		leftMotorSpeed = -1;
	    		rightMotorSpeed = Math.cos(2*angle);
	    		quadrant = 3;
	    	}
	    	if(angle>=Math.PI*3/2 && angle<=2*Math.PI) {
	    		leftMotorSpeed = Math.cos(2*angle);
	    		rightMotorSpeed = -1;
	    		quadrant = 4;
	    	}
	    	
	    //  scale motor speeds to joystick magnitude, max speed
	    	leftMotorSpeed *= magnitude*speedMult;
	    	rightMotorSpeed *= magnitude*speedMult;
	    	
	    	/*SmartDashboard.putNumber("Angle:", angle);
	    	SmartDashboard.putNumber("Magnitude:", magnitude);
	    	SmartDashboard.putNumber("Quadrant:", quadrant);*/
	    	
	    	setMotor("L", leftMotorSpeed);
	    	setMotor("R", rightMotorSpeed);
	    }else{
			switch(dpad){ //default dpad directions (perfectly straight, back, cw, ccw);
						  //negative is forward specifically here... for some reason...
			case 0: //forward
				setMotor("L", -speedMult*dpadMult);
				setMotor("R", -speedMult*dpadMult);
				break;
			case 2: //cw
				setMotor("L", -speedMult*dpadMult);
				setMotor("R", speedMult*dpadMult);
				break;
			case 4: //backward
				setMotor("L", speedMult*dpadMult);
				setMotor("R", speedMult*dpadMult);
				break;
			case 6: //ccw
				setMotor("L", speedMult*dpadMult);
				setMotor("R", -speedMult*dpadMult);
				break;
			}
		}
    }
    
  //crude camera centering method--need to test
    public void autoCenterCamera() {
    	double error = Robot.vision.errorAimingX;
    	double speed = 0;
    	
    	//center at error=0
    	
    	if(Math.abs(error)>0.01) speed = error*speedMult*4; //if error<0, then speed<0. if error>0, then speed>0.
    	if(Math.abs(speed)>1) speed = Math.signum(speed);
    	
    	setMotor("L", -speed);
		setMotor("R", speed);
    }

	/**
	 * Reset the robots sensors to the zero states.
	 */
	public void reset() {
	}

	@Override
	protected double returnPIDInput() {
		return Robot.vision.errorAimingX;
	}

	@Override
	protected void usePIDOutput(double output) {
		setMotor("L", output);
		setMotor("R", -output);
	}
	
	public void enablePID() {
		getPIDController().enable();
	}
	
	public void enablePID(boolean enable) {
		if(enable) {
			getPIDController().enable();
		} else {
			getPIDController().disable();
		}
	}
	
	/*@Override
	protected bool getEnable*/
}
