package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.Drivetrain_TankDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/** Drivetrain ****************************************************************
 * The drivetrain subsystem for the robot.
 */
public class Drivetrain extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_DRIVETRAIN;
	private static final int CAN_LEFT_MOTOR = RobotMap.CAN_LEFT_MOTOR;
	private static final int CAN_RIGHT_MOTOR = RobotMap.CAN_RIGHT_MOTOR;
	private static final boolean HAS_ENCODERS = RobotMap.HAS_ENCODERS;
	private static final boolean HAS_AUTON = RobotMap.HAS_AUTON;
	private static final NeutralMode BRAKE_MODE = RobotMap.DRIVETRAIN_BRAKE_MODE;
	private static final boolean REVERSE_LEFT_MOTOR = RobotMap.REVERSE_LEFT_MOTOR;
	private static final boolean REVERSE_RIGHT_MOTOR = RobotMap.REVERSE_RIGHT_MOTOR;
	private static final boolean IS_PBOT = RobotMap.IS_PBOT;
	private static final boolean IS_SWEEPERBOT = RobotMap.IS_SWEEPERBOT;	
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());

	TalonSRX leftMotor = new TalonSRX(CAN_LEFT_MOTOR);
	TalonSRX rightMotor = new TalonSRX(CAN_RIGHT_MOTOR);

	Talon sweeperBotLeftMotor = new Talon(CAN_LEFT_MOTOR);
	Talon sweeperBotRightMotor =  new Talon(CAN_RIGHT_MOTOR);

	DrivetrainEncoders encoders;
	DrivetrainAuton auton;

	/** Drivetrain ************************************************************
	 * Set up the talons for desired behavior.
	 */
	public Drivetrain() {
		log.add("Constructor", LOG_LEVEL);
		
		if (HAS_ENCODERS) {
			encoders = new DrivetrainEncoders(leftMotor, rightMotor);
			
			if (HAS_AUTON) auton = 
					new DrivetrainAuton(leftMotor, rightMotor, encoders);
		}
		
		initSRXMotor(leftMotor, REVERSE_LEFT_MOTOR);
		initSRXMotor(rightMotor, REVERSE_RIGHT_MOTOR);

		initMotor(sweeperBotLeftMotor, REVERSE_LEFT_MOTOR);
		initMotor(sweeperBotRightMotor, REVERSE_RIGHT_MOTOR);
	}
	private void initSRXMotor(TalonSRX motor, boolean reverse) {
		motor.setNeutralMode(BRAKE_MODE);
		motor.setInverted(reverse); 	// affects percent Vbus mode
	}

	private void initMotor(Talon motor, boolean reverse) {
		motor.setInverted(reverse); 	// affects percent Vbus mode
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new Drivetrain_TankDrive());
	}
	
	/** Methods for setting the motors in Percent Vbus mode ********************/
	public void setPower(double leftPower, double rightPower) {
		leftPower = safetyCheck(leftPower);
		rightPower = safetyCheck(rightPower);

		if(IS_PBOT) {		
			leftMotor.set(ControlMode.PercentOutput, leftPower);
			rightMotor.set(ControlMode.PercentOutput, rightPower);
		}
		else if (IS_SWEEPERBOT) {
			sweeperBotLeftMotor.set(leftPower);
			sweeperBotRightMotor.set(rightPower);
		}
	}
	public void stop() {
		setPower(0.0, 0.0);
	}
	private double safetyCheck(double power) {
		power = Math.min(1.0, power);
		power = Math.max(-1.0, power);
		return power;
	}
	
	/** Provide commands access to the encoders and autonomous ****************/
	public DrivetrainEncoders getEncoders() {
		return encoders;
	}
	public DrivetrainAuton getAuton() {
		return auton;
	}
}