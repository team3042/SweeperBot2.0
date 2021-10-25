package org.usfirst.frc.team3042.robot.commands.Autonomous;

import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.Drivetrain_GyroStraight;
import org.usfirst.frc.team3042.robot.commands.Drivetrain_GyroTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/** Drivetrain Basketball Court *********************************************
 * Code for sweeping the basketball court autonomously */

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
  }
}