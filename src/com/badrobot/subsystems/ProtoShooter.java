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

    private static double MAX_SHOOTER_RPM = 600;
    
    Relay shooterRelay,
            secondaryShooterRelay;
    
    SpeedController shooterController,
            shooterArticulatorSpeedController;
    
    //boolean shooterArticulatorRelayIsForward = true;
    
    public static ProtoShooter getInstance()
    {
        if (instance == null)
            instance = new ProtoShooter();
        
        return instance;
    }
    
    private ProtoShooter()
    {
        shooterRelay = new Relay(BadRobotMap.primaryShooterRelay);
        shooterRelay.setDirection(Relay.Direction.kForward);
        
        secondaryShooterRelay = new Relay(BadRobotMap.secondaryShooterRelay);
        secondaryShooterRelay.setDirection(Relay.Direction.kForward);
        
        //shooterArticulatorRelay = new Relay(BadRobotMap.shooterArticulator);
        //shooterArticulatorRelay.setDirection(Relay.Direction.kForward);
        
        shooterArticulatorSpeedController = new Talon(BadRobotMap.shooterArticulator);
        
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
        
        initialize();
    }
    
    public void initDefaultCommand()
    {
        setDefaultCommand(new TriggerToShoot());
    }

    protected void initialize()
    {
        //controller.set(0.0);
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
        //final rig code (2 relays)
        if (speed != 0)
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
        
    public double getShooterSpeed() 
    {
        return -1;
    }

    public void raiseShooter()
    {
        shooterArticulatorSpeedController.set(1.0);
        
        /*
        if (!shooterArticulatorRelayIsForward)
        {
            shooterArticulatorRelay.setDirection(Relay.Direction.kForward);
            shooterArticulatorRelayIsForward = true;
        }    
        shooterArticulatorRelay.set(Relay.Value.kOn);*/
    }

    public void lowerShooter()
    {
        shooterArticulatorSpeedController.set(-1.0);
        /*
        if (shooterArticulatorRelayIsForward)
        {
            shooterArticulatorRelay.setDirection(Relay.Direction.kReverse);
            shooterArticulatorRelayIsForward = false;
        }    
        shooterArticulatorRelay.set(Relay.Value.kOn);*/
    }
    
    public void lockShooterArticulator()
    {
        shooterArticulatorSpeedController.set(0.0);
        //shooterArticulatorRelay.set(Relay.Value.kOff);
    }
    
}
