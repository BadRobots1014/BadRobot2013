/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.BadRobotMap;
import com.badrobot.OI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * This command will drive the robot using the right and left primary
 * joysticks; Primary RB and LB will turn the robot slowly right or left
 * for easy aiming adjustments;
 * 
 * This is the command we mainly use when driving and shooting;
 * 
 * v1.2 Working instructions for drivetrain, tested on Sevrin 2/11/2013.
 * 
 * @author Jon Buckley
 */
public class DriveWithJoysticks extends BadCommand
{
    private double BUMPER_SPEED = .5;
    protected boolean TANK_DRIVE_MODE = true;
    public DriveWithJoysticks()
    {
        requires((Subsystem) driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
        String mode = (String) CommandBase.driveChooser.getSelected();

        if (mode == "tankDrive")
            TANK_DRIVE_MODE = true;
        else
            TANK_DRIVE_MODE = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {   
        if (OI.isPrimaryRBButtonPressed())
        {
            driveTrain.tankDrive(BUMPER_SPEED, -BUMPER_SPEED);
        }
        
        else if (OI.isPrimaryLBButtonPressed())
        {
            driveTrain.tankDrive(-BUMPER_SPEED, BUMPER_SPEED);
        }
        
        else if (TANK_DRIVE_MODE)
        {
            driveTrain.tankDrive(OI.getPrimaryControllerLeftStickY(), OI.getPrimaryControllerRightStickY());
            log ("LEFT Y: " + OI.getPrimaryControllerLeftStickY() + "  RIGHT Y: " +  OI.getPrimaryControllerRightStickY());
        }
        
        else
            driveTrain.arcadeDrive(OI.getPrimaryControllerLeftStickY(), OI.getPrimaryControllerLeftStickX());
        
        
        SmartDashboard.putNumber("ultrasonic distance", driveTrain.getDistanceToWall());
        SmartDashboard.putNumber("gyro angle", driveTrain.getGyro().getAngle());
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
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
        log("I've been interrupted and am deffering to the new Command");
    }

    public String getConsoleIdentity()
    {
        return "DriveWithJoysticks";
    }

    public void valueChanged(ITable itable, String string, Object o, boolean bln) 
    {
        
    }

    protected void addNetworkTableValues(ITable table) 
    {
        
    }

    public void registerPreferencesValues()
    {
    }
}
