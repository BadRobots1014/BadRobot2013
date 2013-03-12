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
}
