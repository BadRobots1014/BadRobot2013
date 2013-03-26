/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands.autonomousCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Isaac
 */
public class DriveForwardTurnShootDriveBack extends CommandGroup
{
    public DriveForwardTurnShootDriveBack()
    {
        addSequential(new DriveForwardToWallTurnAndShoot());
        addSequential(new ShootAndDriveBack());
    }
}
