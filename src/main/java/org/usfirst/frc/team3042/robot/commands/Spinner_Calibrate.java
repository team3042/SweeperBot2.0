 package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Spinner;
import org.usfirst.frc.team3042.robot.subsystems.SpinnerEncoder;


/** Spinner_Calibrate *********************************************************
 * Determine the F-Gain for the spinner
 */
public class Spinner_Calibrate extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_SPINNER_CLOSED_LOOP;
	private static final double CALIBRATE_POWER = RobotMap.SPINNER_CALIBRATE_POWER;
	private static final double CALIBRATE_TIME = RobotMap.SPINNER_CALIBRATE_TIME;
	private static final int COUNT_AVERAGE = RobotMap.SPINNER_COUNT_AVERAGE;
	
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	Spinner spinner = Robot.spinner;
	SpinnerEncoder encoder = Robot.spinner.getEncoder();
	Timer timer = new Timer();
	int count;
	double rpmSum;
	
	
	/** Spinner_Calibrate *****************************************************/
	public Spinner_Calibrate() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(spinner);
	}

	
	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		
		timer.start();
		timer.reset();
		spinner.setPower(CALIBRATE_POWER);
		count = 0;
		rpmSum = 0.0;
	}

	
	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		if (timer.get() > CALIBRATE_TIME) {
			rpmSum += encoder.getSpeed();
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
		
		double rpmAvg = rpmSum / count;
		double kF = encoder.rpmToF(rpmAvg, CALIBRATE_POWER);
		log.add("Spinner F-Gain", kF, LOG_LEVEL);
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
		spinner.stop();
	}
}
