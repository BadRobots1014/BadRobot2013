/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Jon Buckley
 */
public class InjectFrisbee extends BadCommand
{
    //the state machine variable
    private int state;
    
    //time variable used to time events
    private double startTime;
    
    //the time that the motor should run forward and back
    private static double PUSH_LIMIT = 1; //seconds
    
    //static state variables, used in state machine
    public static final int BOOTING = 0,
                        PUSHING_FORWARD = 1,
                        READY_TO_RETRACT = 2,
                        RETRACTING = 3,
                        FINISHED = 4;
    
    public InjectFrisbee()
    {
        requires((Subsystem) shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
        //we dont want to have the shooter trying to run the frisbee pushing motors
        //for more than a PUSH_TIME either way, this ensures it
        this.setInterruptible(false);
        state = BOOTING;
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
        //state machine
        switch (state)
        {
            //initializing, grabs start time
            case BOOTING:
                startTime = Timer.getFPGATimestamp();
                state = PUSHING_FORWARD;
                break;
            
            //pushes frisbee for PUSH_TIME seconds
            case PUSHING_FORWARD:
                if (shooter.isFrisbeePusherAtMaximumExtension())
                {
                    state = READY_TO_RETRACT;
                    shooter.stopFrisbeePusher();
                    break;
                }
                //This is backup code to make sure we aren't running longer than the push limit.
                else if (Timer.getFPGATimestamp() >= startTime + PUSH_LIMIT)
                {
                    state = FINISHED;
                    shooter.stopFrisbeePusher();
                    break;
                }
                
                shooter.pushFrisbee(true);
                break;
                
           //grabs start time     
            case READY_TO_RETRACT:
                startTime = Timer.getFPGATimestamp();
                state = RETRACTING;
                break;
                
           //retracts piston for PUSH_TIME seconds     
            case RETRACTING:
                if (shooter.isFrisbeePusherAtMaximumExtension())
                {
                    state = FINISHED;
                    shooter.stopFrisbeePusher();
                    break;
                }
                //This is backup code to make sure we aren't running for like 20 millions seconds. 
                else if (Timer.getFPGATimestamp() >= startTime + PUSH_LIMIT)
                {
                    state = FINISHED;
                    shooter.stopFrisbeePusher();
                    break;
                }
                
                shooter.pushFrisbee(false);
                break;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        //done when we are finished with our state machining
        return (state == FINISHED);
    }

    // Called once after isFinished returns true
    protected void end()
    {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
        log("This command cannot be interrupted. Wait your turn, Im almost out of the shower.");
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln)
    {
    }

    protected void addNetworkTableValues(ITable table)
    {
    }

    public String getConsoleIdentity()
    {
        return "InjectFrisbee Command";
    }
}
