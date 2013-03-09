/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems;

import com.badrobot.BadRobotMap;
import com.badrobot.subsystems.interfaces.IClimber;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * Subsystem for the climbing mechanism.
 * @author Isaac
 */
public class Climber extends BadSubsystem implements IClimber
{
    public static Climber instance;
    
    SpeedController climberController;
    
    Encoder encoder;
    
    public static Climber getInstance()
    {
        if (instance == null)
        {
            instance = new Climber();
        }
        return instance;
    }
    
    protected void initialize() 
    {
        climberController = new Jaguar(BadRobotMap.climberArticulator);
        encoder = new Encoder(BadRobotMap.climberEncoderIn, BadRobotMap.climberEncoderOut, true);
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln) {
    }

    protected void addNetworkTableValues(ITable table) {
    }

    public String getConsoleIdentity() 
    {
        return "Climber";
    }

    protected void initDefaultCommand() {
    }

    public void registerPreferencesValues() {
    }
    
    public void raiseClimber() 
    {
        climberController.set(1);
    }

    public void lowerClimber() 
    {
        climberController.set(-1);
    }
    
}
