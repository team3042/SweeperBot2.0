package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.Gyroscope_Dashboard;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/** Gyroscope *****************************************************************
 * Gyroscope subsystem */
public class Gyroscope extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_GYROSCOPE;
	private static final double GYROSCOPE_SCALE = RobotMap.GYROSCOPE_SCALE;

	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
	ADXRS450_Gyro gyroscope = new ADXRS450_Gyro();

	/** Gyroscope *************************************************************/
	public Gyroscope() {
		log.add("Constructor", LOG_LEVEL);
		reset();
		calibrate();
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem */
	public void initDefaultCommand() {
		setDefaultCommand(new Gyroscope_Dashboard());
	}
	
	/** Command Methods *******************************************************/
	public double getAngle() {
		return gyroscope.getAngle() * GYROSCOPE_SCALE;
	}
	public void reset() {
		gyroscope.reset();
	}
	public void calibrate() {
		gyroscope.calibrate();
	}
}