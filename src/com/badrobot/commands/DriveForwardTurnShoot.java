/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Kyle Compton
 */
public class DriveForwardTurnShoot extends CommandGroup 
{
    
    public DriveForwardTurnShoot() 
    {
        addSequential(new DriveStraightForward(3));
        addSequential(new Turn(90));
        //addSequential(new Shoot());
    }
}
