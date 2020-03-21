package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.DrivetrainEncoders_Dashboard;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;


/** DrivetrainEncoders ********************************************************
 * The encoders for the drivetrain.
 */
public class DrivetrainEncoders extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_DRIVETRAIN_ENCODERS;
	private static final int COUNTS_PER_REVOLUTION = RobotMap.COUNTS_PER_REVOLUTION;
	private static final boolean SENSOR_PHASE_LEFT = RobotMap.SENSOR_PHASE_LEFT;
	private static final boolean SENSOR_PHASE_RIGHT = RobotMap.SENSOR_PHASE_RIGHT;

	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
	Encoder leftEncoder, rightEncoder;
	double leftPositionZero, rightPositionZero;
	
	
	/** DrivetrainEncoders ****************************************************/
	public DrivetrainEncoders(Encoder leftEncoder1, Encoder rightEncoder1) {
		log.add("Constructor", LOG_LEVEL);

		leftEncoder = leftEncoder1;
		rightEncoder = rightEncoder1;
				
		initEncoder(leftEncoder, SENSOR_PHASE_LEFT);
		initEncoder(rightEncoder, SENSOR_PHASE_RIGHT);
													
		reset();
	}
	private void initEncoder(Encoder encoder, boolean sensorPhase) {
		encoder.setReverseDirection(sensorPhase); 	// affects closed-loop mode
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new DrivetrainEncoders_Dashboard());
	}

	
	/** reset *****************************************************************/
	public void reset() {
		int leftCounts = leftEncoder.get();
		leftPositionZero = countsToRev(leftCounts);
		
		int rightCounts = rightEncoder.get();
		rightPositionZero = countsToRev(rightCounts);
	}
	public void setToZero() {
		leftPositionZero = 0.0;
		rightPositionZero = 0.0;
	}
	
	
	/** Get the encoder position or speed *************************************
	 * Position is converted to revolutions
	 * Speed returns counts per 100ms and is converted to RPM
	 */
	public double getLeftPosition() {
		int counts = leftEncoder.get();
		return countsToRev(counts) - leftPositionZero;
	}
	public double getRightPosition() {
		int counts = rightEncoder.get();
		return countsToRev(counts) - rightPositionZero;
	}
	private double countsToRev(int counts) {
		return (double)counts / COUNTS_PER_REVOLUTION;
	}
	public double getLeftSpeed() {
		int cp100ms = leftEncoder.get();
		return cp100msToRPM(cp100ms);
	}
	public double getRightSpeed() {
		int cp100ms = rightEncoder.get();
		return cp100msToRPM(cp100ms);
	}
	private double cp100msToRPM(int cp100ms) {
		return (double)cp100ms * 10.0 * 60.0 / COUNTS_PER_REVOLUTION;
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
		// Convert to counts per 100 ms
		double speed = rpm * 4.0 * COUNTS_PER_REVOLUTION / 600.0;
		double kF = power * 1023.0 / speed;
		return kF;
	}
	public double rpmToPower(double rpm, double kF) {
		// Convert to counts per 100 ms
		double speed = rpm * 4.0 * COUNTS_PER_REVOLUTION / 600.0;
		double power = kF * speed / 1023.0;
		return power;
	}
}