package org.usfirst.frc.team3042.robot.commands;

import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/** Drivetrain Basketball Court *********************************************
 * Code for sweeping the basketball court autonomously
 */
public class Drivetrain_BasketballCourt extends CommandGroup {
  /** Configuration Constants ***********************************************/
  private static final double COURT_LENGTH = RobotMap.BASKETBALL_COURT_LENGTH;
  private static final double COURT_WIDTH = RobotMap.BASKETBALL_COURT_WIDTH;
  private static final double DRIVE_SPEED = RobotMap.BASKETBALL_COURT_DRIVE_SPEED;

  public Drivetrain_BasketballCourt() {
  // Align using sensor here
  for (int i=1; i<6; i++) {
    addSequential(new Drivetrain_GyroStraight(COURT_LENGTH, DRIVE_SPEED));
    // Realign using sensor here
    addSequential(new Drivetrain_GyroTurn(90));
    addSequential(new Drivetrain_GyroStraight(COURT_WIDTH, DRIVE_SPEED));
    addSequential(new Drivetrain_GyroTurn(90));
    addSequential(new Drivetrain_GyroStraight(COURT_LENGTH, DRIVE_SPEED));
    // Realign using sensor here
    addSequential(new Drivetrain_GyroTurn(-90));
    addSequential(new Drivetrain_GyroStraight(COURT_WIDTH, DRIVE_SPEED));
    addSequential(new Drivetrain_GyroTurn(-90));
  }

    /** To run multiple commands at the same time,
     * use addParallel()
     * e.g. addParallel(new Command1());
     * addSequential(new Command2());
     * Command1 and Command2 will run in parallel.
     * 
     * A command group will require all of the subsystems that each member
     * would require.
     * e.g. if Command1 requires chassis, and Command2 requires arm,
     * a CommandGroup containing them would require both the chassis and the
     * arm.
     */

  }
}