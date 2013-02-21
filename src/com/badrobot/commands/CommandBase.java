package com.badrobot.commands;

import com.badrobot.BadRobotMap;
import com.badrobot.OI;
import com.badrobot.subsystems.*;
import com.badrobot.subsystems.interfaces.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * 
 * EXPLANATION: Whats up with this class? - Well here goes, using a CommandBase 
 * system is very useful when programming. The big benefit is that it allows 
 * programmers to build up code without a physical robot. It also centralizes
 * the access to hardware components (ie Drive Train or Shooter). 
 * ---CommandBase is extended BY Commands (ie DriveWithJoystick)---
 * 
 * @author Author
 */
public abstract class CommandBase extends Command 
{
    //simple defintions: 
    //static - this allows the variable to be used in this class. 
    //public - the variable can be accessed outside of this class
    //protected or private - the variable canNOT be used outside the class
    
    public static OI oi; //Operation Interface - Associates OI Buttons with
    //commands and groups
    
    public static BadRobotMap map; //It tells us what port (a port is one of the 
    //many places in that blue thingy on the robot >.<'). The port is associated 
    //with hardware parts. Say port one is the FrontLeftJaguar (a jaguar is 
    //a speed controller, controls voltage to the motor, hence speed of motor).
    //So the map can be used to access harware components on the robot.
    
    // Create a single static instance of all of your subsystems:
    //Subsystems are a compilation of hardware components that together act 
    //together to work magic - DriveTrain, uses 4 jaguars to control 4 motors.
    public static IDriveTrain driveTrain;
    public static IShooter shooter;
    public static IShooterArticulator shooterArticulator;
    public static IGatherer gatherer;
    public static IFrisbeePusher frisbeePusher;
    public static BadCameraSystem imageTrackingSystem;
    public static SendableChooser driveChooser;
    public static ILights lightSystem;
    public static ICameraLight cameraLight;

    //Initilizes all of static variables
    public static void init() 
    {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        
        
        //note the getInstance() method, ALWAYS use it when you call them. 
        //Reason why? The getInstance() will not create a new one, it will 
        //give you the existing copy with all the correct values
        map = BadRobotMap.getInstance();
        
        //driveTrain = ProtoDriveTrain.getInstance();
        driveTrain  = DriveTrain.getInstance();
        shooter = Shooter.getInstance();
        frisbeePusher = FrisbeePusher.getInstance();
        shooterArticulator = ShooterArticulator.getInstance();
        //imageTrackingSystem = BadCameraSystem.getInstance();
        lightSystem = DecorativeLights.getInstance();
        cameraLight = CameraLight.getInstance();
        
        driveChooser = new SendableChooser();
        driveChooser.addDefault("Tank Drive", "tankDrive");
        driveChooser.addObject("Arcade Drive", "arcadeDrive");
        SmartDashboard.putData("driveChooser", driveChooser);
        
        oi = new OI();
        oi.init();
    }
    
    //Accessor Methods: remember that protected driveTrain above? How would you 
    //get to use it outside of the class? You make accessor methods as such 
    //below. The benefits: the variable cannot be changed but can be used.
    public static IDriveTrain getDriveTrain()
    {
        return driveTrain;
    }
    public static BadCameraSystem getImageTrackingSubsystem() 
    {
        return imageTrackingSystem;
    }

    //Constructor
    public CommandBase(String name) 
    {
        super(name);
    }

    //Default Constructor
    public CommandBase() 
    {
        super();
    }
}
