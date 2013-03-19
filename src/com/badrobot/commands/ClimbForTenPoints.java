/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.BadPreferences;
import com.badrobot.OI;
import com.badrobot.subsystems.interfaces.IClimber;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Jon Buckley
 */
public class ClimbForTenPoints extends BadCommand
{
    double bearing;
    static double DRIVE_SPEED;
    static String driveSpeedKey = "CLIMBING_DRIVE_SPEED";
    static double Kp = .01;
    
    public ClimbForTenPoints()
    {
        requires((Subsystem) driveTrain);
        requires((Subsystem) shooterArticulator);
        requires((Subsystem) climberArticulator);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
        bearing = driveTrain.getGyro().getAngle();
        DRIVE_SPEED = Double.parseDouble(BadPreferences.getValue(driveSpeedKey, ".4"));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
        climberArticulator.setPosition(IClimber.kUp);
        
        //go straight
        driveTrain.getTrain().drive(DRIVE_SPEED,
                -(driveTrain.getGyro().getAngle() - bearing) * Kp);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return (OI.isPrimaryBButtonPressed());
    }

    // Called once after isFinished returns true
    protected void end()
    {
        driveTrain.tankDrive(0, 0);
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
        return "Climb For Ten Points";
    }

    public void registerPreferencesValues()
    {
        BadPreferences.registerValue(driveSpeedKey, ".4");
    }
}
