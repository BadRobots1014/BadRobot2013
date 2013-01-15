/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.OI;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.badrobot.commands.CommandBase;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 *
 * @author Isaac
 */
public class DriveForward extends BadCommand
{
    RobotDrive train;
    public static Gyro gyro;
    public static double speedscale = 1;
    public double autoSpeed = .2;
    public double gyroAngle = gyro.getAngle();    
    private double a = 1 - Math.abs(gyro.getAngle()*0.025);
    public double alteredSpeed = autoSpeed;
    public double alteredSpeed2 = autoSpeed;
    
    public DriveForward()
    {
        requires((Subsystem) driveTrain);
    }
    
    public String getConsoleIdentity() 
    {
        return "DriveForward";
    }
    
    protected void initialize() 
    {
        driveTrain.getGyro().reset();
    }
    
    protected void execute() 
    {
        if (alteredSpeed2 > 1)//Just in case
            alteredSpeed2 = 1;
        if (alteredSpeed > 1)
            alteredSpeed = 1;
        if (a <= 0)  //Without this, if the robot rotated over 40 degrees either direction, it would begin to move backwards which we don't need.
            a = 0;
        
        //I changed some things around so that when it needs to turn, 
        //it will slow down the inside set of wheels instead of speeding up the outer set.
        //This will make tighter turns and slower overall movements.
        train.tankDrive(alteredSpeed, alteredSpeed2);
        if (gyro.getAngle() > 0)
            alteredSpeed2 = autoSpeed*a;
        if (gyro.getAngle() < 0)
            alteredSpeed = autoSpeed*a;        
    }
    
    protected boolean isFinished() 
    {
        return false;
    }

    protected void end() 
    {
        
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
        
    }
}
