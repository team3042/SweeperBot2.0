 package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Spinner;


/** Spinner_SetSpeed **********************************************************/
public class Spinner_SetSpeed extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_SPINNER_CLOSED_LOOP;
	private static final double DEFAULT_SPEED = RobotMap.SPINNER_DEFAULT_SPEED;
	
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	Spinner spinner = Robot.spinner;
	String speedLabel = "Spinner Speed";
	boolean getFromDash = false;
	double speed;
	
	
	/** Spinner_SetSpeed ******************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public Spinner_SetSpeed(double speed) {
		log.add("Constructor", Log.Level.TRACE);
		requires(spinner);
		
		this.speed = speed;
	}
	public Spinner_SetSpeed() {
		this(DEFAULT_SPEED);
		getFromDash = true;
		SmartDashboard.putNumber(speedLabel, DEFAULT_SPEED);
	}
	
	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);

		if (getFromDash) {
			speed = SmartDashboard.getNumber(speedLabel, DEFAULT_SPEED);
		}
		spinner.closedLoop.setSpeed(speed);
	}

	
	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
	}
	
	
	/** isFinished ************************************************************	
	 * Make this return true when this Command no longer needs to run execute()
	 */
	protected boolean isFinished() {
		return false;
	}

	
	/** end *******************************************************************
	 * Called once after isFinished returns true
	 */
	protected void end() {
		log.add("End", Log.Level.TRACE);
		terminate();
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
