package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;


/** DrivetrainFollowers **********************************************************
 * Motor controllers for secondary drivetrain motors
 */
public class DrivetrainFollowers extends Subsystem {
	/** Configuration Constants ***********************************************/
	public static final Log.Level LOG_LEVEL = RobotMap.LOG_DRIVETRAIN_FOLLOWERS;
	private static final int CAN_LEFT_MOTOR = RobotMap.CAN_LEFT_MOTOR;
	private static final int CAN_RIGHT_MOTOR = RobotMap.CAN_RIGHT_MOTOR;
	private static final int CAN_LEFT_FOLLOWER = RobotMap.CAN_LEFT_FOLLOWER;
	private static final int CAN_RIGHT_FOLLOWER = RobotMap.CAN_RIGHT_FOLLOWER;

	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	TalonSRX leftFollower = new TalonSRX(CAN_LEFT_FOLLOWER);
	TalonSRX rightFollower = new TalonSRX(CAN_RIGHT_FOLLOWER);	
	

	/** DrivetrainFollowers **************************************************/
	public DrivetrainFollowers() {
		log.add("Constructor", Log.Level.TRACE);
		
		leftFollower.set(ControlMode.Follower, CAN_LEFT_MOTOR);
		rightFollower.set(ControlMode.Follower, CAN_RIGHT_MOTOR);
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
	}
}
