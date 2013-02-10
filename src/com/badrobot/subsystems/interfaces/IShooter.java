/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems.interfaces;

/**
 *
 * @author Jon Buckley
 */
public interface IShooter
{
    /**
     * runs the shooter at the designated speed
     * @param speed the speed at which to run the motor, from -1 to 1
     */
    public void runShooter(double speed);
    
    /**
     * raises the shooter in angle
     */
    public void raiseShooter();
    
    /**
     * lowers the shooter's angle (becomes more parallel to the ground)
     */
    public void lowerShooter();
    
    
    /**
     * locks the position of the shooter articulation mechanism
     */
    public void lockShooterArticulator();
    
    /**
     * @return Returns the speed of the shooter wheel.
     */
    public double getShooterSpeed();
    
    /**
     * runs the shooter using PID control, should allow for faster reaching of
     * speeds
     * @param power the decimal percentage of the max rpm to set the shooter at  
     */
    public void pidRunShooter(double power);
}
