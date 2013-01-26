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
    public static final int RAISED_TO_THREE_POINT = 0;
    public static final int RAISED_TO_TWO_POINT = 1;
    public static final int RAISED_TO_ONE_POINT = 2;
    
    public static int STATE = 2;
    /**
     * runs the shooter at the designated speed
     * @param speed the speed at which to run the motor, from -1 to 1
     */
    public void runShooter(double speed);
    
    /**
     * adjusts the shooter to angle between 0 and 90
     * @param angle the angle to raise to (0 degrees is parallel to ground)
     */
    public void setAngle(double angle);
    
    /**
     * Sets the angle to a preset height, using state information.
     * @param state the preset height to raise/lower to 
     */
    public void setAngle(int state);
    
    public void pidRunShooter(double power);
}
