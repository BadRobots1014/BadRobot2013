/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.OI;
import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Subsystem;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Isaac
 */
public class DriveStraightForward extends BadCommand
{    
    public double setSpeed;
    public double gyroAngle;   
    private double alteredSpeedLeft;
    private double alteredSpeedRight;
    private double scaleFactor;
    private long fpgaTime;
    private long setRunTime;
    
    public DriveStraightForward()
    {
        requires((Subsystem) driveTrain);
        setRunTime = 20*1000000;
    }
    
    public DriveStraightForward(long setTime)
    {
        long temp = setTime*1000000;
        setRunTime = temp;
    }
    
    public String getConsoleIdentity() 
    {
        return "DriveStraightForward";
    }
    
    protected void initialize() 
    {        
        setSpeed = .2;
        alteredSpeedLeft = setSpeed;
        alteredSpeedRight = setSpeed;   
        scaleFactor = 1;
        driveTrain.getGyro().reset();
        fpgaTime = Utility.getFPGATime();      //returns time in microseconds.
    }
    
    protected void execute() 
    {        
        gyroAngle = driveTrain.getGyro().getAngle();
        scaleFactor = 1 - Math.abs(gyroAngle*0.025);
        if (scaleFactor <= 0)
            scaleFactor = 0;
        
        if (gyroAngle > 0)
            alteredSpeedRight = setSpeed*scaleFactor;
        if (gyroAngle < 0)
            alteredSpeedLeft = setSpeed*scaleFactor;
        
        if (alteredSpeedLeft >= 1)
            alteredSpeedLeft = 1;
        if (alteredSpeedRight >= 1)
            alteredSpeedRight = 1;
        
        /**
         * Drives the robot in a straight path with speed setSpeed;
         * If the robot's forward direction is changed, it will realign
         * itself by slowing the appropriate set of wheels.
         */
        driveTrain.getTrain().tankDrive(alteredSpeedLeft, alteredSpeedRight);
    }
    
    protected boolean isFinished() 
    {
        if (Utility.getFPGATime() == fpgaTime + setRunTime)
            return true;
        return false;
    }

    protected void end() 
    {
        
    }
    
    protected void interrupted() 
    {
        
    }
}
