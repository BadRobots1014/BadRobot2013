/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands.autonomousCommands;

import com.badrobot.commands.InjectFrisbee;
import com.badrobot.commands.Shoot;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Isaac
 */
public class AutoAimAndShoot extends CommandGroup
{
    public AutoAimAndShoot()
    {
        addParallel(new Shoot(8, 1.0));
        addSequential(new AimWithCamera());
        
        addSequential(new InjectFrisbee(3));
    }
}
