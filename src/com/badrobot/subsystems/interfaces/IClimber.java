/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems.interfaces;

/**
 * The Subsystem responsible for climbing the pyramid
 * @author Jon Buckley
 */
public interface IClimber
{
    public static int kDown = 0, 
            kUp = 1;
    
    /**
     * Raise the climber arms.
     */
    public void raiseClimber();
    
    /**
     * Lower the climber arms.
     */
    public void lowerClimber();
    
    /**
     * Locks the climber at its current position (stops it from going any further)
     */
    public void lockClimber();
    
    /**
     * Sets the position using an encoder to a certain position
     * @param pos the enumerable position (saved in ILights)
     */
    public void setPosition(int pos);
    
    public void zeroPosition();
}
