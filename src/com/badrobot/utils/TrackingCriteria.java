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
    private double aspectRatio;  //width/height
    private double aspectTolerance; //Tolerated percent error
    private double particleArea;
    private double areaTolerance;
    
    private int hueLow;
    private int hueHigh;
    private int saturationLow;
    private int saturationHigh;
    private int valueLow;
    private int valueHigh;
    
    public TrackingCriteria(double aspectRatio, double tolerance, double particleArea, 
            double areaTolerance, int hueLow, int hueHigh, int saturationLow, 
            int saturationHigh, int valueLow, int valueHigh) 
    {
        this.aspectRatio = aspectRatio;
        this.aspectTolerance = tolerance;
        this.particleArea = particleArea;
        this.areaTolerance = areaTolerance;
        
        this.hueLow = hueLow;
        this.hueHigh = hueHigh;
        this.saturationLow = saturationLow;
        this.saturationHigh = saturationHigh;
        this.valueLow = valueLow;
        this.valueHigh = valueHigh;
    }
    
    public double getAspectRatio() 
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
    
    public int getMinimumHue() 
    {
        return hueLow;
    }
    public int getMaximumHue() 
    {
        return hueHigh;
    }
    public int getMinimumSaturation() 
    {
        return saturationLow;
    }
    public int getMaximumSaturation() 
    {
        return saturationHigh;
    }
    public int getMinimumValue() 
    {
        return valueLow;
    }
    public int getMaximumValue() 
    {
        return valueHigh;
    }
    
    public void setAspectRatio(double aspectRatio) 
    {
        this.aspectRatio = aspectRatio;
    }
    public void setAspectTolerance(double perror) 
    {
        aspectTolerance = perror;
    }
    public void setParticleArea(double particleArea) 
    {
        this.particleArea = particleArea;
    }
    public void setAreaTolerance(double perror) 
    {
        areaTolerance = perror;
    }
    
    public void setMinimumHue(int hue) 
    {
        hueLow = hue;
    }
    public void setMaximumHue(int hue) 
    {
        hueHigh = hue;
    }
    public void setMinimumSaturation(int hue)
    {
        saturationLow = hue;
    }
    public void setMaximumSaturation(int hue) 
    {
        saturationHigh = hue;
    }
    public void setMinimumValue(int value)
    {
        valueLow = value;
    }
    public void setMaximumValue(int value)
    {
        valueHigh = value;
    }
}
