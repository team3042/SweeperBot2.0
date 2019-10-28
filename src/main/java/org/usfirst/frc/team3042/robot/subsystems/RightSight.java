package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/** RightSight *****************************************************************
 * RightSight sensor subsystem
 */
public class RightSight extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_RIGHTSIGHT;

	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());

	/** Gyroscope *************************************************************/
	public RightSight() {
		log.add("Constructor", LOG_LEVEL);
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		//setDefaultCommand(new CommandName());
	}
	
	/** Command Methods *******************************************************/
}