/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.BadPreferences;
import com.badrobot.BadRobotMap;
import com.badrobot.OI;
import com.badrobot.RobotMain;
import com.badrobot.subsystems.interfaces.ILights;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GearTooth;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * This command will control both the shooter and the Frisbee pusher;
 * While shooting, sets the LEDs to green and back to alliance color when finished;
 * 
 * Demo Mode (Primary Controller):
 * X Button         run the shooter;
 * Y Button         run the cam;
 * Right Trigger    run the shooter and only push when the shooter is up to speed;
 * 
 * Not Demo Mode (Secondary Controller):
 * Right Trigger    run the shooter and only push when the shooter is up to speed;
 * Left Bumper      run the shooter;
 * PRIMARY X Button run the shooter;
 * Right Bumper     run the cam;
 * 
 * @author Isaac
 */
public class SafeShoot extends BadCommand
{
    boolean hasReset;
    boolean hasPushed = false;
    
    double shooterSpeed;
    static double REQUIRED_SHOOTER_SPEED = 5000;
    
    public SafeShoot()
    {
        requires((Subsystem) frisbeePusher);
        requires((Subsystem) shooter);
        //requires((Subsystem) lightSystem);
        
        REQUIRED_SHOOTER_SPEED = Double.parseDouble(BadPreferences.getValue("REQUIRED_SHOOTER_SPEED", "5000"));
    }
    
    public int iterations;
    
    public SafeShoot(int iterations)
    {
        this();
        this.iterations = iterations;
    }
    
    int incumbentColor;
    
    double startingTime = -1;
    protected void initialize() 
    {
        startingTime = Timer.getFPGATimestamp();
        if (lightSystem != null)
        {
            incumbentColor = lightSystem.getColor();
        }
    }
    
    /**
     * Return true if the speed of the shooter wheels are greater than or equal
     * to the required shooter speed value.
     * 
     * @return true or false.
     */
    private boolean isShooterReadyToShoot()
    {
        //Timer.delay(1.0);
        //REQUIRED_SHOOTER_SPEED = SmartDashboard.getNumber("MAX SHOOTER SPEED IN Auto Shoot", 5200);
        return (shooterSpeed >= REQUIRED_SHOOTER_SPEED);
    }

    int pushedBees = 0;
        
    /**
     * Will push a frisbee if either the frisbee pusher is not retracted, or
     * the shooter wheels are up the required speed;
     * 
     * Will stop the frisbee pusher if it is retracted and the shooter is not 
     * up to speed.
     */
    private void push()
    {
        if (frisbeePusher.isFrisbeeRetracted() && !isShooterReadyToShoot())
        {
            frisbeePusher.stopFrisbeePusher();
            hasPushed = false;
        }
        
        else 
        {
            frisbeePusher.pushFrisbee(true);
            hasPushed = true;
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

    boolean shooting = false;
    boolean wasShooting = false;
    
    protected void execute()
    {
        shooterSpeed = shooter.getShooterSpeed();
        
        //SmartDashboard.putNumber("shooter speed", shooterSpeed);
        SmartDashboard.putBoolean("Shooter Is Ready", isShooterReadyToShoot());
        SmartDashboard.putBoolean("frisbee pusher", frisbeePusher.isFrisbeeRetracted());
        
        if (iterations > 0 )
        {
                        shooter.runShooter(1.0);

            
            //Timer.delay(2);
            push();
        }
        
        else if (OI.isDemoMode())
        {
            if (OI.isPrimaryXButtonPressed())
            {
                shooter.runShooter(1.0);
                //shooter.pidRunShooter(1.0);
            }
            else
            {               
                shooter.runShooter(0.0);
                //shooter.pidRunShooter(0);
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
            if (OI.isSecondaryLBButtonPressed() || OI.isPrimaryXButtonPressed())
            {
                shooting = true;
                                shooter.runShooter(1.0);
                                wasShooting = true;

                //shooter.pidRunShooter(1.0);
            }
            else
            {
                shooting = false;
                                shooter.runShooter(0.0);

                //shooter.pidRunShooter(0);
            }

            if (OI.isSecondaryRBButtonPressed())
            {
                frisbeePusher.pushFrisbee(true);
            }
            else if (OI.getSecondaryRightTrigger() > 0)
            {
                shooting = true;
                wasShooting = true;
                shooter.runShooter(1.0);
                //shooter.pidRunShooter(1);
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
        
        if (shooting && lightSystem != null)
            lightSystem.setColor(ILights.kGreen);
        else
        {
            if (wasShooting && lightSystem != null)
            {
                wasShooting = false;
                lightSystem.setColor(RobotMain.ALLIANCE_COLOR);
            }
            
        }
        //else
          //  lightSystem.setColor(ILights.kRed);
    }

    protected boolean isFinished() 
    {
       if (iterations > 0 && (startingTime + iterations) < Timer.getFPGATimestamp())
           return true;
        return false;
    }

    protected void end() 
    {
        log("ended");
        shooter.runShooter(0.0);
    }

    protected void interrupted() 
    {
    }

    public void registerPreferencesValues()
    {
    }
}
