/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.subsystems.interfaces.IGatherer;
import com.badrobot.subsystems.interfaces.IGatherer.Position;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * Gathers the frisbees with our gatherer which does not exist.
 * 
 * @author Kevin Edwards
 */
public class Gather extends BadCommand
{
    public int gatherAmount;
    public int storageCount;
    public int step; // in autonomous, gives the phase the robot is in
    //1 is lowering gatherer
    //2 is running gatherer
    //3 is raising gatherer to drop into storage
    
    
    
    public Gather(int amount) 
    {
        requires( (Subsystem) gatherer);
        gatherAmount = amount;
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

    public String getConsoleIdentity() 
    {
        return "Gather";
    }

    protected void execute() 
    {
        if(gatherer.getPosition() == IGatherer.Position.STOWED)
        {
            gatherer.lowerToActive();
        }
        if(gatherer.getPosition() == IGatherer.Position.ACTIVE)
        {
            gatherer.gather();
        }
    }

    protected boolean isFinished() {
        if(storageCount == gatherAmount)
        {
            return true;
        }else
        return false;
    }

    protected void end() 
    {
        
    }

    protected void interrupted() 
    {
        
    }

    public void registerPreferencesValues()
    {
    }
}
