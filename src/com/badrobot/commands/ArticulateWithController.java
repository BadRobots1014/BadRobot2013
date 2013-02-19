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
        if (OI.isSecondaryBButtonPressed())
        {
            shooterArticulator.raiseShooter();
        }
        else if (OI.isSecondaryAButtonPressed())
        {
            shooterArticulator.lowerShooter();
        }
        else
        {
            shooterArticulator.lockShooterArticulator();
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
    
}
