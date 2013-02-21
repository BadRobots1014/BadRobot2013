/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.OI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * This command will demonstrate our lights by cycling through
 * the possible colors in a smooth constant change.
 * 
 * @author Jon Buckley
 */
public class DemonstrateLighting extends BadCommand
{
    private int red = 255, 
            blue = 0, 
            green = 0;
    
    private int sweepingColor = 0;
    
    
    public DemonstrateLighting()
    {
        requires((Subsystem) lightSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
        switch (sweepingColor)
        {
            //red
            case 0:
                green++; 
                if (green >= 255)
                {
                    green = 255;
                    blue++;
                    
                    if (blue >= 255)
                    {
                        blue = 0;
                        green = 255;
                        sweepingColor = 1;
                        red = 0;
                    }
                }
                break;
            
            //green
            case 1: 
                red++;
                if (red >= 255)
                {
                    red = 255;
                    blue++;
                    
                    if (blue >= 255)
                    {
                        blue = 255;
                        green = 0; 
                        red = 0;
                        
                        sweepingColor = 2;
                    }
                }
                break;
                
            //blue    
            case 2:
                red++;
                if (red >= 255)
                {
                    red = 255;
                    green++;
                    
                    if (green >= 255)
                    {
                        blue = 0; 
                        red = 255;
                        green = 0;
                        sweepingColor = 0;
                    }
                }
                break;
        }
        
        lightSystem.setColor(red, green, blue);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        if (OI.isPrimaryLeftJoyClick())
            return true;
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
        return "Demonstrate Lighting";
    }
}
