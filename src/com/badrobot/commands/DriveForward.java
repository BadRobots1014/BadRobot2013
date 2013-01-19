/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

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
        startTime = timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
        driveTrain.tankDrive(DRIVE_SPEED, DRIVE_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return ((timer.getFPGATimestamp()-startTime) > driveTime);
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
}
