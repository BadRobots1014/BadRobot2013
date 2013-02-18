/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.OI;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Isaac
 */
public class ControlLighting extends BadCommand
{
    boolean isOn = false;
    boolean hasPressed = false;

    int red = 0, blue = 0, green = 0;
    
    int colorSweeping = 0;
    
    public ControlLighting()
    {
        requires ((Subsystem) lightSystem);
        lightSystem.turnOn();
        SmartDashboard.putNumber("Red Channel", red);
        SmartDashboard.putNumber("Green Channel", green);
        SmartDashboard.putNumber("Blue Channel", blue);
    }
    
    protected void initialize() {
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln) {
    }

    protected void addNetworkTableValues(ITable table) {
    }

    public String getConsoleIdentity() 
    {
        return "ControlLighting";
    }

    protected void execute() 
    {
        red = (int) SmartDashboard.getNumber("Red Channel");
        green = (int) SmartDashboard.getNumber("Green Channel");
        blue = (int) SmartDashboard.getNumber("Blue Channel");
        
        
        /*switch (colorSweeping)
        {
            //red
            case 0:
                red++;
                if (red > 255)
                {
                    red = 255;
                    green = 0;
                    colorSweeping = 1;
                }
                break;
            //blue
            case 1:
                blue++;
                if (blue > 255)
                {
                    blue = 255;
                    colorSweeping = 2;
                }
                 break;
            //green    
            case 2:
                green++;
                if (green > 255)
                {
                    green = 255;
                    colorSweeping = 0;
                    red = 0; 
                    blue = 0;
                }
                break;                
        }*/
        
        /*red = (int) (MathUtils.rint(.4) * 255);
        green = (int) (MathUtils.rint(.4) * 255);
        blue = (int) (MathUtils.rint(.4) * 255);*/
        lightSystem.setColor(red, green, blue);
    }

    protected boolean isFinished() 
    {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
