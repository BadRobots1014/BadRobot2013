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
 * Drives the robot in a straight line (using the gyro) for either
 * the set amount of time or the default time.
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
    private double distance = 0;
    
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
        driveTime = 5*1000000;
    }
    
    /**
     * Runs the command for the set time length.
     * @param setTime Time length in seconds.
     */
    public DriveStraightForward(double setTime)
    {
        long temp = (long) setTime*1000000;
        driveTime = temp;
        distance = -1;
    }
    
    public DriveStraightForward(double setTime, double distanceInYards)
    {
        driveTime = -1;
        distance = distanceInYards;
    }
    
    public String getConsoleIdentity() 
    {
        return "DriveStraightForward";
    }
    
    protected void initialize() 
    {
        setSpeed = .8;
        scaleFactor = 1;
        
        driveTrain.getGyro().reset();
        startTime = Utility.getFPGATime();      //returns fpga time in MICROseconds.
    }
    
    protected void execute() 
    {
        gyroAngle = driveTrain.getGyro().getAngle();
        
        switch (state)
        {
            //Drives robot straight until the angle is greater than 5 degrees either direction.
            case DRIVING_STRAIGHT:
                if (gyroAngle < -2)
                {
                    state = TURNING_RIGHT;
                }
                else if (gyroAngle > 2)
                {
                    state = TURNING_LEFT;
                }
                else
                {
                    driveTrain.tankDrive(setSpeed, setSpeed);
                }
                break;
            
            //Turns the robot to the right until it is less than 5 degrees off center.
            case TURNING_RIGHT:
                if (Math.abs(gyroAngle) <= 2)
                {
                    state = DRIVING_STRAIGHT;
                }
                else
                {
                    double scaleCandidate = 1 - Math.abs(gyroAngle*0.025);
                    scaleFactor = (scaleCandidate < .1) ? 0 : scaleCandidate;
                    driveTrain.tankDrive(setSpeed, setSpeed*scaleFactor);
                }
                break;
                
            //Turns the robot to the left until it is less than 5 degrees off center.
            case TURNING_LEFT:
                if (Math.abs(gyroAngle) <= 2)
                {
                    state = DRIVING_STRAIGHT;
                }
                else
                {
                    double scaleCandidate = 1 - Math.abs(gyroAngle*0.025);
                    scaleFactor = (scaleCandidate < .1) ? 0 : scaleCandidate;
                    driveTrain.tankDrive(setSpeed*scaleFactor, setSpeed);
                }
                break;
            case FINISHED:
                driveTrain.tankDrive(0, 0);
                break;
        }
    }
    
    protected boolean isFinished() 
    {
        //if by time
        if (driveTime > 0 && Utility.getFPGATime() >= startTime + driveTime)
        {
            log("time limit hit");
            driveTrain.tankDrive(0, 0);
            state = FINISHED;
            return true;
        }
        else if (distance > 0 && driveTrain.getDistanceToWall() < distance*36)
        {
            log("distance limit hit");
            return true;
        }
        
        return false;
    }

    protected void end() 
    {
        driveTrain.tankDrive(0,0);
    }
    
    protected void interrupted() 
    {
        
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln) {
    }

    protected void addNetworkTableValues(ITable table) {
    }

    public void registerPreferencesValues()
    {
    }
}
