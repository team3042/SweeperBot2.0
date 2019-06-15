 package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.DrivetrainEncoders;


/** DrivetrainEncoders_Dashboard **********************************************
 * Output encoder values to the SmartDashboard
 */
public class DrivetrainEncoders_Dashboard extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_DRIVETRAIN_ENCODERS;
	private static final double CIRCUMFRENCE = Math.PI * RobotMap.WHEEL_DIAMETER;

	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	DrivetrainEncoders encoders = Robot.drivetrain.getEncoders();
	
	
	/** DrivetrainEncoders_Dashboard ******************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public DrivetrainEncoders_Dashboard() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(encoders);
	}

	
	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		
		encoders.reset();
	}

	
	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		double leftPosition = encoders.getLeftPosition();
		double rightPosition = encoders.getRightPosition();
		
		double leftSpeed = encoders.getLeftSpeed();
		double rightSpeed = encoders.getRightSpeed();

		//Convert to length units, based on units of wheel diameter parameter
		leftPosition *= CIRCUMFRENCE;
		rightPosition *= CIRCUMFRENCE;
		leftSpeed *=  CIRCUMFRENCE;
		rightSpeed *=  CIRCUMFRENCE;
		
		//convert speed to per second instead of per minute
		leftSpeed /= 60.0;
		rightSpeed /= 60.0;
		
		SmartDashboard.putNumber("Left Distance", leftPosition);
		SmartDashboard.putNumber("Right Distance", rightPosition);
		SmartDashboard.putNumber("Left Speed", leftSpeed);
		SmartDashboard.putNumber("Right Speed", rightSpeed);
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
	}

	
	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
	}
}
