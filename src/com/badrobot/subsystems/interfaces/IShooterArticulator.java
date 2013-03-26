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
    
    public void raiseShooter(double speed);

    public double getAngle();
}
