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
                        PUSHING = 1,
                        FINISHED = 4;
    
    public InjectFrisbee()
    {
        requires((Subsystem) frisbeePusher);
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
            //initializing
            case BOOTING:                
                if (frisbeePusher.isFrisbeeRetracted())
                {
                    state = PUSHING;
                    break;
                }
                
                frisbeePusher.pushFrisbee(true);
                break;
            
            //pushes frisbee for one revolution
            case PUSHING:
                if (frisbeePusher.isFrisbeeRetracted())
                {
                    state = FINISHED;
                    break;
                }
                
                frisbeePusher.pushFrisbee(true);
                break;       
            
            case FINISHED:
                frisbeePusher.stopFrisbeePusher();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        frisbeePusher.stopFrisbeePusher();
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
        log("This command cannot be interrupted. Wait your turn, I'm almost out of the shower.");
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
