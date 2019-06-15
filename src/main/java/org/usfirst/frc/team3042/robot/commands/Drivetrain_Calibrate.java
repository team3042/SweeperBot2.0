 package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3042.robot.subsystems.DrivetrainEncoders;


/** Drivetrain_Calibrate ******************************************************
 * Determine the F-Gain for the left and right motors of the drivetrain.
 */
public class Drivetrain_Calibrate extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_DRIVETRAIN_AUTON;
	private static final double CALIBRATE_POWER = RobotMap.AUTON_CALIBRATE_POWER;
	private static final double CALIBRATE_TIME = RobotMap.AUTON_CALIBRATE_TIME;
	private static final int COUNT_AVERAGE = RobotMap.AUTON_COUNT_AVERAGE;
	
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	Drivetrain drivetrain = Robot.drivetrain;
	DrivetrainEncoders encoders = Robot.drivetrain.getEncoders();
	Timer timer = new Timer();
	int count;
	double leftSum, rightSum;
	
	
	/** Drivetrain_Calibrate **************************************************/
	public Drivetrain_Calibrate() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(drivetrain);
	}

	
	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		
		timer.start();
		timer.reset();
		drivetrain.setPower(CALIBRATE_POWER, CALIBRATE_POWER);
		count = 0;
		leftSum = 0.0;
		rightSum = 0.0;
	}

	
	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		if (timer.get() > CALIBRATE_TIME) {
			leftSum += encoders.getLeftSpeed();
			rightSum += encoders.getRightSpeed();
			count ++;
		}
	}
	
	
	/** isFinished ************************************************************	
	 * Make this return true when this Command no longer needs to run execute()
	 */
	protected boolean isFinished() {
		return count >= COUNT_AVERAGE;
	}

	
	/** end *******************************************************************
	 * Called once after isFinished returns true
	 */
	protected void end() {
		log.add("End", Log.Level.TRACE);
		terminate();
		
		log.add("Left kF", findF(leftSum), LOG_LEVEL);
		log.add("Right kF",  findF(rightSum), LOG_LEVEL);
	}
	private double findF (double rpmSum) {
		double rpmAvg = rpmSum / count;
		double kF = encoders.rpmToF(rpmAvg, CALIBRATE_POWER);
		return kF;
	}

	
	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
		terminate();
	}
	
	
	/** Graceful End **********************************************************/
	private void terminate() {
		drivetrain.stop();
	}
}
