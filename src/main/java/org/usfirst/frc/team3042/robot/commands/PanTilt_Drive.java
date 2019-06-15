 package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.OI;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.PanTilt;
import org.usfirst.frc.team3042.robot.triggers.POVButton;


/** PanTilt_Drive ***********************************************************
 * Drive the Pan-Tilt servos using the POV buttons on the gamepad.
 * 
 * SERVO_SPEED determines how fast the servos move, measured per second.
 */
public class PanTilt_Drive extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_PAN_TILT;
	private static final double PAN_CENTER = RobotMap.PAN_CENTER;
	private static final double TILT_CENTER = RobotMap.TILT_CENTER;
	private static final double SERVO_SPEED = RobotMap.SERVO_SPEED;
	private static final boolean REVERSE_PAN = RobotMap.REVERSE_PAN;
	private static final boolean REVERSE_TILT = RobotMap.REVERSE_TILT;

	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	double panPosition, tiltPosition;
	Timer timer = new Timer();
	PanTilt panTilt = Robot.panTilt;
	OI oi = Robot.oi;
	
	
	/** PanTilt_Drive ***********************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public PanTilt_Drive() {
		log.add("Constructor", Log.Level.TRACE);
		requires(panTilt);
	}

	
	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);

		timer.start();
		timer.reset();
	
		panPosition = PAN_CENTER;
		tiltPosition = TILT_CENTER;
	}

	
	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		double dt = timer.get();
		timer.reset();
		
		double delta = SERVO_SPEED * dt;
		double panDelta = (REVERSE_PAN) ? -delta : delta;
		double tiltDelta = (REVERSE_TILT) ? -delta : delta;
		
		int pov = oi.getPOV();
		if ( (pov == POVButton.UP) || (pov == POVButton.UP_LEFT) || 
				(pov == POVButton.UP_RIGHT) ) {
			tiltPosition -= tiltDelta;
		}
		if ( (pov == POVButton.DOWN) || (pov == POVButton.DOWN_LEFT) || 
				(pov == POVButton.DOWN_RIGHT) ) {
			tiltPosition += tiltDelta;
		}
		if ( (pov == POVButton.LEFT) || (pov == POVButton.DOWN_LEFT) ||
				(pov == POVButton.UP_LEFT) ) {
			panPosition -= panDelta;
		}
		if ( (pov == POVButton.RIGHT) || (pov == POVButton.DOWN_RIGHT) ||
				(pov == POVButton.UP_RIGHT) ) {
			panPosition += panDelta;
		}
		
		panPosition = panTilt.setPan(panPosition);
		tiltPosition = panTilt.setTilt(tiltPosition);
		
		SmartDashboard.putNumber("Pan Position", panPosition);
		SmartDashboard.putNumber("Tilt Position", tiltPosition);
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
		timer.stop();
	}
}
