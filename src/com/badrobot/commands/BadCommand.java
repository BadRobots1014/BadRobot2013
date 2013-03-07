/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.IPreferencesDataSource;
import com.badrobot.OI;
import com.badrobot.subsystems.interfaces.Logger;
import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITableListener;

/**
 * An abstract class that all commands should extend.
 * 
 * @author adrian
 */
public abstract class BadCommand extends CommandBase implements Logger, NamedSendable, ITableListener,
        IPreferencesDataSource
{
    protected static final boolean CONSOLE_OUTPUT_ENABLED = true;
    
    protected BadCommand()
    {
        registerPreferencesValues();   
    }
    
    /**
     * Subclasses should implement their own implementations on initialize. This
     * method is meant to instantiate any hardware or variables that will be 
     * needed. This is specific to each class and can be left blank.
     */
    protected abstract void initialize();
    
    //provides automatic NetworkTable compliance
    protected ITable table;
    
    /**
     * provides compliance with Sendable interface. This automatically adds the
     * subsystem to the SmartDashboard.
     */
    public ITable getTable()
    {
        log("getting table");
        return table;
    }
    
    /**
     * initializes table, handing a table down to store values in
     * @param t the table that should be stored and have values added to
     */
    public void initTable(ITable t)
    {
        table = NetworkTable.getTable(this.getConsoleIdentity());
        addNetworkTableValues(table);
        
        if (table != null)
            table.removeTableListener(this);
        table = t;
        
        table.addTableListener(this);
        
        //Hack -- I am very sorry future generations of Team 1014ers 
        registerPreferencesValues();
    }
    
    /**
     * Event handler for when a value of a variable put in the network has 
     * changed. This should have some sort of logic to figure out which variable
     * it was the key parameter and change the local value of the variable to 
     * reflect it.
     * @param key the name of the variable in the NetworkTable
     * @param value the value that the variable has been changed to
     */
     public abstract void valueChanged(ITable itable, String key, Object value, boolean bln);
          
    /**
     * puts all pertinent values into the table (speeds, frequencies, any value 
     * that would be tracked or modified)
     * @param table the table that is being initialized and needs values
     */
    protected abstract void addNetworkTableValues(ITable table);

    public abstract String getConsoleIdentity();
        
    public void log(String str) 
    {
        if(true) 
        {
            System.out.println(getConsoleIdentity()+": "+str);
        }
    }
    
    
}
