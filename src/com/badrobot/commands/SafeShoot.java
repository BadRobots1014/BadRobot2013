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
 *
 * @author Isaac
 */
public class SafeShoot extends BadCommand
{
    //Temporary time delay for the shooter (until we get the optical sensor).
    double timeWhenShot;
    boolean hasReset;
    static double SHOT_DELAY = 2;
    
    boolean hasPushed;
    
    double shooterSpeed;
    static double REQUIRED_SHOOTER_SPEED = 5200;
    
    public SafeShoot()
    {
        requires((Subsystem) frisbeePusher);
        requires((Subsystem) shooter);
    }
    
    protected void initialize() 
    {
        //timeWhenShot = 0;
    }
    
    private boolean isShooterReadyToShoot()
    {
        //return(Utility.getFPGATime() >= (timeWhenShot + SHOT_DELAY*1000000));
        
        return (shooterSpeed >= REQUIRED_SHOOTER_SPEED);
    }

    private void push()
    {
        if (frisbeePusher.isFrisbeeRetracted() && !isShooterReadyToShoot())
        {
            frisbeePusher.stopFrisbeePusher();
            //hasReset = false;
        }
        
        else 
        {
            frisbeePusher.pushFrisbee(true);
        }
        
        /*if (!frisbeePusher.isFrisbeeRetracted() && !hasReset)
        {
            //timeWhenShot = Utility.getFPGATime();
            hasReset = true;
        }*/
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
        else if (OI.isPrimaryAButtonPressed())
        {
            shooter.runShooter(1.0);
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
        
        
        
        //raise/lower shooter
        if (OI.isSecondaryAButtonPressed())
        {
            shooter.lowerShooter();
            
            height--;
            log("lowering");
            SmartDashboard.putNumber("shooter height", height);
        }
        else if (OI.isSecondaryBButtonPressed())
        {
            shooter.raiseShooter();
            
            height++;
            SmartDashboard.putNumber("shooter height", height);
        }
        else
        {
            shooter.lockShooterArticulator();
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
