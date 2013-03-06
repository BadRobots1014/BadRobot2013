/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands.autonomousCommands;

import com.badrobot.BadPreferences;
import com.badrobot.commands.BadCommand;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * This command will position the robot onto the center of the
 * detected goal using our vision tracking code.
 * 
 * @author Jon Buckley
 */
public class AimWithCamera extends BadCommand
{
    NetworkTable table;
    static double TIME_OUT_IN_SECONDS = .5;
    static double TOLERANCE = .15;
    static double TURN_SPEED = .5;
    
    static String toleranceKey = "AUTO_AIM_TOLERANCE";
    static String turnKey = "AUTO_AIM_TURN_SPEED";
    
    public AimWithCamera()
    {
        requires((Subsystem) driveTrain);
        requires((Subsystem) shooterArticulator);        
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
        table = NetworkTable.getTable("IMGPROC");
        
        SmartDashboard.getNumber(turnKey, TURN_SPEED);
        SmartDashboard.putNumber(toleranceKey, TOLERANCE);
    }

    private static double SWEET_SPOT_X = .22,
            SWEET_SPOT_Y = -.07;
    
    private static int NUMBER_CYCLES_TO_VERIFY = 3;
    
    private static double Kp = .2;
    
    double timeSince;
    double targetX = -2, targetY = -2,
            deltaX, deltaY;
    
    //keeps track of how many cycles the robot thinks it's aligned. This is to
    //help weed out inconsitencies
    int numberOfCyclesXAligned = 0,
            numberOfCyclesYAligned = 0;
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
        targetX = table.getNumber("target_x");
        targetY = table.getNumber("target_y");
        timeSince = table.getNumber("time_since");
        
        deltaX = (SWEET_SPOT_X - targetX);
        deltaY = (SWEET_SPOT_Y - targetY);
        
        TIME_OUT_IN_SECONDS = SmartDashboard.getNumber("Auto Aim Time Out In Seconds", TIME_OUT_IN_SECONDS);
        TURN_SPEED = SmartDashboard.getNumber("Auto Aim Turn Speed", TURN_SPEED);
        TOLERANCE = SmartDashboard.getNumber("Auto Aim Tolerance", TOLERANCE);
       
        //if too much lag, stops the robot where it is and waits for a better connection
        if(timeSince > TIME_OUT_IN_SECONDS || timeSince == -1) 
        {
            driveTrain.tankDrive(0, 0);
            shooterArticulator.lockShooterArticulator();
            return;
        }
        
        //x Adjustment
        if (Math.abs(deltaX) < TOLERANCE)
        {
            driveTrain.tankDrive(0,0);
            numberOfCyclesXAligned++;
        }
        else
        {
            //not aligned, reset number of cycles X Aligned
            if (numberOfCyclesXAligned != 0)
                numberOfCyclesXAligned = 0;
            
            if (deltaX <  -TOLERANCE)
            {     
                //proportional speed (the P of PID -- we dont need no goddamn ID)
                driveTrain.tankDrive(deltaX*Kp + TURN_SPEED, -deltaX*Kp - TURN_SPEED);
            }
            else if (deltaX > TOLERANCE)
            {
                //protportional speed
                driveTrain.tankDrive(-deltaX*Kp - TURN_SPEED, deltaX*Kp + TURN_SPEED);
            }
        }
            
        
        //y Adjustment
        if (DriverStation.getInstance().getDigitalIn(2))
        {  
            if (Math.abs(deltaY) < TOLERANCE)
            {
                shooterArticulator.lockShooterArticulator();
                numberOfCyclesYAligned++;
            }
            else
            {
                //not aligned, reset number of cycles Y Aligned
                if (numberOfCyclesYAligned != 0)
                    numberOfCyclesYAligned = 0;
                
                if (deltaY < -TOLERANCE)
                {
                    //go up
                    shooterArticulator.raiseShooter();
                }
                else if (deltaY > TOLERANCE)
                {
                    //go down
                    shooterArticulator.lowerShooter();
                }
            }    
        }
        else
            numberOfCyclesYAligned = NUMBER_CYCLES_TO_VERIFY;
        log(targetX + " -> target x,   timeSince-> " + timeSince);
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        if(Math.abs(SWEET_SPOT_X - targetX) < TOLERANCE  &&
                Math.abs(SWEET_SPOT_Y - targetY) < TOLERANCE &&
                numberOfCyclesXAligned >= NUMBER_CYCLES_TO_VERIFY &&
                numberOfCyclesYAligned >= NUMBER_CYCLES_TO_VERIFY){
            return true;
        }
        return false;
    }

    // Called once after isFinished returns true
    protected void end()
    {
        driveTrain.tankDrive(0,0);
        shooterArticulator.lockShooterArticulator();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln)
    {
    }

    protected void addNetworkTableValues(ITable table)
    {
    }

    public String getConsoleIdentity()
    {
        return "AimWithCamera";
    }

    public void registerPreferencesValues()
    {
        //BadPreferences.registerValue(toleranceKey, "" + TOLERANCE);
        //BadPreferences.registerValue(turnKey, "" + TURN_SPEED);
    }
}
