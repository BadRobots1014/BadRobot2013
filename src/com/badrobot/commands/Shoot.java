/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.BadPreferences;
import com.badrobot.OI;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * Runs the shooter at either the set speed or set time and speed;
 * We do not currently use this command.
 * 
 * @author Paul_Chao
 */
public class Shoot extends BadCommand
{
    double shooterSpeed = .4;
    double shooterRunTime;
    double startTime;
    Timer timer;
    //private ITable ITable;
    
    public Shoot() 
    {
        requires( (Subsystem) shooter);
        //SmartDashboard.putNumber("abc", 1);//this method deals with smartDashboard 
        //smartdash is still under constructing, put it in later.
    }
    
    //second constructor for shooter testing
    public Shoot(double speed)
    {
        requires( (Subsystem) shooter);
        //SmartDashboard.putNumber("abc", 1);//this method deals with smartDashboard 
        //smartdash is still under constructing, put it in later.
        shooterSpeed = speed;
        shooterRunTime = 5;
    }
    //third constructor for autonomous
    public Shoot(double time, double speed)
    {
        requires( (Subsystem) shooter);
        SmartDashboard.putNumber("abc", 1);//this method deals with smartDashboard 
        //smartdash is still under constructing, put it in later.
        shooterRunTime = time;
        shooterSpeed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
        //contructed.
        
        startTime = timer.getFPGATimestamp();
        shooter.runShooter(shooterSpeed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
        shooter.runShooter(shooterSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        if((Timer.getFPGATimestamp()-startTime) > shooterRunTime)
            return true;
        //for debug
        if(OI.primaryXboxController.getBumper(GenericHID.Hand.kLeft) || 
                OI.primaryXboxController.getRawButton(1))
            return true;

        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
        shooter.runShooter(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
        shooter.runShooter(0);
    }

    public String getConsoleIdentity() 
    {
        return ("Shoot");
    }

    /*public void putSmartDashboardValues(ITable table) 
    {
        table.putNumber("Shooter Speed", shooterSpeed);
    }
    
    public void getSmartdashboardValues(ITable table)
    {
        table.getNumber(null);
    }*/

    public void valueChanged(ITable itable, String key, Object value, boolean bln) 
    {
        
    }

    protected void addNetworkTableValues(ITable table) 
    {
        
    }

    public void registerPreferencesValues()
    {
    }
}
