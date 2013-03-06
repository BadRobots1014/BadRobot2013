/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.subsystems.interfaces.IGatherer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * Raises the frisbee to the shooter using the gatherer which
 * does not yet exist.
 * 
 * @author Kevin Edwards
 */
public class RaiseToShooter extends BadCommand
{
    public RaiseToShooter()
    {
        requires( (Subsystem) gatherer);
    }

    protected void initialize() 
    {
        
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln) 
    {
        
    }

    protected void addNetworkTableValues(ITable table) 
    {
        
    }

    public String getConsoleIdentity() {
        return "Raise To Shooter";
    }

    protected void execute() 
    {
        if(gatherer.getPosition() != IGatherer.Position.DEPOSITING)
        {
            gatherer.raiseToShooter();
        }
    }

    protected boolean isFinished() 
    {
        if (gatherer.getPosition() == IGatherer.Position.DEPOSITING)
                {
                    return true;
                }else
            return false;
        
    }

    protected void end() {
    }

    protected void interrupted() {
    }

    public void registerPreferencesValues()
    {
    }
}
