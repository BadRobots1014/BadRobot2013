/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems.interfaces;

/**
 *
 * @author Jon Buckley
 */
public interface IHUD extends Logger
{
    /**
     * Turns on or off an LED on the HUD system
     * @param index the index of the LED to change
     * @param state the state at which to set the LED
     */
    public void setLED(int index, boolean state);
}
