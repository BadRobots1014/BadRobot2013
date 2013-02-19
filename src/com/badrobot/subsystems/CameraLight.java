/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems;

import com.badrobot.BadRobotMap;
import com.badrobot.commands.TurnOnCameraLight;
import com.badrobot.subsystems.interfaces.ICameraLight;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Jon Buckley
 */
public class CameraLight extends BadSubsystem implements ICameraLight
{
    private static CameraLight instance;
    private Relay lightRelay;
    
    private CameraLight()
    {
        lightRelay = new Relay(BadRobotMap.cameraLightRelay);
        lightRelay.setDirection(Relay.Direction.kForward);
    }
    
    public static CameraLight getInstance()
    {
        if (instance == null)
            instance = new CameraLight();
        
        return instance;
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand()
    {
        this.setDefaultCommand(new TurnOnCameraLight());
    }

    protected void initialize()
    {
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln)
    {
    }

    protected void addNetworkTableValues(ITable table)
    {
    }

    public String getConsoleIdentity()
    {
        return "Camera Light subsystem";
    }

    public void turnOn()
    {
        lightRelay.set(Relay.Value.kOn);
    }

    public void turnOff()
    {
        lightRelay.set(Relay.Value.kOff);
    }
}
