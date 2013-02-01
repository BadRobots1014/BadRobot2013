/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.OI;
import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Isaac
 */
public class DriveStraightForward extends BadCommand
{    
    public double setSpeed;
    public double dontEatTheMotors;
    public double gyroAngle;
    private double scaleFactor;
    private long startTime;
    private long driveTime;
    
    public int state = DRIVING_STRAIGHT;
    public static final int DRIVING_STRAIGHT = 0,
                            TURNING_RIGHT = 1,
                            TURNING_LEFT = 2,
                            FINISHED = 3;
    
    /**
     * Runs the command for the default time length.
     */
    public DriveStraightForward()
    {
        requires((Subsystem) driveTrain);
        driveTime = 10*1000000;
    }
    
    /**
     * Runs the command for the set time length in seconds.
     */
    public DriveStraightForward(double setTime)
    {
        long temp = (long) setTime*1000000;
        driveTime = temp;
    }
    
    public String getConsoleIdentity() 
    {
        return "DriveStraightForward";
    }
    
    protected void initialize() 
    {
        state = DRIVING_STRAIGHT;
        
        setSpeed = 1;
        scaleFactor = 1;
        
        driveTrain.getGyro().reset();
        startTime = Utility.getFPGATime();      //returns fpga time in MICROseconds.
    }
    
    protected void execute() 
    {
        gyroAngle = driveTrain.getGyro().getAngle();
        log("Angle: "+gyroAngle);
        
        //This if statement will make sure the motors do not go in the reverse direction
        //when the angle is over 40 degrees either direction.
        if(scaleFactor <= 0)
            scaleFactor = 0;
        
        switch (state)
        {
            //Drives robot straight until the angle is greater than 5 degrees either direction.
            case DRIVING_STRAIGHT:
                if (gyroAngle < -5)
                {
                    state = TURNING_RIGHT;
                }
                else if (gyroAngle > 5)
                {
                    state = TURNING_LEFT;
                }
                else
                {
                    driveTrain.getTrain().tankDrive(setSpeed, setSpeed);
                }
                break;
            
            //Turns the robot to the right until it is less than 5 degrees off center.
            case TURNING_RIGHT:
                if (gyroAngle >= 5)
                {
                    state = DRIVING_STRAIGHT;
                }
                else
                {
                    scaleFactor = 1 - Math.abs(gyroAngle*0.025);
                    dontEatTheMotors = setSpeed*scaleFactor;
                    if(dontEatTheMotors <= 0.2)
                    {
                        dontEatTheMotors = 0;
                    }
                    driveTrain.getTrain().tankDrive(setSpeed, dontEatTheMotors);
                }
                break;
                
            //Turns the robot to the left until it is less than 5 degrees off center.
            case TURNING_LEFT:
                if (gyroAngle <= 5)
                {
                    state = DRIVING_STRAIGHT;
                }
                else
                {
                    scaleFactor = 1 - Math.abs(gyroAngle*0.025);
                    dontEatTheMotors = setSpeed*scaleFactor;
                    if(dontEatTheMotors <= 0.2)
                    {
                        dontEatTheMotors = 0;
                    }
                    driveTrain.getTrain().tankDrive(dontEatTheMotors, setSpeed);
                }
                break;
            case FINISHED:
                driveTrain.tankDrive(0, 0);
                break;
        }
    }
    
    protected boolean isFinished() 
    {
        if (Utility.getFPGATime() >= startTime + driveTime)
        {
            driveTrain.tankDrive(0, 0);
            state = FINISHED;
            return true;
        }
        else
        {
            return false;
        }
    }

    protected void end() 
    {
        driveTrain.getTrain().tankDrive(0,0);
    }
    
    protected void interrupted() 
    {
        
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln) {
    }

    protected void addNetworkTableValues(ITable table) {
    }
}
