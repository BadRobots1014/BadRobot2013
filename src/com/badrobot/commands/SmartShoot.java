/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.OI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * This command is meant to be a one button solution to shooting frisbees. The shooter
 * should run continously and the frisbee pusher should push frisbees after ever
 * second or two. 
 * @author Jon Buckley
 */
public class SmartShoot extends BadCommand
{
    
    public SmartShoot()
    {
        requires((Subsystem) shooter);
        requires((Subsystem) frisbeePusher);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
    }

    //runs the shooter, pushes frisbees in at intervals.
    protected void execute()
    {
        if (OI.getSecondaryRightTrigger() > 0)
        {
            shooter.runShooter(1);
            
            //TODO inject frisbee code
        }
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
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln)
    {
    }

    protected void addNetworkTableValues(ITable table)
    {
    }

    public String getConsoleIdentity()
    {
        return "SmartShoot";
    }
}
