/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CleanMop extends CommandGroup {
  /**
   * Cleans the dust off of SweeperBot's mop.
   */
  public CleanMop() {

    addSequential(new BigPiston_Toggle()); //Raise big piston

    for(int i=0; i<4; i++) {
      addSequential(new SmallPiston_Toggle()); //Shake small piston
  }
    addSequential(new BigPiston_Toggle()); //Lower Big Piston
  }
}