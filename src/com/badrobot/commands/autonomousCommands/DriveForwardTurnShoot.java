/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands.autonomousCommands;

import com.badrobot.commands.DriveStraightForward;
import com.badrobot.commands.InjectFrisbee;
import com.badrobot.commands.Shoot;
import com.badrobot.commands.Turn;
import edu.wpi.first.wpilibj.Timer;
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
        addParallel(new Shoot(6, 1.0));
        addSequential(new Turn(30));
        //time, power
        addSequential(new InjectFrisbee(4));
    }
}
