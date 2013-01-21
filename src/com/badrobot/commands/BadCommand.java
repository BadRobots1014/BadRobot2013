/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.OI;
import com.badrobot.subsystems.interfaces.Logger;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author adrian
 */
public abstract class BadCommand extends CommandBase implements Logger 
{
    protected static final boolean CONSOLE_OUTPUT_ENABLED = true;

    public abstract String getConsoleIdentity();
    
    public SmartDashboard smartDash;
    
    public void log(String str) 
    {
        if(true) 
        {
            System.out.println(getConsoleIdentity()+": "+str);
            //smartDash
        }
    }
    
    //public abstract void putSmartDashboardValues(ITable table);
    
    //public abstract void getSmartdashboardValues(ITable table);
}
