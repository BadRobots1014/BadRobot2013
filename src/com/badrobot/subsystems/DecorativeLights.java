/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems;

import com.badrobot.BadRobotMap;
import com.badrobot.commands.ControlLighting;
import com.badrobot.subsystems.interfaces.ILights;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
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
    
    private DecorativeLights()
    {
        redChannel = new DigitalOutput(BadRobotMap.redChannel);
        greenChannel = new DigitalOutput(BadRobotMap.greenChannel);
        blueChannel = new DigitalOutput(BadRobotMap.blueChannel);
        
        LiveWindow.addActuator("ProtoLights", "red", redChannel);
        LiveWindow.addActuator("ProtoLights", "green", greenChannel);
        LiveWindow.addActuator("ProtoLights", "blue", blueChannel);
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
        return "ProtoLights";
    }

    protected void initDefaultCommand() 
    {
        this.setDefaultCommand(new ControlLighting());
    }
    
    public static double byteToPWM(int color)
    {
        double bytePercent = (double) ((double) color/255);
        double pwmValue = (bytePercent*1);
        return pwmValue;
    }

    public void turnOn() 
    {
        /*redChannel.set(false);
        blueChannel.set(false);
        greenChannel.set(false);*/
        
        redChannel.enablePWM(byteToPWM(0));
        greenChannel.enablePWM(byteToPWM(0));
        blueChannel.enablePWM(byteToPWM(0));
    }

    public void turnOff() 
    {
        //redChannel.disablePWM();
        //greenChannel.disablePWM();
        //blueChannel.disablePWM();
    }

    /**
     * Sets the color of the lights on the robot.
     * @param r red value
     * @param g green value
     * @param b blue value
     */
    public void setColor(int r, int g, int b) 
    {
        log("Byte to PWM of Math.abs(g)"+byteToPWM(Math.abs(g)));
        log("Byte to PWM of Math.abs(r)"+byteToPWM(Math.abs(r)));
        log("Byte to PWM of Math.abs(b)"+byteToPWM(Math.abs(b)));
        
        
        //redChannel.pulse()
        
        redChannel.updateDutyCycle(byteToPWM(Math.abs(r)));
        greenChannel.updateDutyCycle(byteToPWM(Math.abs(g)));
        blueChannel.updateDutyCycle(byteToPWM(Math.abs(b)));
        
        
        /*redChannel.setPWMRate(byteToPWM(Math.abs(r)));
        greenChannel.setPWMRate(byteToPWM(Math.abs(g)));
        blueChannel.setPWMRate(byteToPWM(Math.abs(b)));*/
        
        /*redChannel.set((r > 0));
        blueChannel.set((b > 0));
        greenChannel.set((g > 0));*/
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
                
            case ILights.kETech:
                setColor(255, 0, 30);
                
            case ILights.kGold:
                setColor(200, 30, 10);
        }
    }

    public void registerPreferencesValues()
    {
    }
}
