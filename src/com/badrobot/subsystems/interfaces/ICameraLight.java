/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems.interfaces;

/**
 * The Ring Light that is around the camera used for lighting up targets
 * @author Jon Buckley
 */
public interface ICameraLight
{
    /**
     * Turns on the ring light around the camera
     */
    public void turnOn();
    
    /**
     * Turns off the ring light around the camera
     */
    public void turnOff();
    
}
