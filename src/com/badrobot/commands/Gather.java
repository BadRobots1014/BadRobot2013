/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Kevin Edwards
 */
public class Gather extends BadCommand
{
    public int gatherAmount;
    public int storageCount;
    
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
        
        gatherer.gather();
        gatherer.raiseToShooter();
        storageCount++;
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
}
