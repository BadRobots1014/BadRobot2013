/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Kyle Compton
 */
public class Turn extends BadCommand
{
    public double turnAngle;
    public double angleDifference;
    public int state;
    
    /**
     * turns the robot at the given angle
     * @param angle the angle of the radius of the turn
     */
    public Turn(double angle)
    {
        requires((Subsystem) driveTrain);
        turnAngle = angle;
    }
    
    protected void initialize() 
    {
        log("reset");
        driveTrain.getGyro().reset();
    }

    public String getConsoleIdentity() 
    {
        return "Turn";
    }

    protected void execute() 
    {
        if(turnAngle < 0)
            driveTrain.tankDrive(-.6, .6);
        else if(turnAngle > 0)
            driveTrain.tankDrive(.6, -.6);
    }

    protected boolean isFinished() 
    {
        angleDifference = driveTrain.getGyro().getAngle() - turnAngle;
        if(Math.abs(angleDifference) < 2)
            return true;
        return false;
    }

    protected void end() 
    {
        driveTrain.getTrain().tankDrive(0,0);
    }

    protected void interrupted() {
    }
    
    public void valueChanged(ITable itable, String key, Object value, boolean bln) {
    }

    protected void addNetworkTableValues(ITable table) {
    }

    public void registerPreferencesValues()
    {
    }
}
