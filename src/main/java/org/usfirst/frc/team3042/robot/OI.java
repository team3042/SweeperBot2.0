package org.usfirst.frc.team3042.robot;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.commands.BigPiston_Toggle;
//import org.usfirst.frc.team3042.lib.Path;
//import org.usfirst.frc.team3042.robot.commands.DrivetrainAuton_Drive;
import org.usfirst.frc.team3042.robot.commands.Drivetrain_BasketballCourt;
import org.usfirst.frc.team3042.robot.commands.Drivetrain_GyroStraight;
import org.usfirst.frc.team3042.robot.commands.Drivetrain_GyroTurn;
import org.usfirst.frc.team3042.robot.commands.SmallPiston_Toggle;

/** OI ************************************************************************
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {	
	/** Configuration Constants ***********************************************/
	private static final boolean IS_PBOT = RobotMap.IS_PBOT;
	private static final boolean IS_SWEEPERBOT = RobotMap.IS_SWEEPERBOT;
	private static final int USB_GAMEPAD = RobotMap.USB_GAMEPAD;
	private static final double JOYSTICK_DRIVE_SCALE = RobotMap.JOYSTICK_DRIVE_SCALE;
	private static final double JOYSTICK_DEAD_ZONE = RobotMap.JOYSTICK_DEAD_ZONE;
	private static final double TRIGGER_SPINNER_SCALE = RobotMap.TRIGGER_SPINNER_SCALE;
	//private static final double ROBOT_WIDTH = RobotMap.ROBOT_WIDTH;
	private static final int GAMEPAD_LEFT_Y_AXIS = Gamepad.LEFT_JOY_Y_AXIS;
	private static final int GAMEPAD_RIGHT_Y_AXIS = Gamepad.RIGHT_JOY_Y_AXIS;
	private static final int GAMEPAD_LEFT_TRIGGER = Gamepad.LEFT_TRIGGER;
	private static final int GAMEPAD_RIGHT_TRIGGER = Gamepad.RIGHT_TRIGGER;
	
	/** Instance Variables ****************************************************/
	Log log = new Log(RobotMap.LOG_OI, "OI");
	Gamepad gamepad, joyLeft, joyRight;
	int driveAxisLeft, driveAxisRight;

	/** OI ********************************************************************
	 * Assign commands to the buttons and triggers
	 */
	public OI() {
		log.add("OI Constructor", Log.Level.TRACE);
		
		gamepad = new Gamepad(USB_GAMEPAD);
		
		/** Setup Driving Controls ********************************************/
		joyLeft = gamepad;
		joyRight = gamepad;
		driveAxisLeft = GAMEPAD_LEFT_Y_AXIS;
		driveAxisRight = GAMEPAD_RIGHT_Y_AXIS;
		
		/** PBOT Controls *****************************************************/
		if (IS_PBOT) {

			/*Big gyroscope needs to be fixed?*/
			gamepad.X.whenPressed(new Drivetrain_GyroStraight(36, 24.0)); 
			gamepad.Y.whenPressed(new Drivetrain_GyroTurn(90.0));

			gamepad.Start.whenPressed(new Drivetrain_BasketballCourt());

			/*Code For Autonomous Driving*/

			/*double turnRadius = 1.5 * ROBOT_WIDTH;
			Path basketballCourt = new Path();

			basketballCourt.addStraight(36.0, 18.0);
			basketballCourt.addRightTurn(90.0, turnRadius, 21.0);
			basketballCourt.addStraight(8.0, 18.0);
			basketballCourt.addRightTurn(90.0, turnRadius, 21.0);
			basketballCourt.addStraight(36.0, 18.0);
			basketballCourt.addRightTurn(90.0, turnRadius, 21.0);
			basketballCourt.addStraight(8.0, 18.0);
			basketballCourt.addRightTurn(90.0, turnRadius, 21.0);

			gamepad.A.whenPressed(new DrivetrainAuton_Drive(basketballCourt));*/
			
			//double turnInPlace = 0.5 * ROBOT_WIDTH;
			//Path testPath2 = new Path();
			//testPath2.addLeftTurn(380.0, turnInPlace, 21.0);
			//testPath2.addRightTurn(420.0, turnInPlace, 21.0);
		}
		
		/** SweeperBot Controls **************************************************/
		if (IS_SWEEPERBOT) {
			
			gamepad.X.whenPressed(new Drivetrain_GyroStraight(36, 24.0)); 
			gamepad.Y.whenPressed(new Drivetrain_GyroTurn(90.0));

			gamepad.A.whenPressed(new BigPiston_Toggle());
			gamepad.B.whenPressed(new SmallPiston_Toggle());

			gamepad.Start.whenPressed(new Drivetrain_BasketballCourt());
		}
	}
	
	/** Access to the driving axes values *************************************
	 * A negative has been added to make pushing forward positive.
	 */
	public double getDriveLeft() {
		double joystickValue = joyLeft.getRawAxis(driveAxisLeft);
		joystickValue = scaleJoystick(joystickValue);
		return joystickValue;
	}
	public double getDriveRight() {
		double joystickValue = joyRight.getRawAxis(driveAxisRight);
		joystickValue = scaleJoystick(joystickValue);
		return joystickValue;
	}
	private double scaleJoystick(double joystickValue) {
		joystickValue = checkDeadZone(joystickValue);
		joystickValue *= JOYSTICK_DRIVE_SCALE;
		joystickValue *= -1.0;
		return joystickValue;
	}
	private double checkDeadZone(double joystickValue) {
		if (Math.abs(joystickValue) < JOYSTICK_DEAD_ZONE) joystickValue = 0.0;
		return joystickValue;
	}
	
	/** Access the POV value **************************************************/
	public int getPOV() {
		return gamepad.getPOV();
	}
	
	/** Access the Trigger Values *********************************************/
	public double getTriggerDifference() {
		double leftTrigger = gamepad.getRawAxis(GAMEPAD_LEFT_TRIGGER);
		double rightTrigger = gamepad.getRawAxis(GAMEPAD_RIGHT_TRIGGER);
		return (rightTrigger - leftTrigger) * TRIGGER_SPINNER_SCALE;
	}
}