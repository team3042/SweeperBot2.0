/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3042.robot.commands;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.LineTracker;

import edu.wpi.first.wpilibj.command.Command;


public class LineTracker_PrintLines extends Command {

  /** Configuration Constants ***********************************************/
  private static final Log.Level LOG_LEVEL = RobotMap.LOG_LINE_TRACKER;

  /** Instance Variables ****************************************************/
  Log log = new Log(LOG_LEVEL, getName()); 
  LineTracker lineTracker = Robot.lineTracker;

  public LineTracker_PrintLines() {
		log.add("Constructor", Log.Level.TRACE);
		requires(lineTracker);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    log.add("Initialize", Log.Level.TRACE);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    lineTracker.printLines();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    log.add("End", Log.Level.TRACE);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    log.add("Interrupted", Log.Level.TRACE);
  }
}
