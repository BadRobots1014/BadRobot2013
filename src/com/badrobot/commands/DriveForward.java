/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Paul_Chao
 */
public class DriveForward extends BadCommand 
{
    
    //the state machine variable
    private int state;
    
     //time variable used to time events
    private double startTime;
    
    //the time that the motor should run forward and back
    private static final double DRIVE_TIME = 5; //seconds
    
    //static state variables, used in state machine
    public static final int BOOTING = 0,
                        DRIVING_FORWARD = 1,
                        FINISHED = 2;
    
    
    private final double DRIVE_SPEED = 1;
    
    public DriveForward()
    {
        requires( (Subsystem) driveTrain);
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
        switch (state)
        {
            //initializing, grabs start time
            case BOOTING:
                startTime = Timer.getFPGATimestamp();
                state = DRIVING_FORWARD;
                break;
            
            //pushes frisbee for PUSH_TIME seconds
            case DRIVING_FORWARD:
                if (Timer.getFPGATimestamp() >= startTime + DRIVE_TIME)
                {
                    state = FINISHED;
                    CommandBase.driveTrain.tankDrive(0, 0);
                    break;
                }
                
                CommandBase.driveTrain.tankDrive(DRIVE_SPEED, DRIVE_SPEED);
                break;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     *
     * @return
     */
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
         log("This command cannot be interrupted. Wait your turn, I have to flush the toilet.");
    }
    
    public String getConsoleIdentity() 
    {
        return "Drive forward for set time";
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln) 
    {
        
    }

    protected void addNetworkTableValues(ITable table) 
    {
        
    }
}
