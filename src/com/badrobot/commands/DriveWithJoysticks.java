/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.OI;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * v1.0 Working instructions for drivetrain, tested on Shelby 1/12/2013
 * @author Jon Buckley
 */
public class DriveWithJoysticks extends BadCommand
{
    
    public DriveWithJoysticks()
    {
        requires((Subsystem) driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
        //implement a sendable chooser
        driveTrain.tankDrive(OI.getPrimaryControllerLeftStickY(), OI.getPrimaryControllerRightStickY());
        driveTrain.arcadeDrive(OI.getPrimaryControllerLeftStickY(), OI.getPrimaryControllerLeftStickX());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return false;
    }

    // Called once after isFinished returns true
    protected void end()
    {
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
        log("I've been interrupted and am deffering to the new Command");
    }

    public String getConsoleIdentity()
    {
        return "DriveWithJoysticks";
    }
}
