/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Paul_Chao
 */
public class DriveForward extends BadCommand 
{
    Timer timer;
    //boolean finished;
    double driveTime;
    double startTime;
    
    private final double DRIVE_SPEED = 1;
    
    public DriveForward()
    {
        requires( (Subsystem) driveTrain);
        timer = new Timer();
        SmartDashboard.putNumber("xyz", 1);//this method deals with smartDashboard 
        //smartdash is still under constructing, put it in later.
    }
    
    public DriveForward(double time) 
    {
        requires( (Subsystem) driveTrain);
        timer = new Timer();
        //finished = false;
        driveTime = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
        driveTime = SmartDashboard.getNumber("xyz");//keys not yet constructed
        
        startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
        driveTrain.tankDrive(DRIVE_SPEED, DRIVE_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return ((Timer.getFPGATimestamp()-startTime) > driveTime);
    }

    // Called once after isFinished returns true
    protected void end() 
    {
        driveTrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
        driveTrain.tankDrive(0, 0);
    }
    
    public String getConsoleIdentity() 
    {
        return "Drive forward for set time";
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln) 
    {
        
    }

    protected void addNetworkTableValues(ITable table) 
    {
        
    }
}
