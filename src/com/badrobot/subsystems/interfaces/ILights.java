/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems.interfaces;

/**
 *
 * @author Isaac
 */
public interface ILights extends Logger
{
    /**
     * Turns the lights on the robot on.
     */
    public void turnOn();
    
    /**
     * Turns the lights on the robot off.
     */
    public void turnOff();
    
    /**
     * Sets the color of the lights.
     * All parameters should be between 0 and 255.
     * @param r red
     * @param g green
     * @param b blue
     */
    public void setColor(int r, int g, int b);
}
