package org.usfirst.frc.team3042.robot;

import org.usfirst.frc.team3042.lib.Log;

import com.ctre.phoenix.motorcontrol.NeutralMode;

/** RobotMap ******************************************************************
 * The robot configuration file.
 */
public class RobotMap {
	/** Robot selector ********************************************************/
	public static enum Bot {PBOT, ARTEMIS;}
	// Set the bot to which you intend to push code.
	private static Bot currentBot = Bot.PBOT;

	public static final boolean IS_PBOT 	= (currentBot == Bot.PBOT);
	public static final boolean IS_ARTEMIS = (currentBot == Bot.ARTEMIS);
	
	
	/** Robot Size Parameters *************************************************
	 * The units of the wheel diameter determine the units of the position 
	 * and speed closed-loop commands. For example, if the diameter is given 
	 * in inches, position will be in inches and speed in inches per second.
	 */
	public static final double WHEEL_DIAMETER = 4.0;
	public static final double ROBOT_WIDTH = (IS_PBOT) ? 15.0 : 0.0;
	
	
	/** USB ports *************************************************************/					
	public static final int USB_JOYSTICK_LEFT 	= 0;
	public static final int USB_JOYSTICK_RIGHT 	= 1;
	public static final int USB_GAMEPAD 		= IS_PBOT ? 0 : 2;


	/** PWM ports *************************************************************/
	public static final int PWM_PAN_PORT 	= 0;
	public static final int PWM_TILT_PORT 	= 1;
	
	
	/** CAN ID numbers ********************************************************/
	public static final int CAN_LEFT_MOTOR 	= 		IS_PBOT 	? 3 :
													IS_ARTEMIS 	? 0 : 0;
	public static final int CAN_RIGHT_MOTOR = 		IS_PBOT 	? 9 :
													IS_ARTEMIS 	? 0 : 0;
	public static final int CAN_LEFT_FOLLOWER = 	IS_ARTEMIS 	? 0 : 0;
	public static final int CAN_RIGHT_FOLLOWER = 	IS_ARTEMIS 	? 0 : 0;
	public static final int CAN_SPINNER 	= 		IS_PBOT		? 10 :
													IS_ARTEMIS 	? 0 : 0;
	
	
	/** PCM channels **********************************************************/
	public static final int LIGHT_RING_CHANNEL = 1;
	

	/** SPI ports *************************************************************/
	public static final int LINE_TRACKER_PIXY_PORT = (IS_PBOT)? 0: 0;
	//note that the Gyroscope uses the myRIO Expansion Port (MXP) and is defined in the SPI class (edu.wpi.first.wpilibj.SPI)
	//notes for dummies: the MXP is the big boy smack center of the RoboRio (where the gyro ALWAYS goes);
	//see http://www.ni.com/pdf/manuals/374474a.pdf for additional info on the RoboRio
	
	/** OI Settings ***********************************************************/
	public static final boolean USE_JOYSTICKS = !IS_PBOT;
	public static final double JOYSTICK_DRIVE_SCALE = 0.5;
	public static final double TRIGGER_SPINNER_SCALE = 0.1;
	public static final double JOYSTICK_DEAD_ZONE = 0.0;


	/** Drivetrain Settings ***************************************************/
	public static final boolean HAS_DRIVETRAIN = true;
	public static final boolean HAS_FOLLOWERS = !IS_PBOT;
	public static final NeutralMode DRIVETRAIN_BRAKE_MODE = NeutralMode.Brake;
	public static final boolean REVERSE_LEFT_MOTOR = 	(IS_PBOT) ? true : false;
	public static final boolean REVERSE_RIGHT_MOTOR = 	(IS_PBOT) ? false: false;
	// Maximum Acceleration given in power per second
	public static final double ACCELERATION_MAX = 1.5;
	public static final double kF_DRIVE_LEFT = 	(IS_PBOT) 		?  0.1817180616740088 :
												(IS_ARTEMIS) 	? 0.0 : 0.0;
	public static final double kF_DRIVE_RIGHT = (IS_PBOT) 		?  0.16686239968682717 :
												(IS_ARTEMIS) 	? 0.0 : 0.0;
	
	
	/** Drivetrain Encoder Settings *******************************************/
	public static final boolean HAS_ENCODERS = true;
	//Encoder counts per revolution
	//In quadrature mode, actual counts will be 4x this; e.g., 360 -> 1440
	public static final int COUNTS_PER_REVOLUTION = 1440;
	//How often the encoders update on the CAN, in milliseconds
	public static final int ENCODER_FRAME_RATE = 10;
	public static final boolean SENSOR_PHASE_LEFT = 	(IS_PBOT) ? false: false;
	public static final boolean SENSOR_PHASE_RIGHT = 	(IS_PBOT) ? false: false;
	
	
	/** Drivetrain Autonomous Settings ****************************************/
	public static final boolean HAS_AUTON = HAS_ENCODERS;
	public static final int AUTON_PROFILE = 0;
	public static final double kP_AUTON = 		(IS_PBOT) 		? 0.4 :
												(IS_ARTEMIS) 	? 0.0 : 0.0;
	public static final double kI_AUTON = 		(IS_PBOT) 		? 0.0 :
												(IS_ARTEMIS) 	? 0.0 : 0.0;
	public static final double kD_AUTON = 		(IS_PBOT) 		? 0.8 :
												(IS_ARTEMIS) 	? 0.0 : 0.0;
	public static final int I_ZONE_AUTON =		(IS_PBOT)		? 0 :
												(IS_ARTEMIS)	? 0 : 0;
	//The rate of pushing motion profile points to the talon, in ms
	public static final int AUTON_FRAME_RATE = 10;
	//Parameters for calibrating the F-gain
	public static final double AUTON_CALIBRATE_POWER = 0.5;
	public static final double AUTON_CALIBRATE_TIME = 5.0; //seconds
	public static final int AUTON_COUNT_AVERAGE = 20;
	//Parameters for motion profile driving
	public static final int AUTON_DT_MS = 30;
	public static final double AUTON_DT_SEC = (double)AUTON_DT_MS / 1000.0;
	public static final double AUTON_ACCEL_TIME = 1.0; //time in sec
	public static final double AUTON_SMOOTH_TIME = 0.1; //time in sec
	public static final double AUTON_MAX_ACCEL = 3.0; //rev per sec per sec
	public static final int AUTON_BUFFER_TRIGGER = 10;
	public static final int AUTON_TIMEOUT = 0; // timeout in ms; set to zero
	public static final int AUTON_PIDIDX = 0; // used for cascading PID; set to zero
	public static final int AUTON_HEADING = 0; //unimplemented feature; set to zero
	
	
	/** Drivetrain Gyro Drive Settings ****************************************/
	public static final double kP_GYRO = 0.0175;
	public static final double kI_GYRO = 0.0;
	public static final double kD_GYRO = 0.0170;
	public static final double ANGLE_TOLERANCE = 2.0;
	public static final double MAX_SPEED_GYRO = 0.4;
	public static final double kI_GYRO_INTERVAL = 0.0;
	
	
	/** Spinner Settings ******************************************************/
	public static final boolean HAS_SPINNER = IS_PBOT;
	public static final NeutralMode SPINNER_BRAKE_MODE = NeutralMode.Brake;
	public static final boolean REVERSE_SPINNER = false;
	
	
	/** Spinner Encoder Settings **********************************************/
	public static final boolean HAS_SPINNER_ENCODER = HAS_SPINNER;
	public static final int SPINNER_ENCODER_FRAME_RATE = 10;
	public static final int SPINNER_ENCODER_COUNTS_PER_REV = 4096;
	public static final boolean REVERSE_SPINNER_ENCODER = false;
	public static final boolean SPINNER_SENSOR_PHASE = false;
	
	
	/** Spinner Closed-Loop Settings ******************************************/
	public static final boolean HAS_SPINNER_CLOSED_LOOP = HAS_SPINNER;
	public static final int SPINNER_POSITION_PROFILE = 0;
	public static final double kP_SPINNER_POSITION = 0.51;
	public static final double kI_SPINNER_POSITION = 0.0;
	public static final double kD_SPINNER_POSITION = 5.1;
	public static final double kF_SPINNER_POSITION = 0.0; //Should be set to zero
	public static final int I_ZONE_SPINNER_POSITION = 0;
	public static final int SPINNER_SPEED_PROFILE = 1;
	public static final double kP_SPINNER_SPEED = 0.05;
	public static final double kI_SPINNER_SPEED = 0.0;
	public static final double kD_SPINNER_SPEED = 0.5;
	public static final double kF_SPINNER_SPEED = 0.036;
	public static final int I_ZONE_SPINNER_SPEED = 0;
	public static final double SPINNER_DEFAULT_POSITION = 1.0; //revolutions
	public static final double SPINNER_DEFAULT_SPEED = 500; //RPM
	public static final double SPINNER_CALIBRATE_POWER = 0.2;
	public static final double SPINNER_CALIBRATE_TIME = 10.0; //seconds
	public static final int SPINNER_COUNT_AVERAGE = 20;
	public static final int SPINNER_TIMEOUT = 0; // timeout in ms; set to zero
	public static final int SPINNER_PIDIDX = 0; // used for cascading PID; set to zero
	public static final int SPINNER_CRUISE = 500; //RPM
	public static final int SPINNER_ACCEL = 500; //RPM per sec
	
	
	/** PanTilt Settings ******************************************************/
	public static final boolean HAS_PAN_TILT = false;
	//PWM bounds are for the HS-5685MH servo
	public static final double SERVO_PWM_MAX = 2.25;
	public static final double SERVO_PWM_MIN = 0.76;
	public static final double PAN_MIN = 0.25;
	public static final double PAN_CENTER = 0.430;
	public static final double PAN_MAX = 0.7;
	public static final double TILT_MIN = 0.0;
	public static final double TILT_CENTER 	= 0.515;
	public static final double TILT_MAX = 0.7;
	//The change in servo position per second when driven with the POV buttons
	public static final double SERVO_SPEED = 0.25;
	//Reverse the direction of the servos if they don't match the controls
	public static final boolean REVERSE_PAN = false;
	public static final boolean REVERSE_TILT = false;
	
	
	/** Gyroscope Settings ****************************************************/
	public static final boolean HAS_GYROSCOPE = true;
	public static final double GYROSCOPE_SCALE = 1.0;
	
	
	/** LEDRing Settings ******************************************************/
	public static final boolean HAS_LIGHT_RING = true;

	/** LineTracker Settings ******************************************************/
	public static final boolean HAS_LINE_TRACKER = false;
	
	/** Logger Settings *******************************************************/
	public static final String 		LOG_FILE_FORMAT = "yyyy-MM-dd-hhmmss";
	public static final String 		LOG_TIME_FORMAT = "hh:mm:ss:SSS";
	public static final String 		LOG_DIRECTORY_PATH = "/home/lvuser/logs/";
	public static final String 		LOG_TIME_ZONE = "America/Chicago";
	public static final boolean 	LOG_TO_CONSOLE 				= true;
	public static final boolean 	LOG_TO_FILE 				= false;
	public static final Log.Level 	LOG_GLOBAL 					= Log.Level.DEBUG;
	public static final Log.Level 	LOG_ROBOT 					= Log.Level.TRACE;
	public static final Log.Level	LOG_OI 						= Log.Level.TRACE;
	public static final Log.Level	LOG_AXIS_TRIGGER 			= Log.Level.ERROR;
	public static final Log.Level	LOG_POV_BUTTON				= Log.Level.ERROR;
	/** Subsystems **/
	public static final Log.Level	LOG_DRIVETRAIN				= Log.Level.TRACE;
	public static final Log.Level	LOG_DRIVETRAIN_FOLLOWERS	= Log.Level.TRACE;
	public static final Log.Level	LOG_DRIVETRAIN_ENCODERS 	= Log.Level.DEBUG;
	public static final Log.Level	LOG_DRIVETRAIN_AUTON		= Log.Level.DEBUG;
	public static final Log.Level	LOG_SPINNER					= Log.Level.TRACE;
	public static final Log.Level	LOG_SPINNER_ENCODER			= Log.Level.TRACE;
	public static final Log.Level	LOG_SPINNER_CLOSED_LOOP		= Log.Level.DEBUG;
	public static final Log.Level 	LOG_PAN_TILT 				= Log.Level.TRACE;
	public static final Log.Level	LOG_GYROSCOPE				= Log.Level.DEBUG;
	public static final Log.Level	LOG_LIGHT_RING				= Log.Level.TRACE;
	public static final Log.Level   LOG_LINE_TRACKER			= Log.Level.TRACE;
	public static final Log.Level	LOG_EXAMPLE_SUBSYSTEM 		= Log.Level.TRACE;
}