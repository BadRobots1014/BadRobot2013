/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands.autonomousCommands;

import com.badrobot.commands.DriveStraightForward;
import com.badrobot.commands.SafeShoot;
import com.badrobot.commands.Shoot;
import com.badrobot.commands.Turn;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive straight forward, turn, auto aim with camera, and shoot 3 frisbees.
 * 
 * @author Jon Buckley
 */
public class DriveForwardAutoAimShoot extends CommandGroup
{
    
    public DriveForwardAutoAimShoot()
    {
       //seconds driving
       addSequential(new DriveStraightForward(5.0));
       //angle 
       addParallel(new Turn(30));
       //time in seconds, power
       addParallel(new Shoot(8, 1.0));
       addSequential(new AimWithCamera());
       //number of frisbees to inject (iterations)
       addParallel(new SafeShoot(3));
    }
}
