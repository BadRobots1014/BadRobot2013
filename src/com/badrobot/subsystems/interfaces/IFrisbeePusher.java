/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems.interfaces;

/**
 *
 * @author Jon Buckley
 */
public interface IFrisbeePusher extends Logger
{
        
    /**
     * instructs the window lift motor to run forward or back, driving the 
     * frisbee into the motors, or retracting to allow a frisbee to drop down
     * @param forward should be forward to push frisbee into shooter, backwards
     * to allow frisbee to fall through
     */
    public void pushFrisbee(boolean forward);
    
    /**
     * Stops the frisbee pusher from going forward or retracting
     */
    public void stopFrisbeePusher();
    
    /**
     * This method should return whether the frisbee pusher is at its one 
     * revolution point. This is when the pusher should NOT be pushed/withdrawn
     * any further
     * @return whether the pusher is at its zero position
     */
    public boolean isFrisbeeRetracted();
}
