/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.utils.DetectedPoint;
import com.badrobot.utils.TrackingCriteria;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * On average one pass takes up around 200ms
 * definitely going to need to run this on either
 * the driver station, or on a Pi.
 * 
 * @author ajtgarber
 */
public class DefaultTrackingCommand extends BadCommand {
    
    private boolean completed = false;
    
    public DefaultTrackingCommand() {
        requires(imageTrackingSystem);
    }

    public String getConsoleIdentity() 
    {
        return "DefaultTrackingCommand";
    }

    protected void initialize() 
    {
        setInterruptible(false);
    }

    protected void execute() 
    {
        /*
         * For later tests we should look at CPU usage...
         * and definitely check for memory leaks
         */
        long startTime = System.currentTimeMillis();
        double aspectRatio = 3.0/2.0;
        TrackingCriteria criteria = new TrackingCriteria(aspectRatio, .5, 1992, .5, 200, 250,
                                        40, 80, 60, 80);
        DetectedPoint[] detectedPoints = imageTrackingSystem.getTargetCoordinates(criteria);
        if(detectedPoints != null) {
            for(int i = 0; i < detectedPoints.length; i++) {
                DetectedPoint point = detectedPoints[i];
                log("I has a point! ("+point.getX()+", "+point.getY()+")");
            }
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime-startTime;
        log("Completed my work in "+duration+" milliseconds :3");
        completed = true;
    }

    protected boolean isFinished()
    {
        return false;
    }

    protected void end() 
    {
    
    }

    protected void interrupted() 
    {
        log("Shouldn't have been interrupted... I had imporant work to do :c");
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln) 
    {
        
    }

    protected void addNetworkTableValues(ITable table) 
    {
        
    }
    
}
