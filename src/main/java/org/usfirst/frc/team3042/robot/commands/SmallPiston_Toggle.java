package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.SmallPiston;

public class SmallPiston_Toggle extends InstantCommand {
   /** Configuration Constants ***********************************************/
   private static final Log.Level LOG_LEVEL = RobotMap.LOG_SMALL_PISTON;

   /** Instance Variables ****************************************************/
  Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
	SmallPiston small_piston = Robot.small_piston;

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