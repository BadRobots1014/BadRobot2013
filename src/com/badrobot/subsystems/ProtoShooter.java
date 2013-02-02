/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems;

import com.badrobot.BadRobotMap;
import com.badrobot.commands.TestShooter;
import com.badrobot.commands.TriggerToShoot;
import com.badrobot.subsystems.interfaces.IShooter;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    //public static final double FRISBEE_PUSH_TIME = .5;
    private static double MAX_SHOOTER_RPM = 600;
    
    Relay shooterRelay,
            secondaryShooterRelay;
    
    public static ProtoShooter getInstance()
    {
        if (instance == null)
            instance = new ProtoShooter();
        
        return instance;
    }
    
    private ProtoShooter()
    {
        shooterRelay = new Relay(BadRobotMap.primaryShooterRelay);
        secondaryShooterRelay = new Relay(BadRobotMap.secondaryShooterRelay);
        //controller = new Victor(BadRobotMap.shooterSpeedController);
        DigitalInput input = new DigitalInput(BadRobotMap.opticalShooterSensor);
        geartooth = new GearTooth(input);
        /*pid = new EasyPID(0, 0, 0, "Shooter Fly Wheel", new PIDSource()
        {
            public double pidGet()
            {
                //convert from Seconds/Revolutions to Revolutions/Minute
                System.out.println("rpm " + (60/(geartooth.getPeriod())));
                return (60/(geartooth.getPeriod()));
            }
        });*/
        geartooth.start();
    }
    
    public void initDefaultCommand()
    {
        setDefaultCommand(new TriggerToShoot());
    }

    protected void initialize()
    {
        controller.set(0.0);
        geartooth.reset();
        geartooth.setMaxPeriod(2);
        geartooth.start();
        
        shooterRelay.setDirection(Relay.Direction.kForward);
        secondaryShooterRelay.setDirection(Relay.Direction.kForward);
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
        if (speed > 0)
        {  
            shooterRelay.set(Relay.Value.kOn);
            secondaryShooterRelay.set(Relay.Value.kOn); 
        }
        
        else 
        {
            shooterRelay.set(Relay.Value.kOff);
            secondaryShooterRelay.set(Relay.Value.kOff);
        }
        //SmartDashboard.putBoolean("sensor", sensor.get());
        SmartDashboard.putNumber("period", geartooth.getPeriod());
        SmartDashboard.putNumber("count", geartooth.get());
        //SmartDashboard.putNumber("rpm", pid.source.pidGet());
    }
    
    public void pidRunShooter(double power)
    {
        double setpoint = power*MAX_SHOOTER_RPM;
        pid.setSetpoint(setpoint);
        
        controller.set(pid.getValue());
        SmartDashboard.putNumber("period", geartooth.getPeriod());
        SmartDashboard.putNumber("count", geartooth.get());
        SmartDashboard.putNumber("rpm", pid.source.pidGet());
    }

    public void setAngle(double angle)
    {
        //later
    }

    public void setAngle(int state)
    {
        //later
    }
        
    public double getShooterSpeed() 
    {
        return -1;
    }
    
}
