package com.badrobot.commands;

import com.badrobot.BadRobotMap;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.badrobot.OI;
import com.badrobot.subsystems.BadCameraSystem;
import com.badrobot.subsystems.ProtoDriveTrain;
import com.badrobot.subsystems.interfaces.IDriveTrain;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    public static BadRobotMap map;
    // Create a single static instance of all of your subsystems
    protected static IDriveTrain driveTrain;
    protected static BadCameraSystem imageTrackingSystem;

    public static void init() 
    {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
        oi.init();
        map = BadRobotMap.getInstance();
        
        driveTrain  = ProtoDriveTrain.getInstance();
        imageTrackingSystem = BadCameraSystem.getInstance();
    }
    
    public static IDriveTrain getDriveTrain()
    {
        return driveTrain;
    }
    public static BadCameraSystem getImageTrackingSubsystem() 
    {
        return imageTrackingSystem;
    }

    public CommandBase(String name) 
    {
        super(name);
    }

    public CommandBase() 
    {
        super();
    }
}
