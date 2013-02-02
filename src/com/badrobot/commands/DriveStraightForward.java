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
    public double gyroAngle;
    private double scaleFactor;
    private long startTime;
    private long driveTime;
    
    public int state;
    public static final int DRIVING_STRAIGHT = 0,
                            TURNING_RIGHT = 1,
                            TURNING_LEFT = 2;
    
    /**
     * Runs the command for the default time length.
     */
    public DriveStraightForward()
    {
        requires((Subsystem) driveTrain);
        driveTime = 6*1000000;
    }
    
    /**
     * Runs the command for the set time length in seconds.
     */
    public DriveStraightForward(long setTime)
    {
        long temp = setTime*1000000;
        driveTime = temp;
    }
    
    public String getConsoleIdentity() 
    {
        return "DriveStraightForward";
    }
    
    protected void initialize() 
    {
        setSpeed = .2;
        scaleFactor = 1;
        
        driveTrain.getGyro().reset();
        startTime = Utility.getFPGATime();      //returns fpga time in MICROseconds.
    }
    
    protected void execute() 
    {
        gyroAngle = driveTrain.getGyro().getAngle();
        
        //This if statement will make sure the motors do not go in the reverse direction
        //when the angle is over 40 degrees either direction.
        if(scaleFactor <= 0)
            scaleFactor = 0;
        
        switch (state)
        {
            //Drives robot straight until the angle is greater than 5 degrees either direction.
            case DRIVING_STRAIGHT:
                if (gyroAngle > 5)
                {
                    state = TURNING_RIGHT;
                }
                else if (gyroAngle < -5)
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
                if (gyroAngle <= 5)
                {
                    state = DRIVING_STRAIGHT;
                }
                else
                {
                    double scaleCandidate = 1 - Math.abs(gyroAngle*0.025);
                    scaleFactor = (scaleCandidate < .1) ? 0 : scaleCandidate;
                    driveTrain.getTrain().tankDrive(setSpeed, setSpeed*scaleFactor);
                }
                break;
                
            //Turns the robot to the left until it is less than 5 degrees off center.
            case TURNING_LEFT:
                if (gyroAngle >= 5)
                {
                    state = DRIVING_STRAIGHT;
                }
                else
                {
                    double scaleCandidate = 1 - Math.abs(gyroAngle*0.025);
                    scaleFactor = (scaleCandidate < .1) ? 0 : scaleCandidate;
                    driveTrain.getTrain().tankDrive(setSpeed*scaleFactor, setSpeed);
                }
                break;
        }
    }
    
    protected boolean isFinished() 
    {
        if (Utility.getFPGATime() >= startTime + driveTime)
        {
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
