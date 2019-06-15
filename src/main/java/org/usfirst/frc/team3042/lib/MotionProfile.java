package org.usfirst.frc.team3042.lib;

import org.usfirst.frc.team3042.robot.RobotMap;

import com.ctre.phoenix.motion.TrajectoryPoint;

/** MotionProfile *************************************************************
 * All distances are assumed to be in revolutions.
 * All Times are assumed to be in seconds.
 */
public class MotionProfile {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_DRIVETRAIN_AUTON;
	private static final int PROFILE = RobotMap.AUTON_PROFILE;
	private static final double DT_SEC = RobotMap.AUTON_DT_SEC;
	private static final double ACCEL_TIME = RobotMap.AUTON_ACCEL_TIME;
	private static final double SMOOTH_TIME = RobotMap.AUTON_SMOOTH_TIME;
	private static final double MAX_ACCEL = RobotMap.AUTON_MAX_ACCEL;
	private static final int COUNTS_PER_REV = RobotMap.COUNTS_PER_REVOLUTION;
	private static final int PIDIDX = RobotMap.AUTON_PIDIDX;
	
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, "MotionProfile");
	TrajectoryPoint[] trajectory;

	
	/**MotionProfile **********************************************************
	 * distance given in rotations
	 * Speed given in rps
	 */
	public MotionProfile(double[] distance, double[] speed) {
		checkAccel(speed);
		
		double[] velocity = calculateVelocity(distance, speed);
		double[] position = calculatePosition(velocity);

		fillTrajectory(position, velocity);
	}
	
	
	/** checkAccel ************************************************************/
	private void checkAccel(double[] speed) {
		int stages = speed.length;

		checkAccel(0.0, speed[0]);
		for (int stage=1; stage<stages; stage++) {
			checkAccel(speed[stage-1], speed[stage]);
		}
		checkAccel(speed[stages-1], 0.0);
	}
	private void checkAccel(double speed0, double speed1) {
		double deltaSpeed = Math.abs(speed1 - speed0);
		double accel = deltaSpeed / ACCEL_TIME;
		if (accel > MAX_ACCEL) {
			log.add("Warning: Maximum Acceleration Exceeded from "
					+speed0+" to "+speed1, Log.Level.WARNING);
		}
	}
	
	
	/** calculateVelocity *****************************************************
	 * Velocity calculated in revolutions per second. 
	 */
	private double[] calculateVelocity(double[] distance, double[] speed) {
		int stages = speed.length;

		/** Calculate array length ************************/
		int accelPoints = (int)Math.floor(ACCEL_TIME/DT_SEC);
		int smoothPoints = (int)Math.floor(SMOOTH_TIME/DT_SEC);
		int length = accelPoints + smoothPoints;
		int[] flatPoints = new int[stages];
		for (int stage=0; stage<stages; stage++) {
			double flatTime = Math.abs(distance[stage]/speed[stage]);
			flatPoints[stage] = (int)Math.floor(flatTime/DT_SEC);
			length += flatPoints[stage];
		}

		/** Generate Flat velocity array ******************/
		double[] velocity = new double[length];
		int n=0;
		velocity[n] = 0.0;
		for (int stage=0; stage<stages; stage++) {
			for (int i=0; i<flatPoints[stage]; i++) {
				n++;
				velocity[n] = speed[stage];
			}
		}
		for (int i=1; i<(accelPoints+smoothPoints); i++) {
			n++;
			velocity[n] = 0.0;
		}
		
		/** Smooth the velocity profile *******************/
		velocity = boxcarSmooth(velocity, accelPoints);
		velocity = boxcarSmooth(velocity, smoothPoints);
		
		return velocity;
	}
	
	/** boxcarSmooth **********************************************************/
	private double[] boxcarSmooth(double[] unsmooth, int box) {
		int length = unsmooth.length;
		double[] smooth = new double[length];

		for (int n=0; n<length; n++) {
			int i0 = Math.max(0,  1+n-box);
			for (int i=i0; i<=n; i++) {
				smooth[n] += unsmooth[i];
			}
			smooth[n] /= box;
		}
		return smooth;
	}
	
	
	/** calculatePosition *****************************************************
	 * Position given in revolutions. 
	 */
	private double[] calculatePosition(double[] velocity) {
		int length = velocity.length;
		double[] position = new double[length];
		
		position[0] = 0.0;
		for (int n=1; n<length; n++) {
			double averageVelocity = 0.5 * (velocity[n-1] + velocity[n]);
			position[n] = position[n-1] + averageVelocity * DT_SEC;
		}
		return position;
	}
	
	
	/** fillTrajectory ********************************************************
	 * Position is calculated in revolutions, convert to counts for talon.
	 * 
	 * Velocity is calculated in RPS, convert to counts per 100 ms for talon.
	 */
	private void fillTrajectory(double[] position, double[] velocity) {
		int length = velocity.length;
		trajectory = new TrajectoryPoint[length];
		
		for (int n=0; n<length; n++) {
			trajectory[n] = new TrajectoryPoint();
			trajectory[n].position = position[n] * COUNTS_PER_REV;
			trajectory[n].velocity = velocity[n] * COUNTS_PER_REV / 10.0;			
			trajectory[n].headingDeg = 0; // Set to zero; unimplemented feature
			trajectory[n].profileSlotSelect0 = PROFILE;
			trajectory[n].profileSlotSelect1 = PIDIDX;
			trajectory[n].timeDur = 0;
			trajectory[n].zeroPos = (n == 0);
			trajectory[n].isLastPoint = (n == length-1);
		}
	}
	
	/** getPoint **************************************************************/
	public TrajectoryPoint getPoint(int n) {
		TrajectoryPoint point = null;
		if ( (n>= 0) && (n<getLength()) ) {
			point = trajectory[n];
		}
		else {
			log.add("Array out of bounds: getPoint()", Log.Level.ERROR);
		}
		return point;
	}
	
	
	/** getLength *************************************************************/
	public int getLength() {
		return trajectory.length;
	}
}
