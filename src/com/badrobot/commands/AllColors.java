/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author joyce
 */
public class AllColors extends BadCommand {

    int red; 
    int green;
    int blue;
    

    protected void initialize() 
    {
        requires ((Subsystem) lightSystem);
        red = 0;
        blue = 0;
        green = 0;
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln) {
    }

    protected void addNetworkTableValues(ITable table) {
    }

    public String getConsoleIdentity() {
        return "16,581,375 colors";
    }

    protected void execute() 
    {
        blue++;
        if (blue >= 255)
        {
            green++;
            blue = 0;
        }
        if (green >= 255)
        {
            red++;
            green = 0;
        }
        lightSystem.setColor(red, green, blue);
           
    }

    protected boolean isFinished() {
        if(red == 255 && blue == 255 && green ==255)
            return true;
        else
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
