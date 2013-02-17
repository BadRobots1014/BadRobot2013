/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.OI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Isaac
 */
public class ControlLighting extends BadCommand
{
    boolean isOn = false;
    boolean hasPressed = false;
    
    public ControlLighting()
    {
        requires ((Subsystem) lightSystem);
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
        boolean isLBPressed = OI.isSecondaryLBButtonPressed();
        
        if (isLBPressed)
        {
            hasPressed = true;
        }
        if (hasPressed && !isLBPressed)
        {
            isOn = !isOn;
            hasPressed = false;
        }
        
        if (isOn)
        {
            lightSystem.turnOn();
        }
        else
        {
            lightSystem.turnOff();
        }
        
        lightSystem.setColor((int)OI.getSeondaryControllerLeftStickY()*255, 
                        (int)OI.getSecondaryControllerRightStickY()*255, 
                        (int)OI.getSecondaryControllerRightStickX()*255);
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
