/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.OI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * This command controls the shooter articulator with the XBox controller;
 * Keep in mind that both will raise and lower if the rope is wrapped around
 * the wrong way, and do not use A to raise or B to lower—if this happens
 * just continue to raise until they switch back to normal;
 * 
 * Secondary B raises the shooter;
 * Secondary A lowers the shooter.
 * 
 * @author Isaac
 */
public class ArticulateWithController extends BadCommand
{
    public ArticulateWithController()
    {
        requires ((Subsystem) shooterArticulator);
    }

    protected void initialize() {
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln) {
    }

    protected void addNetworkTableValues(ITable table) {
    }

    public String getConsoleIdentity() 
    {
        return "ArticulateWithControllerS";
    }

    protected void execute() 
    {
        if (OI.isDemoMode())
        {
            if (OI.isPrimaryLeftJoyClick())
            {
                shooterArticulator.raiseShooter();
            }
            else if (OI.isPrimaryRightJoyClick())
            {
                shooterArticulator.lowerShooter();
            }
            else
            {
                shooterArticulator.lockShooterArticulator();
            }
        }
        
        else
        {
            if (OI.isSecondaryBButtonPressed())
            {
                shooterArticulator.raiseShooter();
            }
            else if (OI.isSecondaryAButtonPressed())
            {
                shooterArticulator.lowerShooter();
            }
            else if (OI.getSeondaryControllerLeftStickY() != 0)
            {
                log ("left stick change");
                shooterArticulator.raiseShooter(OI.getSeondaryControllerLeftStickY());
            }
            else
            {
                shooterArticulator.lockShooterArticulator();
            }
            
            
        }
        
    }

    protected boolean isFinished() 
    {
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
