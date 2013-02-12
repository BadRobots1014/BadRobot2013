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
public class InjectFrisbeesWithController extends BadCommand
{

    public InjectFrisbeesWithController()
    {
        requires((Subsystem) frisbeePusher);
    }
    
    protected void initialize() {
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln) {
    }

    protected void addNetworkTableValues(ITable table) {
    }

    public String getConsoleIdentity() 
    {
        return "InjectFrisbeesWithController";
    }

    protected void execute()
    {
        if (frisbeePusher.isFrisbeeRetracted() && !OI.isSecondaryRBButtonPressed())
        {
            frisbeePusher.stopFrisbeePusher();
        }
        else if (frisbeePusher.isFrisbeeRetracted() && OI.isSecondaryRBButtonPressed())
        {
            frisbeePusher.pushFrisbee(true);
        }
    }

    protected boolean isFinished() 
    {
        return false;
    }

    protected void end() 
    {
        
    }

    protected void interrupted() {
    }
    
}
