/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.BadPreferences;
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
public class DriveStraightBackwards extends BadCommand
{    
    double bearing;
    static double DRIVE_SPEED;
    static String driveSpeedKey = "CLIMBING_DRIVE_SPEED";
    static double Kp = .05;
    
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
    public DriveStraightBackwards()
    {
        requires((Subsystem) driveTrain);
        driveTime = 5*1000000;
    }
    
    /**
     * Runs the command for the set time length.
     * @param setTime Time length in seconds.
     */
    public DriveStraightBackwards(double setTime)
    {
        long temp = (long) setTime*1000000;
        driveTime = temp;
        distance = -1;
    }
    
    public DriveStraightBackwards(double setTime, double distanceInInches)
    {
        driveTime = -1;
        distance = distanceInInches;
    }
    
    public String getConsoleIdentity() 
    {
        return "DriveStraightBackwards";
    }
    
    double delayTime;
    protected void initialize() 
    {
        setSpeed = .8;
        scaleFactor = 1;
        
        driveTrain.getGyro().reset();
        bearing = driveTrain.getGyro().getAngle();
        startTime = Utility.getFPGATime();      //returns fpga time in MICROseconds.
        
        DRIVE_SPEED = Double.parseDouble(BadPreferences.getValue(driveSpeedKey, ".8"));
        delayTime = Double.parseDouble(BadPreferences.getValue("DRIVE_STRAIGHT_FORWARD_WITH_DISTANCE_DELAY", "2.4"));

    }
    
    private int lockedOnIterations = 0;
    protected void execute() 
    {
        //with time
        if (distance <= 0)
        {
       
            driveTrain.getTrain().drive(-DRIVE_SPEED,
                    -(driveTrain.getGyro().getAngle() - bearing) * Kp);
        }
        
        //with distance
        else
        {
            if (driveTrain.getDistanceToWall() < distance && Utility.getFPGATime() - startTime > delayTime*1000000)
            { 
                lockedOnIterations++;  
            }
            else
            { 
                if (lockedOnIterations > 0)
                    lockedOnIterations = 0;

                    driveTrain.getTrain().drive(DRIVE_SPEED, 
                            -(driveTrain.getGyro().getAngle() - bearing) * Kp);
                
            }
        }
    }
    
    protected boolean isFinished() 
    {
        //if by time
        if (driveTime > 0 && Utility.getFPGATime() >= startTime + driveTime)
        {
            driveTrain.tankDrive(0, 0);
            state = FINISHED;
            return true;
        }
        else if (distance > 0 && driveTrain.getDistanceToWall() <= distance && lockedOnIterations >= 3)
        {
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
