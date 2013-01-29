/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems.interfaces;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 *
 * @author Jon Buckley
 */
public interface IDriveTrain 
{
    /**
     * Drives the robot in tank drive--two sticks represent the left and right
     * sides of the robot; pushing forward on the left stick moves the left side
     * forward, pushing backwards on the right stick moves the right side of the
     * robot backwards.
     * 
     * @param left the left side joystick value (-1 to 1)
     * @param right the right joystick value (-1 to 1)
     */
    public void tankDrive(double left, double right);
    
    public Gyro getGyro();
    
    public RobotDrive getTrain();
    
    public double getDistanceToWall();
    
    /**
     * Drives the robot in arcade drive--one stick controls all movement;
     * pushing forward moves all wheels forward, pushing backwards moves
     * all wheels backwards, pushing left moves the
     * @param y the joysticks vertical value (-1 to 1)
     * @param x the joysticks horizontal value (-1 to 1)
     */
    public void arcadeDrive(double Y, double X);
}
