package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/** Small Piston **************************************************************
 * The Small piston subsystem of the robot */
public class SmallPiston extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_SMALL_PISTON;
	private static final int ID = RobotMap.SMALL_PISTON_SOLENOID;
	private static final boolean open = false;
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, SendableRegistry.getName(this));
	Solenoid smallPistonSolenoid = new Solenoid(ID);
	boolean isOpen = open;

	/** Small Piston **********************************************************
	 * The small piston that shakes off the mop */
	public SmallPiston() {
		log.add("Constructor", LOG_LEVEL);
	}
	public void setOpen(){
    	smallPistonSolenoid.set(!open);
    	isOpen = true;
    }
    public void setClose(){
    	smallPistonSolenoid.set(open);
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
	 * Set the default command for the subsystem */
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
}