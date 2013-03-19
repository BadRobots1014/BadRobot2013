/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems;

import com.badrobot.BadPreferences;
import com.badrobot.BadRobotMap;
import com.badrobot.commands.ArticulateClimber;
import com.badrobot.subsystems.interfaces.IClimber;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * Subsystem for the climbing mechanism.
 * @author Isaac
 */
public class Climber extends BadSubsystem implements IClimber
{
    public static Climber instance;
    
    public SpeedController climberController;
    static double CLIMBER_DOWN_ENCODER_VALUE, 
            CLIMBER_UP_ENCODER_VALUE;
    EasyPID controller;
    
    Encoder encoder;
    
    public static Climber getInstance()
    {
        if (instance == null)
        {
            instance = new Climber();
        }
        return instance;
    }
    
    private Climber()
    {
        climberController = new Jaguar(BadRobotMap.climberArticulator);
        log("BadRobotMap Encoder In: " + BadRobotMap.climberEncoderIn);
        encoder = new Encoder(BadRobotMap.climberEncoderIn, BadRobotMap.climberEncoderOut, true);
        encoder.start();
        
        //controller = new EasyPID(.01, 0.0, 0.0, 0.0, "Climber Controller", encoder);
        //controller.controller.enable();
        //controller.setAbsoluteTolerance(8);
        //controller.enable();
        
    }
    
    protected void initialize() 
    {
        CLIMBER_DOWN_ENCODER_VALUE = Double.parseDouble(BadPreferences.getValue("CLIMBER_DOWN_POSITION", "355.0"));
        CLIMBER_UP_ENCODER_VALUE = Double.parseDouble(BadPreferences.getValue("CLIMBER_UP_POSITION", "29.0"));
        RUN_SPEED = Double.parseDouble(BadPreferences.getValue("CLIMBER_ARTICULATION_SPEED", ".5"));
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
        setDefaultCommand(new ArticulateClimber());
    }

    public void registerPreferencesValues() 
    {
        
    }
    
    private static double RUN_SPEED = .5;
    public void raiseClimber() 
    {
        climberController.set(RUN_SPEED);
    }

    public void lowerClimber() 
    {
        climberController.set(-RUN_SPEED);
    }
    
    public void lockClimber()
    {
        //controller.disable();
        climberController.set(0.0);
        SmartDashboard.putNumber("Encoder Value", encoder.get());
        SmartDashboard.putNumber("Encoder Distance", encoder.getDistance());
    }

    public void setPosition(int pos)
    {
        //controller.enable();
        switch (pos)
        {
            case IClimber.kDown:
                /*controller.setSetpoint(CLIMBER_DOWN_ENCODER_VALUE);
                log(controller.getValue() + "  " + CLIMBER_DOWN_ENCODER_VALUE);
                climberController.set(controller.getValue());*/
                if (encoder.get() < CLIMBER_DOWN_ENCODER_VALUE)
                    climberController.set(-.8);
                else 
                    climberController.set(0.0);
                
                break;
            case IClimber.kUp:
                /*controller.setSetpoint(CLIMBER_DOWN_ENCODER_VALUE);
                log(controller.getValue() + "  ");
                climberController.set(controller.getValue());*/
                if (encoder.get() > CLIMBER_UP_ENCODER_VALUE)
                    climberController.set(.8);
                else 
                    climberController.set(0.0);
                break;
        }
    }
    
}
