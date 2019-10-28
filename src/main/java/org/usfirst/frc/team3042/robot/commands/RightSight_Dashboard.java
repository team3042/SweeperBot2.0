package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.RightSight;

/** RightSight_Dashboard *******************************************************
 * Displays RightSight sensor readings on the dashboard
 */
public class RightSight_Dashboard extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_GYROSCOPE;
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	RightSight rightsight = Robot.rightsight;
	
	/** RightSight_Dashboard **************************************************/
	public RightSight_Dashboard() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(rightsight);
	}

	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
	}

	protected void execute() {

	}
	
	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		log.add("End", Log.Level.TRACE);
	}

	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
	}
}