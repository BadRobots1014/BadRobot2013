/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands.autonomousCommands;

import com.badrobot.commands.DriveStraightForward;
import com.badrobot.commands.SafeShoot;
import com.badrobot.commands.Turn;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Isaac
 */
public class DriveForwardToWallTurnAndShoot extends CommandGroup
{
    public DriveForwardToWallTurnAndShoot()
    {
        this.addSequential(new DriveStraightForward(-1, 109));
        this.addSequential(new Turn(-20));
        this.addParallel(new SafeShoot(3));
    }
}
