/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems;

import com.badrobot.subsystems.interfaces.IHUD;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO.EnhancedIOException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Jon Buckley
 */
public class HUD extends BadSubsystem implements IHUD
{
    DriverStationEnhancedIO io;
    
    private static HUD instance;
    public static HUD getInstance()
    {
        if (instance == null)
        {
            instance = new HUD();
        }
        
        return instance;
    }
    
    private HUD()
    {
        io = DriverStation.getInstance().getEnhancedIO();
    }
    
    public void initDefaultCommand()
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
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
        return "HUD";
    }

    public void setLED(int index, boolean state)
    {
        try
        {
            io.setLED(index, state);
        }
        catch (EnhancedIOException ex)
        {
            ex.printStackTrace();
        }
    }
}
