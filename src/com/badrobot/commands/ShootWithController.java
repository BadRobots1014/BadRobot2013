/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.OI;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import com.badrobot.commands.Shoot;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Jon Buckley
 */
public class ShootWithController extends CommandBase
{
    boolean runShooter;
    double shooterSpeed;
    
    public ShootWithController()
    {
        requires((Subsystem) shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
        shooterSpeed = .4;
        runShooter = false;
        
        SmartDashboard.putNumber("shooter height", 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
        if (OI.primaryXboxController.getBumper(GenericHID.Hand.kLeft))
        {
            if (!runShooter)
            {
                shooter.runShooter(shooterSpeed);
                runShooter = true;
            }
            
            else
            {
                shooter.runShooter(0);
                runShooter = false;
            }            
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    }
}
