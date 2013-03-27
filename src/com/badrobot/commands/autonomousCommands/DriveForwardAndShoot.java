/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands.autonomousCommands;

import com.badrobot.BadPreferences;
import com.badrobot.commands.DriveForward;
import com.badrobot.commands.DriveStraightForward;
import com.badrobot.commands.SafeShoot;
import com.badrobot.commands.Shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive forward for the set amount of time and shoot 3 frisbees.
 * 
 * @author Paul_Chao
 */
public class DriveForwardAndShoot extends CommandGroup 
{
  
    public DriveForwardAndShoot() 
    {
        double driveForwardTime = Double.parseDouble(BadPreferences.getValue("DRIVE_FORWARD_TIME", "4.0"));
        
        addSequential(new DriveStraightForward(driveForwardTime));
        addParallel(new SafeShoot(3));
    }
}
