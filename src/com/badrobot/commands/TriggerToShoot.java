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
    
    public TriggerToShoot()
    {
        requires((Subsystem) shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
        shooterSpeed = .4;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
        //TESTING THE TRIGGER
        //3 - Triggers (Each trigger = 0 to 1, axis value = right - left
        //http://www.chiefdelphi.com/forums/showthread.php?threadid=82825
        
        //if(OI.primaryXboxController.getRawAxis(3)>=0)//right trigger
        //if (OI.primaryXboxController.getTrigger(GenericHID.Hand.kLeft))
        
        double rightTriggerValue = OI.isPrimaryLBButtonPressed() ? -1 : 0;
        if(rightTriggerValue != 0)
        {
            SmartDashboard.putBoolean("shooterRunning", true);
            shooter.runShooter(rightTriggerValue);
        }
        else 
        {    
            SmartDashboard.putBoolean("shooterRunning", false);
            shooter.runShooter(0);
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
