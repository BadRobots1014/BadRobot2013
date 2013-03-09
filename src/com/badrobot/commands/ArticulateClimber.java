/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * Articulate the climber arms with controller.
 * 
 * @author Isaac
 */
public class ArticulateClimber extends BadCommand
{
    public ArticulateClimber()
    {
        requires ((Subsystem) climberArticulator);
    }
    
    protected void initialize() {
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln) {
    }

    protected void addNetworkTableValues(ITable table) {
    }

    public String getConsoleIdentity() 
    {
        return "AritculateClimber";
    }

    protected void execute() {
    }

    protected boolean isFinished() 
    {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

    public void registerPreferencesValues() {
    }
    
}
