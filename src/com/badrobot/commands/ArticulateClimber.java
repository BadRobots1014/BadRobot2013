/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.OI;
import com.badrobot.subsystems.Climber;
import com.badrobot.subsystems.interfaces.IClimber;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * Articulate the climber arms with controller;
 * 
 * Demo Mode (Primary Controller): 
 * Left Joy Click   set the climber all the way up;
 * Right Joy Click  set the climber all the way down;
 * A Button         lower the climber;
 * B Button         raise the climber;
 * 
 * Not Demo Mode (Secondary Controller):
 * Y Button         set the climber all the way up;
 * X Button         set the climber all the way down;
 * Left Joy Click   raise the climber;
 * Right Joy Click  lower the climber;
 * Left Trigger     zero the climber position.
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
        
        if (OI.isDemoMode())
        {
            if (OI.isPrimaryLeftJoyClick())
            {
                climberArticulator.setPosition(IClimber.kUp);
            }          
            else if (OI.isPrimaryRightJoyClick())
            {
                climberArticulator.setPosition(IClimber.kDown);
            }           
            
            else if (OI.isPrimaryAButtonPressed())
            {
                climberArticulator.lowerClimber();
            }
            else if (OI.isPrimaryBButtonPressed())
            {
                climberArticulator.raiseClimber();
            }
            else 
            {
                ((Climber) climberArticulator).climberController.set(0.0);
                climberArticulator.lockClimber();
            }
        }
        
        else
        {
            if (OI.isSecondaryLeftJoyClick())
            {
                climberArticulator.raiseClimber();
            }
            else if (OI.isSecondaryRightJoyClick())
            {
                climberArticulator.lowerClimber();
            }
            else if (OI.isSecondaryXButtonPressed())
            {
                climberArticulator.setPosition(IClimber.kDown);
            }          
            else if (OI.isSecondaryYButtonPressed())
            {
                climberArticulator.setPosition(IClimber.kUp);
            }   
            else 
            {
                climberArticulator.lockClimber();
            }
            
            if (OI.getSecondaryLeftTrigger() > 0)
            {
                climberArticulator.zeroPosition();
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

    public void registerPreferencesValues() {
    }
    
}
