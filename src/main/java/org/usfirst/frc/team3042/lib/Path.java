package org.usfirst.frc.team3042.lib;

import java.util.ArrayList;

import org.usfirst.frc.team3042.robot.RobotMap;

/** Path **********************************************************************/
public class Path {
	/** Configurations Constants **********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_DRIVETRAIN_AUTON;
	private static final double ROBOT_WIDTH = RobotMap.ROBOT_WIDTH;
	private static final double CIRCUMFRENCE = Math.PI * RobotMap.WHEEL_DIAMETER;

	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, "Path");
	ArrayList<Double> leftDistance = new ArrayList<Double>();
	ArrayList<Double> rightDistance = new ArrayList<Double>();
	ArrayList<Double> leftSpeed = new ArrayList<Double>();
	ArrayList<Double> rightSpeed = new ArrayList<Double>();
	

	/** addStraight ***********************************************************
	 * distance: physical unit matching that of the wheel diameter constant
	 * speed: physical unit of distance per second.
	 * 
	 * Converted to revolutions and rps for the motion profile
	 * Direction is determined by the sign of speed.
	 */
	public void addStraight(double distance, double speed) {
		distance = convertDistance(distance);
		speed = convertSpeed(speed);
		
		leftDistance.add(distance);
		rightDistance.add(distance);
		leftSpeed.add(speed);
		rightSpeed.add(speed);
	}
	private double convertDistance(double distance) {
		distance = Math.abs(distance);
		return distance / CIRCUMFRENCE;
	}
	private double convertSpeed(double speed) {
		return speed / CIRCUMFRENCE;
	}
	
	
	/** Add Turn **************************************************************
	 * angle: degrees
	 * radius: inches
	 * 		If You want to turn in place, use a radius that is 0.5 times the 
	 * 		robot width. If you want to turn around one side of the robot, use
	 * 		a radius that is 1.0 times the width of the robot.
	 * 
	 * The speed is the speed of the outer wheel.
	 * 
	 * Converted to revolutions and rps for the motion profile.
	 * Direction is determined by the sign of speed.
	 */
	public void addLeftTurn(double angle, double radius, double speed) {
		double distance = convertDistance(angle, radius);
		speed = convertSpeed(speed);
		
		double innerScale = innerScale(radius);
		
		leftDistance.add(distance * Math.abs(innerScale));
		leftSpeed.add(speed * innerScale);
		rightDistance.add(distance);
		rightSpeed.add(speed);
	}
	public void addRightTurn(double angle, double radius, double speed) {
		double distance = convertDistance(angle, radius);
		speed = convertSpeed(speed);
		
		double innerScale = innerScale(radius);
		
		leftDistance.add(distance);
		leftSpeed.add(speed);
		rightDistance.add(distance * Math.abs(innerScale));
		rightSpeed.add(speed * innerScale);
	}
	private double convertDistance(double angle, double radius) {
		angle *= Math.PI / 180.0; //convert to radians
		double distance = radius * angle;
		return convertDistance(distance); // convert to revolutions
	}
	private double innerScale(double radius) {
		radius = Math.abs(radius);
		double innerRadius = radius - ROBOT_WIDTH;
		if (radius > 0.0) innerRadius /= radius;
		return innerRadius;
	}
	
	
	/** Generate Motion Profile Paths *****************************************/
	public MotionProfile generateLeftPath() {
		return new MotionProfile(convert(leftDistance), convert(leftSpeed));
	}
	public MotionProfile generateRightPath() {
		return new MotionProfile(convert(rightDistance), convert(rightSpeed));
	}
	private double[] convert(ArrayList<Double> input) {
		int length = input.size();
		double[] output = new double[length];
		for (int n=0; n<length; n++) {
			output[n] = input.get(n);
		}
		return output;
	}
}
