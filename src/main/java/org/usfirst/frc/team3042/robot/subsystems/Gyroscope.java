package org.usfirst.frc.team3042.robot.subsystems;

//import com.analog.adis16448.frc.ADIS16448_IMU; //Uncomment for ADIS16448 gyroscope (Big Boy)

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.Gyroscope_Dashboard;

import edu.wpi.first.wpilibj.ADXRS450_Gyro; //Uncomment for ADXRS450 gyroscope (Small Boy)
import edu.wpi.first.wpilibj.command.Subsystem;

/** Gyroscope *****************************************************************
 * Gyroscope subsystem
 */
public class Gyroscope extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_GYROSCOPE;
	private static final double GYROSCOPE_SCALE = RobotMap.GYROSCOPE_SCALE;

	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	//ADIS16448_IMU gyroscope = new ADIS16448_IMU(); //Uncomment for ADIS16448 gyroscope (Big Boy)
	ADXRS450_Gyro gyroscope = new ADXRS450_Gyro(); //Uncomment for ADXRS450 gyroscope (Small Boy)

	/** Gyroscope *************************************************************/
	public Gyroscope() {
		log.add("Constructor", LOG_LEVEL);
		reset();
		calibrate();
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new Gyroscope_Dashboard());
	}
	
	/** Command Methods *******************************************************/
	public double getAngle() {
		//return gyroscope.getAngleZ()*GYROSCOPE_SCALE;	//Uncomment for ADIS16448 gyroscope (Big Boy)
		return gyroscope.getAngle() * GYROSCOPE_SCALE; //Uncomment for ADXRS450 gyroscope (Small Boy)
	}
	public void reset() {
		gyroscope.reset();
	}
	public void calibrate() {
		gyroscope.calibrate();
	}
}