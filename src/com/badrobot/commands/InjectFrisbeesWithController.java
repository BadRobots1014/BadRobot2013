/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.BadRobotMap;
import com.badrobot.OI;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GearTooth;
import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Isaac
 */
public class InjectFrisbeesWithController extends BadCommand
{
    //Temporary time delay for the shooter (until we get the optical sensor).
    double timeWhenShot;
    double shooterSpeed;
    double shotDelay;
    
    public InjectFrisbeesWithController()
    {
        requires((Subsystem) frisbeePusher);
    }
    
    protected void initialize() 
    {
        shooterSpeed = shooter.getShooterSpeed();
        
        timeWhenShot = 0;
        shotDelay = 0.5;
    }
    
    private boolean isShooterDelayedEnough()
    {
        return(Utility.getFPGATime() >= (timeWhenShot + shotDelay*1000000));
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
        if (frisbeePusher.isFrisbeeRetracted() && !isShooterDelayedEnough())
        {
            frisbeePusher.stopFrisbeePusher();
        }
        else if (frisbeePusher.isFrisbeeRetracted() && OI.isSecondaryRBButtonPressed() && isShooterDelayedEnough())
        {
            frisbeePusher.pushFrisbee(true);
            timeWhenShot = Utility.getFPGATime();
        }
    }

    protected boolean isFinished() 
    {
        return false;
    }

    protected void end() 
    {
    }

    protected void interrupted() 
    {
    }
}
