/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.OI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *SAME CODE AS SHOOT WITH CONTROLLER, JUST THAT TRIGGER IS TESTED
 * @author Jon Buckley
 */
public class TriggerToShoot extends CommandBase
{
    double shooterSpeed;
    double height = 100;
    
    public TriggerToShoot()
    {
        requires((Subsystem) shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
        shooterSpeed = .4;
        
        //SmartDashboard.putNumber("shooter height", height);
    }

    boolean triggerWasDepressed = false;
    boolean shoot = false;
    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
        //TESTING THE TRIGGER
        //3 - Triggers (Each trigger = 0 to 1, axis value = right - left
        //http://www.chiefdelphi.com/forums/showthread.php?threadid=82825
        
        //if(OI.primaryXboxController.getRawAxis(3)>=0)//right trigger
        //if (OI.primaryXboxController.getTrigger(GenericHID.Hand.kLeft))
        
        boolean isTriggerDepressed = OI.isSecondaryLBButtonPressed();
        
        if (isTriggerDepressed)
        {
            triggerWasDepressed = true;
        }
        
        if (triggerWasDepressed && !isTriggerDepressed)
        {
            shoot = !shoot;
            triggerWasDepressed = false;
        }
        
        if (shoot || OI.isPrimaryAButtonPressed())
            shooter.runShooter(1);
        else
            shooter.runShooter(0);
        

        
        /*
        double shooterSetValue = OI.isSecondaryLBButtonPressed() ? -1 : 0;
        if(shooterSetValue != 0)
        {
            SmartDashboard.putBoolean("shooterRunning", true);
            shooter.runShooter(shooterSetValue);
        }
        else 
        {    
            SmartDashboard.putBoolean("shooterRunning", false);
            shooter.runShooter(0);
        }*/
        
        //shooter articulation sensing
        if (OI.isSecondaryAButtonPressed())
        {
            shooter.lowerShooter();
            
            height--;
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

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return false;
    }

    // Called once after isFinished returns true
    protected void end()
    {
        shooter.runShooter(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    }
}
