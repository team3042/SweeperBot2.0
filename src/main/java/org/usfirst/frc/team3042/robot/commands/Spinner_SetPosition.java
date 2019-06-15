 package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Spinner;


/** Spinner_SetPosition *******************************************************/
public class Spinner_SetPosition extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_SPINNER_CLOSED_LOOP;
	private static final double DEFAULT_POSITION = RobotMap.SPINNER_DEFAULT_POSITION;
	
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	Spinner spinner = Robot.spinner;
	String positionLabel = "Spinner Position";
	boolean getFromDash = false;
	double position;
	
	
	/** Spinner_SetPosition ***************************************************
	 * Required subsystems will cancel commands when this command is run.
	 * 
	 * Unit for position is revolutions, relative to the current zero position.
	 */
	public Spinner_SetPosition(double position) {
		log.add("Constructor", Log.Level.TRACE);
		requires(spinner);

		this.position = position;
	}
	public Spinner_SetPosition() {
		this(DEFAULT_POSITION);
		getFromDash = true;
		SmartDashboard.putNumber(positionLabel, DEFAULT_POSITION);
	}

	
	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		
		if (getFromDash) {
			position = SmartDashboard.getNumber(positionLabel, DEFAULT_POSITION);
		}
		
		spinner.closedLoop.setMagicPosition(position);
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
