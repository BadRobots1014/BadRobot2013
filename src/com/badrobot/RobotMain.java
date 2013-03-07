/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.badrobot;


import com.badrobot.commands.autonomousCommands.AutoAimAndShoot;
import com.badrobot.commands.autonomousCommands.DriveForwardTurnShoot;
import com.badrobot.commands.*;
import com.badrobot.commands.autonomousCommands.AimWithCamera;
import com.badrobot.commands.autonomousCommands.DriveForwardAutoAimShoot;
import com.badrobot.subsystems.interfaces.ILights;
import com.badrobot.subsystems.interfaces.Logger;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotMain extends IterativeRobot implements Logger
{
    Command autonomousCommand; //Autonomous Command
    SendableChooser autoChooser; //adds a widget to the SmartDashboard
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() 
    {
        BadRobotMap.isPrototype = true;
        
        // Initialize all subsystems
        CommandBase.init();
               
        autoChooser = new SendableChooser();
        
        autoChooser.addDefault("Drive Forward + Auto Fire", new DriveForwardAutoAimShoot());
        autoChooser.addObject("Drive Straight Forward + Turn (5s , 20 deg)", new DriveStraightForward(5));
        autoChooser.addObject("Auto Aim", new AimWithCamera());
        
        SmartDashboard.putData("Autonomous mode chooser", autoChooser);
        
        if (CommandBase.lightSystem != null)
            new RunLights(ILights.kYellow).start();
    }
   
    public void autonomousInit() 
    {
        autonomousCommand = (Command) autoChooser.getSelected();        
        Scheduler.getInstance().add(autonomousCommand);
        
        Command turnOnCameraLight = new TurnOnCameraLight();
        Scheduler.getInstance().add(turnOnCameraLight);
    }
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() 
    {
        Scheduler.getInstance().run();
        Watchdog.getInstance().feed();
    }

    public void teleopInit() {
        Watchdog.getInstance().setEnabled(false);
        
        Command driveTrainControls = new DriveWithController();
        Scheduler.getInstance().add(driveTrainControls);
        
        Command shooterControls = new SafeShoot();
        Scheduler.getInstance().add(shooterControls);
        
        Command articulate = new ArticulateWithController();
        Scheduler.getInstance().add(articulate);
        
        if (CommandBase.lightSystem != null)
            Scheduler.getInstance().add(new RunLights(ILights.kRed));
    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() 
    {  
        Scheduler.getInstance().run();
        Watchdog.getInstance().feed();
        // Timer.delay(.1);
    }
    /**
     * This function is called periodically during test mode
     * this comment was ammended by Joe Cssidy
     */
    public void testPeriodic() 
    {
        LiveWindow.run();
    }

    public void log(String out)
    {
        if (OI.CONSOLE_OUTPUT_ENABLED)
            System.out.println("RobotMain: " + out);
    }
}
