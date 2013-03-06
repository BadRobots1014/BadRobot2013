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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * This command will control both the shooter and the Frisbee pusher;
 * 
 * Secondary RB will run the shooter and only push when the shooters
 * are up to the required shooter speed;
 * 
 * Secondary X will run the shooter alone;
 * Secondary Y will run the Frisbee pusher alone.
 * 
 * @author Isaac
 */
public class SafeShoot extends BadCommand
{
    boolean hasReset;
    boolean hasPushed;
    
    double shooterSpeed;
    static double REQUIRED_SHOOTER_SPEED = 4800;
    
    public SafeShoot()
    {
        requires((Subsystem) frisbeePusher);
        requires((Subsystem) shooter);
        
        SmartDashboard.putNumber("MAX SHOOTER SPEED IN Auto Shoot", REQUIRED_SHOOTER_SPEED);
    }
    
    protected void initialize() 
    {
    }
    
    private boolean isShooterReadyToShoot()
    {
        REQUIRED_SHOOTER_SPEED = SmartDashboard.getNumber("MAX SHOOTER SPEED IN Auto Shoot", 5200);
        return (shooterSpeed >= REQUIRED_SHOOTER_SPEED);
    }

    private void push()
    {
        if (frisbeePusher.isFrisbeeRetracted() && !isShooterReadyToShoot())
        {
            frisbeePusher.stopFrisbeePusher();
        }
        
        else 
        {
            frisbeePusher.pushFrisbee(true);
        }
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
        shooterSpeed = shooter.getShooterSpeed();
        
        SmartDashboard.putNumber("shooter speed", shooterSpeed);
        SmartDashboard.putBoolean("Shooter Is Ready", isShooterReadyToShoot());
        SmartDashboard.putBoolean("frisbee pusher", frisbeePusher.isFrisbeeRetracted());
        
        if (OI.isDemoMode())
        {
            if (OI.isPrimaryXButtonPressed())
            {
                shooter.runShooter(1.0);
            }
            else
            {
                shooter.runShooter(0);
            }

            if (OI.isPrimaryYButtonPressed())
            {
                frisbeePusher.pushFrisbee(true);
            }
            else if (OI.getPrimaryRightTrigger() > 0)
            {
                shooter.runShooter(1);
                push();
            }
            else
            {
                if (!frisbeePusher.isFrisbeeRetracted())
                {
                    frisbeePusher.pushFrisbee(true);
                }
                

                else
                {
                    frisbeePusher.stopFrisbeePusher();
                }
            }   
        }
        
        else 
        {
            if (OI.isSecondaryXButtonPressed())
            {
                shooter.runShooter(1.0);
            }
            else
            {
                shooter.runShooter(0);
            }

            if (OI.isSecondaryYButtonPressed())
            {
                frisbeePusher.pushFrisbee(true);
            }
            else if (OI.isSecondaryRBButtonPressed())
            {
                shooter.runShooter(1);
                push();
            }
            else
            {
                if (!frisbeePusher.isFrisbeeRetracted())
                {
                    frisbeePusher.pushFrisbee(true);
                }

                else
                {
                    frisbeePusher.stopFrisbeePusher();
                }
            }
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

    public void registerPreferencesValues()
    {
    }
}
