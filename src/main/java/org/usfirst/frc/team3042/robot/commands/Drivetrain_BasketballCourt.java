/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Drivetrain_BasketballCourt extends CommandGroup {
  /**
   * Add your docs here.
   */
  public Drivetrain_BasketballCourt() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.
    addSequential(new Drivetrain_GyroStraight(36, 24));
    addSequential(new Drivetrain_GyroTurn(90));
    addSequential(new Drivetrain_GyroStraight(12, 24));
    addSequential(new Drivetrain_GyroTurn(90));
    addSequential(new Drivetrain_GyroStraight(36, 24));
    addSequential(new Drivetrain_GyroTurn(-90));
    addSequential(new Drivetrain_GyroStraight(12, 24));
    addSequential(new Drivetrain_GyroTurn(-90));
    addSequential(new Drivetrain_GyroStraight(36, 24));
    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
