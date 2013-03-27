/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands.autonomousCommands;

import com.badrobot.BadPreferences;
import com.badrobot.commands.DriveStraightForward;
import com.badrobot.commands.SafeShoot;
import com.badrobot.commands.Turn;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive straight forward until the set distance away from the wall;
 * Turn and shoot 3 frisbees.
 * 
 * @author Isaac
 */
public class DriveForwardToWallTurnAndShoot extends CommandGroup
{
    SafeShoot shoot;
    public DriveForwardToWallTurnAndShoot()
    {
        shoot = new SafeShoot();
        int distance = Integer.parseInt(BadPreferences.getValue("ULTRASONIC_IDEAL_DISTANCE", "109"));
        double angle = Double.parseDouble(BadPreferences.getValue("AUTONOMOUS_TURNING_ANGLE", "-10"));
              
        this.addSequential(new DriveStraightForward(-1, distance));
        this.addSequential(new Turn(angle));
        this.addParallel(new SafeShoot(3));
    }
    
    public void initialize()
    {
        super.initialize();
        
        
        shoot.iterations = 3;
    }
    
    
}
