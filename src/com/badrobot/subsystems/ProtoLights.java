/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems;

import com.badrobot.BadRobotMap;
import com.badrobot.commands.ControlLighting;
import com.badrobot.subsystems.interfaces.ILights;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Isaac
 */
public class ProtoLights extends BadSubsystem implements ILights
{
    DigitalOutput redChannel;
    DigitalOutput greenChannel;
    DigitalOutput blueChannel;

    protected void initialize() 
    {
        redChannel = new DigitalOutput(BadRobotMap.redChannel);
        greenChannel = new DigitalOutput(BadRobotMap.greenChannel);
        blueChannel = new DigitalOutput(BadRobotMap.blueChannel);
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln) {
    }

    protected void addNetworkTableValues(ITable table) {
    }

    public String getConsoleIdentity() 
    {
        return "ProtoLights";
    }

    protected void initDefaultCommand() 
    {
        this.setDefaultCommand(new ControlLighting());
    }
    
    public static double byteToPWM(int color)
    {
        double bytePercent = color/255;
        double pwmValue = (bytePercent*(19000-.6));
        return pwmValue+.6;
    }

    public void turnOn() 
    {
        redChannel.enablePWM(byteToPWM(255));
        greenChannel.enablePWM(byteToPWM(0));
        blueChannel.enablePWM(byteToPWM(0));
    }

    public void turnOff() 
    {
        redChannel.disablePWM();
        greenChannel.disablePWM();
        blueChannel.disablePWM();
    }

    /**
     * Sets the color of the lights on the robot.
     * @param r red value
     * @param g green value
     * @param b blue value
     */
    public void setColor(int r, int g, int b) 
    {
        redChannel.setPWMRate(byteToPWM(r));
        greenChannel.setPWMRate(byteToPWM(g));
        blueChannel.setPWMRate(byteToPWM(b));
    }
}
