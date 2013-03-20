/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems;

import com.badrobot.BadRobotMap;
import com.badrobot.commands.ControlLighting;
import com.badrobot.subsystems.interfaces.ILights;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Isaac
 */
public class DecorativeLights extends BadSubsystem implements ILights
{
    DigitalOutput redChannel;
    DigitalOutput greenChannel;
    DigitalOutput blueChannel;

    private static DecorativeLights instance;
    
    private int currentColor = 0;
    
    private DecorativeLights()
    {
        redChannel = new DigitalOutput(BadRobotMap.redChannel);
        greenChannel = new DigitalOutput(BadRobotMap.greenChannel);
        blueChannel = new DigitalOutput(BadRobotMap.blueChannel);
        
        redChannel.enablePWM(0);
        greenChannel.enablePWM(0);
        blueChannel.enablePWM(0);
    }
    
    protected void initialize() {
    }

    public static DecorativeLights getInstance()
    {
        if (instance == null)
        {
            instance = new DecorativeLights();
        }
        return instance;
    }
    
    public void valueChanged(ITable itable, String key, Object value, boolean bln) {
    }

    protected void addNetworkTableValues(ITable table) {
    }

    public String getConsoleIdentity() 
    {
        return "DecorativeLights";
    }

    protected void initDefaultCommand() 
    {
        //this.setDefaultCommand(new ControlLighting());
    }
    
    public static double byteToPWM(int color)
    {
        double bytePercent = (double) ((double) color/255);
        double pwmValue = (bytePercent*1);
        return pwmValue;
    }

    public void turnOn() 
    {
        setColor(ILights.kYellow);
    }

    public void turnOff() 
    {
        redChannel.updateDutyCycle(0);
        greenChannel.updateDutyCycle(0);
        blueChannel.updateDutyCycle(0);
    }

    /**
     * Sets the color of the lights on the robot.
     * @param r red value
     * @param g green value
     * @param b blue value
     */
    public void setColor(int r, int g, int b) 
    {
        redChannel.updateDutyCycle(byteToPWM(Math.abs(r)));
        greenChannel.updateDutyCycle(byteToPWM(Math.abs(g)));
        blueChannel.updateDutyCycle(byteToPWM(Math.abs(b)));
    }

    public void setColor(int color) {        
        switch (color)
        {
            case ILights.kBlue:
                setColor(0, 0, 255);
                break;
            
            case ILights.kRed:
                setColor(255, 0, 0);
                break;
            
            case ILights.kYellow:
                setColor(255, 50, 0);
                break;
            
            case ILights.kWhite:
                setColor(200, 50, 30);
                break;
                
            case ILights.kETech:
                setColor(255, 0, 30);
                break;
                
            case ILights.kGold:
                setColor(200, 30, 10);
                break;
                
            case ILights.kGreen:
                setColor(0, 255, 0);
                break;
        }
        
        currentColor = color;
    }

    public void registerPreferencesValues()
    {
    }

    public int getColor()
    {
        return currentColor;
    }
}
