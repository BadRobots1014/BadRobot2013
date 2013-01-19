/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Paul_Chao
 */
public class Shoot extends BadCommand
{
    double shooterSpeed;
    //private ITable ITable;
    
    public Shoot() 
    {
        requires( (Subsystem) shooter);
        smartDash.putNumber("abc", 1);//this method deals with smartDashboard 
        //smartdash is still under constructing, put it in later.
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
        
        shooterSpeed = smartDash.getNumber("abc");//the key has not yet been 
        //contructed.
        
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
}
