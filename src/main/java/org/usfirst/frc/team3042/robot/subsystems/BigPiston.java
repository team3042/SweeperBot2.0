package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/** Big Piston ****************************************************************
 * The Big Piston subsystem for the robot.
 */
public class BigPiston extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_BIG_PISTON;
	private static final int ID = RobotMap.BIG_PISTON_SOLENOID;
	private static final boolean open = false;
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
	Solenoid bigPistonSolenoid = new Solenoid(ID);
	boolean isOpen = open;

	/** Big Piston ************************************************************
	 * Big Piston that shakes off the mop.
	 */
	public BigPiston() {
		log.add("Constructor", LOG_LEVEL);
	}

	public void setOpen(){
    	bigPistonSolenoid.set(!open);
    	isOpen = true;
    }

    public void setClose(){
    	bigPistonSolenoid.set(open);
    	isOpen = false;
    }

	public void toggle(){
    	if (isOpen){
    		setClose();
    	}
    	else {
    		setOpen();
    	}
    }

	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
}