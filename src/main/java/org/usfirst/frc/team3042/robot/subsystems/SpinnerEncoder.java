package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.SpinnerEncoder_Dashboard;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;


/** SpinnerEncoder ************************************************************/
public class SpinnerEncoder extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_SPINNER_ENCODER;
	private static final int FRAME_RATE = RobotMap.SPINNER_ENCODER_FRAME_RATE;
	private static final int COUNTS_PER_REV = RobotMap.SPINNER_ENCODER_COUNTS_PER_REV;
	private static final int TIMEOUT = RobotMap.SPINNER_TIMEOUT;
	private static final int PIDIDX = RobotMap.SPINNER_PIDIDX;
	private static final boolean SENSOR_PHASE = RobotMap.SPINNER_SENSOR_PHASE;

	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	TalonSRX encoder;
	double positionZero;
	
	
	/** SpinnerEncoder ********************************************************/
	public SpinnerEncoder(TalonSRX motor) {
		log.add("Constructor", LOG_LEVEL);

		encoder = motor;
		
		encoder.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 
				PIDIDX, TIMEOUT);
		encoder.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 
				FRAME_RATE, TIMEOUT);
		encoder.setSensorPhase(SENSOR_PHASE); 	// affects closed-loop mode
		
		reset();
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new SpinnerEncoder_Dashboard());
	}
	
	
	/** Reset the encoder zero position ****************************************/
	public void reset() {
		int counts = encoder.getSelectedSensorPosition(PIDIDX);
		positionZero = countsToRev(counts);
	}
	
	
	/** Get the encoder position and velocity *********************************
	 * Encoder position returns counts, convert to revolutions for output
	 * Encoder speed returns counts per 100 ms, convert to RPM for output
	 */
	public double getPosition() {
		int counts = encoder.getSelectedSensorPosition(PIDIDX);
		return countsToRev(counts) - positionZero;
	}
	private double countsToRev(int counts) {
		return (double)counts / COUNTS_PER_REV;
	}
	public double getSpeed() {
		int cp100ms = encoder.getSelectedSensorVelocity(PIDIDX);
		
		return (double)cp100ms * 10.0 * 60.0 / COUNTS_PER_REV;
	}
	public double getPositionZero() {
		return positionZero;
	}
	
	
	/** rpmToF ****************************************************************
	 * Convert RPM reading into an F-Gain
	 * Note that 1023 is the native full-forward power of the talons, 
	 * equivalent to setting the power to 1.0.
	 * The speed has to be converted from rpm to encoder counts per 100ms
	 * 
	 * so F = power * 1023 / speed
	 */
	public double rpmToF(double rpm, double power) {
		//Convert to counts per 100 ms
		double speed = rpm * 4.0 * COUNTS_PER_REV / 600.0;
		double kF = power * 1023.0 / speed;
		
		return kF;
	}
}
