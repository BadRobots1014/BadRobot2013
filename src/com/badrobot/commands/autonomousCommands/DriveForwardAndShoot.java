/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands.autonomousCommands;

import com.badrobot.commands.DriveForward;
import com.badrobot.commands.Shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Paul_Chao
 */
public class DriveForwardAndShoot extends CommandGroup 
{
  
    public DriveForwardAndShoot() 
    {
        addSequential(new DriveForward());
        addSequential(new Shoot());
    }
}
