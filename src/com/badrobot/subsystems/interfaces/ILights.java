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
    //makeshift enumerable
    public static final int kBlue = 0,
                            kRed = 1,
                            kGreen = 6,
                            kYellow = 2,
                            kWhite = 3,
                            kETech = 4,
                            kGold = 5;
    
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
    
    /**
     * Sets the color to one of the predefined color, as enumerated in this class
     * @param color the enumerable key of the color
     */
    public void setColor(int color);
    
    /**
     * 
     * @return the enumerated color value as specified by the constants in this interface
     */
    public int getColor();
}
