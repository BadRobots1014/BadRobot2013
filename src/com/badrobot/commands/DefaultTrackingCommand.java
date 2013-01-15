/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.utils.DetectedPoint;
import com.badrobot.utils.TrackingCriteria;

/**
 * Test me!
 * @author ajtgarber
 */
public class DefaultTrackingCommand extends BadCommand {
    
    private boolean completed = false;

    public String getConsoleIdentity() 
    {
        return "DefaultTrackingCommand";
    }

    protected void initialize() 
    {
        requires(imageTrackingSystem);
        setInterruptible(false);
    }

    protected void execute() 
    {
        /*
         * For later tests we should look at CPU usage...
         * and definitely check for memory leaks
         */
        long startTime = System.currentTimeMillis();
        double aspectRatio = 31.5/63.25;
        TrackingCriteria criteria = new TrackingCriteria(aspectRatio, .05, 1992, .05, 100, 156,
                                        30, 255, 145, 255);
        DetectedPoint[] detectedPoints = imageTrackingSystem.getTargetCoordinates(criteria);
        for(int i = 0; i < detectedPoints.length; i++) {
            DetectedPoint point = detectedPoints[i];
            log("I has a point! ("+point.getX()+", "+point.getY()+")");
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime-startTime;
        log("completed my work in "+duration+" milliseconds :3");
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
        log("Shouldn't have been interrupted... I had imporant work to do :c");
    }
    
}
