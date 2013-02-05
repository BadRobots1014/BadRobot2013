/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.OI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Isaac
 */
public class CoopDriveWithTriggers extends BadCommand
{
    protected boolean TANK_DRIVE_MODE = true;
    public CoopDriveWithTriggers()
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
        if (TANK_DRIVE_MODE)
            driveTrain.tankDrive(-OI.getPrimaryLeftTrigger()+OI.getPrimaryRightTrigger(),
                        -OI.getSecondaryLeftTrigger()+OI.getSecondaryRightTrigger());
        
        else
            driveTrain.arcadeDrive(OI.getPrimaryControllerLeftStickY(), OI.getPrimaryControllerLeftStickX());
        
        //log("Distance To Wall: "+driveTrain.getDistanceToWall());     //works fine.
        //log("Gyro Angle: "+driveTrain.getGyro().getAngle());
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
}
