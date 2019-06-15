/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2.LinkType;
import io.github.pseudoresonance.pixy2api.Pixy2Line;
import io.github.pseudoresonance.pixy2api.Pixy2Line.Vector;

/**
 * Add your docs here.
 */
public class LineTracker extends Subsystem {
  /** Configuration Constants ***********************************************/
  private static final Log.Level LOG_LEVEL = RobotMap.LOG_LINE_TRACKER;
  private static final int PIXY_PORT = RobotMap.LINE_TRACKER_PIXY_PORT;

  /** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
  Pixy2 pixy;
  
  public LineTracker() {
      log.add("Constructor", LOG_LEVEL);

      this.pixy = Pixy2.createInstance(LinkType.SPI);
      pixy.init(PIXY_PORT);
      
      // change to the line_tracking program.  Note, changeProg can use partial strings, so for example,
      // you can change to the line_tracking program by calling changeProg("line") instead of the whole
      // string changeProg("line_tracking")
      int result = this.pixy.changeProg("line".toCharArray());
      if (result == Pixy2.PIXY_RESULT_ERROR) {
        log.add("ERROR returned by pixy.changeProg", Log.Level.ERROR);
      }

      // Set white line on dark background tracking
      result = this.pixy.getLine().setMode(Pixy2Line.LINE_MODE_WHITE_LINE);
      if (result == Pixy2.PIXY_RESULT_ERROR) {
        log.add("ERROR return by pixy.setMode", Log.Level.ERROR);
      }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
  }

  /** Command Controls ******************************************************/

  public void printLines() {
    
      byte res = this.pixy.getLine().getAllFeatures();
      log.add("getAllFeatures returned " + Integer.toHexString(res), LOG_LEVEL);

      Vector[] vectors = this.pixy.getLine().getVectors();
      if (vectors != null) {

        log.add("Vectors Found: " + vectors.length, LOG_LEVEL);
        for (Vector vector : vectors) {
          if (vector != null) {
            log.add(vector.toString(), LOG_LEVEL);
          }
        }
        
      } else {
        log.add("***** vector array is null *****", LOG_LEVEL);
      }
  }

  public void printVersion() {
    int res = pixy.getVersion();
    if (res != Pixy2.PIXY_RESULT_ERROR) {
      log.add(pixy.getVersionInfo().toString(), LOG_LEVEL);
    } else {
      log.add("***** ERROR Getting Pixy Version", Log.Level.ERROR);
    }
  }

  public void followLine() {

    // Follows the code from https://github.com/charmedlabs/pixy2/blob/master/src/host/arduino/libraries/Pixy2/examples/line_zumo_demo/line_zumo_demo.ino
    // Will need to put put our own looping control in here since the Arduino example code
    // loops already.  The key will be determining when to stop

    //this will most likely not be a method, but rather it's own command that has
    //its own IsFinished() and we will most likely use 
    //the gyro turn to degree command (Drivetrain_GyroTurn) which takes angle in deg as a param
    //possibly command group? TDB. 
  }
}
