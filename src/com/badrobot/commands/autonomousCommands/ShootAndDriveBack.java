/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands.autonomousCommands;

import com.badrobot.BadPreferences;
import com.badrobot.commands.DriveStraightBackwards;
import com.badrobot.commands.SafeShoot;
import com.badrobot.commands.Turn;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Turn right and drive straight backwards.
 * 
 * @author Isaac
 */
public class ShootAndDriveBack extends CommandGroup
{
    public ShootAndDriveBack()
    {
        double driveBackwardsTime = Double.parseDouble(BadPreferences.getValue("DRIVE_BACKWARDS_TIME", "5.0"));
        
        //addSequential(new SafeShoot(3), 3);
        addSequential(new Turn(15));
        addSequential(new DriveStraightBackwards(driveBackwardsTime));
    }
}
