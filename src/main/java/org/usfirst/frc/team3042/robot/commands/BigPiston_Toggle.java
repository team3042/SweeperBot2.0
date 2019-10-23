package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.BigPiston;

public class BigPiston_Toggle extends InstantCommand {
   /** Configuration Constants ***********************************************/
   private static final Log.Level LOG_LEVEL = RobotMap.LOG_SMALL_PISTON;

   /** Instance Variables ****************************************************/
  Log log = new Log(LOG_LEVEL, getName());
	BigPiston big_piston = Robot.big_piston;

  public BigPiston_Toggle() {
    super();
    requires(big_piston);
  }

  @Override

  protected void initialize() {
    log.add("Initialize", Log.Level.TRACE);
    big_piston.toggle();
  }
}