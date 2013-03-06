/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import edu.wpi.first.wpilibj.DriverStation;
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
    static double TOLERANCE = .2;
    static double TURN_SPEED = .5;
    
    public AimWithCamera()
    {
        requires((Subsystem) driveTrain);
        requires((Subsystem) shooterArticulator);
        SmartDashboard.putNumber("Auto Aim Time Out In Seconds", TIME_OUT_IN_SECONDS);
        SmartDashboard.putNumber("Auto Aim Turn Speed", TURN_SPEED);
        SmartDashboard.putNumber("Auto Aim Tolerance", TOLERANCE);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
        table = NetworkTable.getTable("IMGPROC");
    }

    double SWEET_SPOT_X = .22,
            SWEET_SPOT_Y = -.07;
    
    double timeSince;
    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
        double targetX = table.getNumber("target_x");
        double targetY = table.getNumber("target_y");
        timeSince = table.getNumber("time_since");
        
        TIME_OUT_IN_SECONDS = SmartDashboard.getNumber("Auto Aim Time Out In Seconds");
        TURN_SPEED = SmartDashboard.getNumber("Auto Aim Turn Speed");
        TOLERANCE = SmartDashboard.getNumber("Auto Aim Tolerance");
        
        if(timeSince > TIME_OUT_IN_SECONDS || timeSince == -1) 
        {
            log("hang on..... ");
            driveTrain.tankDrive(0, 0);
            return;
        }
        else if ((SWEET_SPOT_X - targetX) <  -TOLERANCE)
        {
            //turn left
            log("turning left");
            driveTrain.tankDrive(TURN_SPEED, -TURN_SPEED);
        }
        else if ((SWEET_SPOT_X - targetX) > TOLERANCE)
        {
            //turn right
            log("turning right");
            driveTrain.tankDrive(-TURN_SPEED, TURN_SPEED);
        }
        else
            driveTrain.tankDrive(0,0);
        
        
        if (DriverStation.getInstance().getDigitalIn(2))
        {
            if(timeSince > TIME_OUT_IN_SECONDS || timeSince == -1) 
            {
                log("hang on..... ");
                shooterArticulator.lockShooterArticulator();
                return;
            }
            else if ((SWEET_SPOT_Y - targetY) <  -TOLERANCE)
            {
                //go up
                shooterArticulator.raiseShooter();
            }
            else if ((SWEET_SPOT_Y - targetY) > TOLERANCE)
            {
                //go down
                shooterArticulator.lowerShooter();
            }
            else
                shooterArticulator.lockShooterArticulator();
        }
        log(targetX + " -> target x,   timeSince-> " + timeSince);
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        double targetX = table.getNumber("target_x");
        double targetY = table.getNumber("target_y");

        if(Math.abs(SWEET_SPOT_X - targetX) < TOLERANCE  &&
                Math.abs(SWEET_SPOT_Y - targetY) < TOLERANCE){
            driveTrain.tankDrive(0,0);
            shooterArticulator.lockShooterArticulator();
            log("finished...   targetX : " + targetX);
            return true; //for now keep running
        }
        return false;
    }

    // Called once after isFinished returns true
    protected void end()
    {
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
}
