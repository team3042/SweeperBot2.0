package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;


/** SpinnerClosedLoop *********************************************************/
public class SpinnerClosedLoop extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_SPINNER_CLOSED_LOOP;
	private static final int POSITION_PROFILE = RobotMap.SPINNER_POSITION_PROFILE;
	private static final double kP_POSITION = RobotMap.kP_SPINNER_POSITION;
	private static final double kI_POSITION = RobotMap.kI_SPINNER_POSITION;
	private static final double kD_POSITION = RobotMap.kD_SPINNER_POSITION;
	private static final double kF_POSITION = RobotMap.kF_SPINNER_POSITION;
	private static final int I_ZONE_POSITION = RobotMap.I_ZONE_SPINNER_POSITION;
	private static final int SPEED_PROFILE = RobotMap.SPINNER_SPEED_PROFILE;
	private static final double kP_SPEED = RobotMap.kP_SPINNER_SPEED;
	private static final double kI_SPEED = RobotMap.kI_SPINNER_SPEED;
	private static final double kD_SPEED = RobotMap.kD_SPINNER_SPEED;
	private static final double kF_SPEED = RobotMap.kF_SPINNER_SPEED;
	private static final int I_ZONE_SPEED = RobotMap.I_ZONE_SPINNER_SPEED;
	private static final double COUNTS_PER_REV = RobotMap.SPINNER_ENCODER_COUNTS_PER_REV;
	private static final int TIMEOUT = RobotMap.SPINNER_TIMEOUT;
	private static final int PIDIDX = RobotMap.SPINNER_PIDIDX;
	private static final int CRUISE = RobotMap.SPINNER_CRUISE;
	private static final int ACCEL = RobotMap.SPINNER_ACCEL;
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	TalonSRX motor;
	SpinnerEncoder encoder;
	
	
	/** SpinnerClosedLoop *****************************************************/
	public SpinnerClosedLoop(TalonSRX spinnerMotor, SpinnerEncoder spinnerEncoder) {
		log.add("Constructor", LOG_LEVEL);
		
		motor = spinnerMotor;
		encoder = spinnerEncoder;
		
		motor.config_kP(POSITION_PROFILE, kP_POSITION, TIMEOUT);
		motor.config_kI(POSITION_PROFILE, kI_POSITION, TIMEOUT);
		motor.config_kD(POSITION_PROFILE, kD_POSITION, TIMEOUT);
		motor.config_kF(POSITION_PROFILE, kF_POSITION, TIMEOUT);
		motor.config_IntegralZone(POSITION_PROFILE, I_ZONE_POSITION, TIMEOUT);
		
		motor.config_kP(SPEED_PROFILE, kP_SPEED, TIMEOUT);
		motor.config_kI(SPEED_PROFILE, kI_SPEED, TIMEOUT);
		motor.config_kD(SPEED_PROFILE, kD_SPEED, TIMEOUT);
		motor.config_kF(SPEED_PROFILE, kF_SPEED, TIMEOUT);
		motor.config_IntegralZone(SPEED_PROFILE, I_ZONE_SPEED, TIMEOUT);
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		//setDefaultCommand(new ExampleCommand());
	}


	/** Spinner Closed-Loop Control *******************************************
	 * Input units for speed is RPM, convert to counts per 100ms for talon
	 * Input units for Position is revolutions, convert to counts for talon
	 */
	public void setSpeed(double rpm){
		log.add("Speed", rpm, LOG_LEVEL);
		
		double cp100ms = rpmToCp100ms(rpm);
				
		motor.selectProfileSlot(SPEED_PROFILE, PIDIDX);
		motor.set(ControlMode.Velocity, cp100ms);
	}
	public void setPosition(double position) {		
		double counts = positionToCounts(position);
				
		motor.selectProfileSlot(POSITION_PROFILE, PIDIDX);
		motor.set(ControlMode.Position, counts);
	}
	public void setMagicPosition(double position) {
		double counts = positionToCounts(position);
		
		double cp100ms = rpmToCp100ms(CRUISE);
		double accel = rpmToCp100ms(ACCEL);
		
		motor.selectProfileSlot(POSITION_PROFILE, PIDIDX);
		motor.configMotionCruiseVelocity((int)cp100ms, TIMEOUT);
		motor.configMotionAcceleration((int)accel, TIMEOUT);
		motor.set(ControlMode.MotionMagic, counts);		
	}
	
	
	private double positionToCounts(double position) {
		position += encoder.getPositionZero();
		double counts = position * COUNTS_PER_REV;
		return counts;
	}
	private double rpmToCp100ms(double rpm) {
		double cp100ms = rpm * (double)COUNTS_PER_REV / 600.0;
		return cp100ms;
	}
}
