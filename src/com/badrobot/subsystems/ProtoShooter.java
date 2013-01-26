/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems;

import com.badrobot.BadRobotMap;
import com.badrobot.commands.TestShooter;
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
    
    Relay frisbeePusher;
    public static final double FRISBEE_PUSH_TIME = .5;
    
    private static double MAX_SHOOTER_RPM = 600;
    
    public static ProtoShooter getInstance()
    {
        if (instance == null)
            instance = new ProtoShooter();
        
        return instance;
    }
    
    private ProtoShooter()
    {
        controller = new Victor(BadRobotMap.shooterSpeedController);
        DigitalInput input = new DigitalInput(BadRobotMap.opticalShooterSensor);
        geartooth = new GearTooth(input);
        pid = new EasyPID(0, 0, 0, "Shooter Fly Wheel", new PIDSource()
        {
            public double pidGet()
            {
                //convert from Seconds/Revolutions to Revolutions/Minute
                System.out.println("rpm " + (1/(60 * geartooth.getPeriod())));
                return (1/(60 * geartooth.getPeriod()));
            }
        });
        geartooth.start();
        
        frisbeePusher.setDirection(Relay.Direction.kBoth);
        frisbeePusher.set(Relay.Value.kOff);
    }
    
    public void initDefaultCommand()
    {
        setDefaultCommand(new TestShooter());
    }

    protected void initialize()
    {
        controller.set(0.0);
        geartooth.reset();
        geartooth.setMaxPeriod(2);
        geartooth.start();
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
        //SmartDashboard.putBoolean("sensor", sensor.get());
        SmartDashboard.putNumber("period", geartooth.getPeriod());
        SmartDashboard.putNumber("count", geartooth.get());
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
    
    public void pushFrisbee(boolean push)
    {
        frisbeePusher.set(Relay.Value.kOn);
        double startTime;
        Timer timer = new Timer();
        if(push == true)
        {
            startTime = timer.getFPGATimestamp();
            timer.start();
            frisbeePusher.set(Relay.Value.kForward);
            if(timer.get() == (startTime + FRISBEE_PUSH_TIME))
            {
                timer.stop();
                frisbeePusher.set(Relay.Value.kOff);
            }
        }
        else
        {
            frisbeePusher.set(Relay.Value.kReverse);
        }
    }
    
    
}