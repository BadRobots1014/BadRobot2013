/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Paul_Chao
 */
public class Shooter extends CommandBase 
{
    double shooterSpeed;
    
    public Shooter(double speed) 
    {
        requires( (Subsystem) shooter);
        shooterSpeed = speed;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
        shooter.runShooter(shooterSpeed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
        shooter.runShooter(shooterSpeed);
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
        shooter.runShooter(0);
    }
}
