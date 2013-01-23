/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.badrobot;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import com.badrobot.commands.CommandBase;
import com.badrobot.commands.ExampleCommand;
import com.badrobot.commands.autonomousCommands.DriveForwardAndShoot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotMain extends IterativeRobot 
{
    Command autonomousCommand; //Autonomous Command
    SendableChooser autoChooser; //adds a widget to the SmartDashboard
    //for selection.
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() 
    {
        // Initialize all subsystems
        CommandBase.init();
        
        autoChooser = new SendableChooser();
        //Replace ExampleCommand() with autonomous command. 
        //Currently there are none.
        autoChooser.addDefault("Default program", new DriveForwardAndShoot());
        autoChooser.addObject("Other program 1", new ExampleCommand());
        SmartDashboard.putData("Autonomous mode chooser", autoChooser);
    }

    public void autonomousInit() 
    {
        //It will get the selected Command from the SmartDashboard
        autonomousCommand = (Command) autoChooser.getSelected();
        autonomousCommand.start();
    }
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() 
    {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        Watchdog.getInstance().setEnabled(false);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() 
    {
        Scheduler.getInstance().run();   
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() 
    {
        LiveWindow.run();
    }
}
