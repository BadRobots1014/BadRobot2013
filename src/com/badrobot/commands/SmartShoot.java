/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.OI;
import edu.wpi.first.wpilibj.Timer;
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
    private static double PUSH_INTERVAL = 1;
    private static double PUSH_DELAY = 2;
    
    public SmartShoot()
    {
        requires((Subsystem) shooter);
        requires((Subsystem) frisbeePusher);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
    }

    double lastPushTime = 0;
    double timeSinceLastPush = 0;
    double shooterStartTime = -1;
    
    boolean pushingFrisbee = false;
    //runs the shooter, pushes frisbees in at intervals.
    protected void execute()
    {        
        if (!frisbeePusher.isFrisbeeRetracted())
        {
            frisbeePusher.pushFrisbee(true);
            pushingFrisbee = true;
        }
        
        timeSinceLastPush = Timer.getFPGATimestamp() - lastPushTime;
        if (OI.getSecondaryRightTrigger() > 0)
        {
            if (shooterStartTime < 0)
                shooterStartTime = Timer.getFPGATimestamp();
            shooter.runShooter(1);
            
            double timeSinceShooterStart = Timer.getFPGATimestamp() - shooterStartTime;
            log("time since last push: " + timeSinceLastPush);
            log("time since shooter running: " + timeSinceShooterStart);
            log("is retracted: " + frisbeePusher.isFrisbeeRetracted());
            
            if (frisbeePusher.isFrisbeeRetracted() && 
                    timeSinceLastPush > PUSH_INTERVAL &&
                     timeSinceShooterStart > PUSH_DELAY)
            {
                frisbeePusher.pushFrisbee(true);
                pushingFrisbee = true;
                
                log("pushing frisbee...");
            }
            
            else if (frisbeePusher.isFrisbeeRetracted() && pushingFrisbee)
            {
                frisbeePusher.stopFrisbeePusher();
                lastPushTime = Timer.getFPGATimestamp();
                pushingFrisbee = false;
                
                log("stopping pushing...");
            }
        }
        
        else
        {
            if (shooterStartTime > 0)
                shooterStartTime = -1;
            
            shooter.runShooter(0);
            
            if (frisbeePusher.isFrisbeeRetracted())
                frisbeePusher.stopFrisbeePusher();
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
        shooter.runShooter(0);
        frisbeePusher.stopFrisbeePusher();
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
