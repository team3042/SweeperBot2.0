package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.SmallPiston;

/** Gyroscope_Dashboard *****************************************************
 * Display the gyroscope angle on the dashboard
 */
public class SmallPiston_Toggle extends InstantCommand {
  /** Configuration Constants ***********************************************/
  private static final Log.Level LOG_LEVEL = RobotMap.LOG_SMALL_PISTON;

  /** Instance Variables ****************************************************/
  Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
  SmallPiston small_piston = Robot.small_piston;

  /** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
  public SmallPiston_Toggle() {
    super();
    requires(small_piston);
  }

  @Override

  protected void initialize() {
    log.add("Initialize", Log.Level.TRACE);
    small_piston.toggle();
  }
}