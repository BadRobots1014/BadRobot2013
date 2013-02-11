/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Jon Buckley
 */
public class AimWithCamera extends BadCommand
{
    NetworkTable table;
    public AimWithCamera()
    {
        requires((Subsystem) driveTrain);
        
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
        table = NetworkTable.getTable("IMGPROC");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
        double targetX = table.getNumber("target_x");
        double targetY = table.getNumber("target_y");
        double timeSince = table.getNumber("time_since");
        
        if(timeSince > 1) {
            driveTrain.tankDrive(0, 0);
            return;
        }
        if (targetX <  -.1)
            driveTrain.tankDrive(-.5, .5);
        else if (targetX > .1)
            driveTrain.tankDrive(.5, -.5);
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        double targetX = table.getNumber("target_x");
        if(Math.abs(targetX) < .1){
            driveTrain.tankDrive(0,0);
            return false; //for now prevent it from ending
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
