/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.utils.DetectedPoint;
import com.badrobot.utils.TrackingCriteria;

/**
 * Emphasis on bad...
 * @author ajtgarber
 */
public class BadDefaultTracker extends BadCommand {
    
    private boolean completed = false;

    public String getConsoleIdentity() 
    {
        return "BadDefaultTracker";
    }

    protected void initialize() 
    {
        requires(imageTrackingSystem);
    }

    protected void execute() 
    {
        /*
         * For later tests we should look at CPU usage...
         * and definitely check for memory leaks
         */
        double aspectRatio = 31.5/63.25;
        TrackingCriteria criteria = new TrackingCriteria(aspectRatio, .05, 1992, .05, 100, 156,
                                        30, 255, 145, 255);
        DetectedPoint[] detectedPoints = imageTrackingSystem.getTargetCoordinates(criteria);
        for(int i = 0; i < detectedPoints.length; i++) {
            DetectedPoint point = detectedPoints[i];
            log("I has a point! ("+point.getX()+", "+point.getY()+")");
        }
        completed = true;
    }

    protected boolean isFinished()
    {
        return completed;
    }

    protected void end() 
    {
    
    }

    protected void interrupted() 
    {
        
    }
    
}
