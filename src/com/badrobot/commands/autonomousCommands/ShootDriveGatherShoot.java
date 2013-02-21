/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands.autonomousCommands;

import com.badrobot.commands.DriveForward;
import com.badrobot.commands.Gather;
import com.badrobot.commands.RaiseToShooter;
import com.badrobot.commands.Shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Kevin Edwards
 */
public class ShootDriveGatherShoot extends CommandGroup
{
    //public double driveTime = .4;
    public int gatherCount = 2;
    public double shootTime = 3.0;
    public double shootTime2 = shootTime;
    public double shootSpeed = .4;
    public ShootDriveGatherShoot()
    {
        
        addSequential(new Shoot(shootSpeed, shootTime));
        addSequential(new DriveForward(/*driveTime*/));//Drive forward currently (1/29) does not accept a time parameter
        addSequential(new Gather(gatherCount));
        addSequential(new RaiseToShooter());
        addSequential(new Shoot(shootSpeed, shootTime2));
    }
    
    
}
