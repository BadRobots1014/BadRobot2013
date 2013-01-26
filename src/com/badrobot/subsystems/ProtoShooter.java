/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems;

import com.badrobot.BadRobotMap;
import com.badrobot.subsystems.interfaces.IShooter;
import edu.wpi.first.wpilibj.GearTooth;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Jon Buckley
 */
public class ProtoShooter extends BadSubsystem implements IShooter
{
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    SpeedController controller;
    private static ProtoShooter instance;
    
    EasyPID pid;
    GearTooth geartooth;
    
    public static ProtoShooter getInstance()
    {
        if (instance == null)
            instance = new ProtoShooter();
        
        return instance;
    }
    
    private ProtoShooter()
    {
        controller = new Victor(BadRobotMap.shooterSpeedController);
        geartooth = new GearTooth(BadRobotMap.opticalShooterSensor);
        pid = new EasyPID(0, 0, 0, "Shooter Fly Wheel", new PIDSource()
        {
            public double pidGet()
            {
                return geartooth.getPeriod();
            }
        });
    }
    
    public void initDefaultCommand()
    {
        
    }

    protected void initialize()
    {
        controller.set(0.0);
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln)
    {
        //TODO
    }

    protected void addNetworkTableValues(ITable table)
    {
        //TODO
    }
    
    public String getConsoleIdentity()
    {
        return "ProtoShooter";
    }

    public void runShooter(double speed)
    { 
        controller.set(speed);
    }

    public void setAngle(double angle)
    {
        //later
    }

    public void setAngle(int state)
    {
        //later
    }
}
