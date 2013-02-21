/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems;

import com.badrobot.BadRobotMap;
import com.badrobot.commands.SafeShoot;
import com.badrobot.commands.TriggerToShoot;
import com.badrobot.subsystems.interfaces.IShooter;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Noah Baskes
 */
public class Shooter extends BadSubsystem implements IShooter
{
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    SpeedController controller;
    private static Shooter instance;
    
    
    EasyPID pid;
    GearTooth geartooth;

    private static double MAX_SHOOTER_RPM = 600;
    
    SpeedController shooterController,
            secondaryShooterController;
    
    //boolean shooterArticulatorRelayIsForward = true;
    
    public static Shooter getInstance()
    {
        if (instance == null)
        {
            instance = new Shooter();
        }
        
        return instance;
    }
    
    private Shooter()
    {
        shooterController = new Talon(BadRobotMap.primaryShooterSpeedController);
        secondaryShooterController = new Talon(BadRobotMap.secondaryShooterSpeedController);
        
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
        setDefaultCommand(new SafeShoot());
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
        shooterController.set(speed);
        secondaryShooterController.set(speed);
                
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
        SmartDashboard.putNumber("Period", geartooth.getPeriod());
        SmartDashboard.putNumber("Count", geartooth.get());
        SmartDashboard.putNumber("RPM", pid.source.pidGet());
    }
        
    public double getShooterSpeed() 
    {
        //Converts from sec/rev to rev/min.
        return (60/geartooth.getPeriod());  
    }

    /**
     * Runs the shooter with the back wheel starting up with a delay before
     * the front wheel does.
     * @param speed the speed at which to run the motor, from -1 to 1.
     */
    public void runShooterDelayed(double speed) 
    {
        double start = Utility.getFPGATime();
        double delay = 0.5 * 1000000;
        
        secondaryShooterController.set(speed);
        
        if (Utility.getFPGATime() >= start+delay)
        {
            shooterController.set(speed);
        }
        
        SmartDashboard.putNumber("period", geartooth.getPeriod());
        SmartDashboard.putNumber("count", geartooth.get());
    }
}
