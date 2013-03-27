/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems.interfaces;

/**
 *
 * @author Isaac
 */
public interface IShooterArticulator 
{
    public static int kUp = 9,
            kDown = 2, 
            kMiddle = 0;
     
    /**
     * Raises the shooter's angle.
     */
    public void raiseShooter();
    
    /**
     * Lowers the shooter's angle.
     */
    public void lowerShooter();
    
    /**
     * Locks the shooter articulator at the current position.
     */
    public void lockShooterArticulator();
    
    /**
     * Raises the shooter at any speed between -1 and 1
     * @param speed the rate to raise/lower the shooter [-1, 1]
     */
    public void raiseShooter(double speed);

    /**
     * Finds the angle of the shooter articulator
     * @return the angle the shooter is currently at
     */
    public double getAngle();
    
    /**
     * Zeroes the shooter articulator encoder--the position it currently is at
     * when this is called will from then on be considered the "down" position
     */
    public void zero();
    
    /**
     * Raises the shooter to a predefined position according to an enumerable of 
     * this interface
     * @param pos the position at which to set 
     */
    public void raiseToPosition(int pos);
}
