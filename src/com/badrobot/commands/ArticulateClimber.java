/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.OI;
import com.badrobot.subsystems.Climber;
import com.badrobot.subsystems.interfaces.IClimber;
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
        
        if (OI.isDemoMode())
        {
            if (OI.isSecondaryLeftJoyClick())
            {
                climberArticulator.setPosition(IClimber.kDown);
            }          
            else if (OI.isSecondaryRightJoyClick())
            {
                climberArticulator.setPosition(IClimber.kUp);
                log ("right joy click!");
            }           
            
            else if (OI.isSecondaryAButtonPressed())
            {
                climberArticulator.lowerClimber();
            }
            else if (OI.isSecondaryBButtonPressed())
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
            if (OI.isSecondaryXButtonPressed())
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
