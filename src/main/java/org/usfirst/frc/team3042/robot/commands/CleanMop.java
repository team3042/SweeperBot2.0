package org.usfirst.frc.team3042.robot.commands;

import org.usfirst.frc.team3042.robot.commands.Autonomous.Wait;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CleanMop extends CommandGroup {
  /** Cleans the dust off of SweeperBot's mop! */

  public CleanMop() {

    addSequential(new BigPiston_Toggle()); //Raise big piston
    addSequential(new Wait(0.8));

    for(int i=0; i<6; i++) {
      addSequential(new SmallPiston_Toggle()); //Shake small piston
      addSequential(new Wait(.3));

    }

    addSequential(new Wait(0.8));
    addSequential(new BigPiston_Toggle()); //Lower Big Piston
    
  }
}