/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.OI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * This command controls the shooter articulator with the XBox controller;
 * 
 * Demo Mode (Primary Controller):
 * Left Joy Click   raise the shooter;
 * Right Joy Click  lower the shooter;
 * 
 * Not Demo Mode (Secondary Controller):
 * Left Stick Y     raise and lower the shooter;
 * B Button         raise the shooter;
 * A Button         lower the shooter.
 * 
 * @author Isaac
 */
public class ArticulateShooter extends BadCommand
{
    public ArticulateShooter()
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
                shooterArticulator.raiseShooter(OI.getSeondaryControllerLeftStickY());
            }
            else
            {
                shooterArticulator.lockShooterArticulator();
            }
            SmartDashboard.putNumber("shooter angle", shooterArticulator.getAngle());
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
