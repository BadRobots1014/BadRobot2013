/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * Injects frisbees into the shooter wheels for a set amount of
 * iterations; We currently use SafeShoot to insert frisbees into the shooter.
 * 
 * @author Jon Buckley
 */
public class InjectFrisbee extends BadCommand
{
    //the state machine variable
    private int state;
    
    //time variable used to time events
    private double startTime = 0.0;
    
    //the time that the motor should run forward and back
    private static double PUSH_LIMIT = 1; //seconds
    
    private int iterations = 1;
    private int currentIteration = 0;
    
    //static state variables, used in state machine
    public static final int BOOTING = 0,
                        PUSHING = 1,
                        FINISHED = 4;
    
    public InjectFrisbee()
    {
        requires((Subsystem) frisbeePusher);
    }
    
    public InjectFrisbee(int numIterations)
    {
        requires((Subsystem) frisbeePusher);
        iterations = numIterations;
        currentIteration = 0;
        log("initing, iterations = " + iterations  );
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
        //we dont want to have the shooter trying to run the frisbee pushing motors
        //for more than a PUSH_TIME either way, this ensures it
        this.setInterruptible(false);
        
        startTime = Timer.getFPGATimestamp();
        camLeftStart = false;
        state = PUSHING;
    }
    
    boolean camLeftStart = false;
    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
        log("running command...");
                    log(state + " state, current ite: " + currentIteration);

        //state machine
        switch (state)
        {
            //initializing
            case BOOTING:                
                if (frisbeePusher.isFrisbeeRetracted())
                {
                    state = PUSHING;
                    camLeftStart = false;
                    
                    startTime = Timer.getFPGATimestamp();
                    break;
                }
                
                frisbeePusher.pushFrisbee(true);
                break;
                
            
            //pushes frisbee for one revolution
            case PUSHING:
                if (!frisbeePusher.isFrisbeeRetracted() && !camLeftStart)
                {
                    camLeftStart = true;
                }
                
                else if (frisbeePusher.isFrisbeeRetracted() && camLeftStart)
                {
                    state = FINISHED;
                    break;
                }
                
                frisbeePusher.pushFrisbee(true);
                break;       
            
            //all done
            case FINISHED:
            {
                currentIteration++;
                log("current iteration " + currentIteration);
                
                if(currentIteration < iterations)
                {
                    startTime = Timer.getFPGATimestamp();
                    frisbeePusher.stopFrisbeePusher();
                    Timer.delay(1.0);
                    state = BOOTING;
                }
                
                else
                {   
                    log("max iterations hit, stopping");
                    frisbeePusher.stopFrisbeePusher();
                }
            }
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        //done when we are finished with our state machining
        return (
                (state == FINISHED && currentIteration >= iterations) || 
                ((Timer.getFPGATimestamp() - startTime) > 1.5) );
    }

    // Called once after isFinished returns true
    protected void end()
    {
        frisbeePusher.stopFrisbeePusher();
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
