package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;


/** LightRing *****************************************************************
 * Control of the LED ring for the camera.
 */
public class LightRing extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_LIGHT_RING;
	private static final int LIGHT_RING_CHANNEL = RobotMap.LIGHT_RING_CHANNEL;
	
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	Solenoid lightRing = new Solenoid(LIGHT_RING_CHANNEL);
	
	
	/** LightRing *************************************************************/
	public LightRing() {
		log.add("Constructor", LOG_LEVEL);
		off();
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		//setDefaultCommand(new ExampleCommand());
	}
	
	
	/** Command Controls ******************************************************/
	public void on() {
		lightRing.set(true);
	}
	public void off() {
		lightRing.set(false);
	}
}
