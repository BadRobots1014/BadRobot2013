/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.utils;

/**
 *
 * @author adrian
 */
public class TrackingCriteria 
{
    private int aspectRatio;  //width/height
    private double aspectTolerance; //Tolerated percent error
    private double particleArea;
    private double areaTolerance;
    
    public TrackingCriteria(int aspectRatio, double tolerance, double particleArea, double areaTolerance) 
    {
        this.aspectRatio = aspectRatio;
        this.aspectTolerance = tolerance;
        this.particleArea = particleArea;
        this.areaTolerance = areaTolerance;
    }
    
    public int getAspectRatio() 
    {
        return aspectRatio;
    }
    public double getAspectTolerance() 
    {
        return aspectTolerance;
    }
    public double getParticleArea() 
    {
        return particleArea;
    }
    public double getAreaTolerance() 
    {
        return areaTolerance;
    }
}
