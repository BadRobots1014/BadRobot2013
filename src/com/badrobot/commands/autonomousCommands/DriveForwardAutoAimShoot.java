/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands.autonomousCommands;

import com.badrobot.commands.DriveStraightForward;
import com.badrobot.commands.InjectFrisbee;
import com.badrobot.commands.Shoot;
import com.badrobot.commands.Turn;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
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
       addParallel(new InjectFrisbee(3, 2.0));
    }
}
