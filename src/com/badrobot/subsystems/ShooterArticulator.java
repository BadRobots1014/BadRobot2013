/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems;

import com.badrobot.BadRobotMap;
import com.badrobot.OI;
import com.badrobot.commands.ArticulateShooter;
import com.badrobot.subsystems.interfaces.IShooterArticulator;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GearTooth;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * 
 * @author Isaac
 */
public class ShooterArticulator extends BadSubsystem implements IShooterArticulator
{
    SpeedController shooterArticulatorSpeedController;
    
    GearTooth geartooth;
    
    public static ShooterArticulator instance;
    int goingUp = 0;
    
    private ShooterArticulator()
    {
        if (!BadRobotMap.isPrototype)
            shooterArticulatorSpeedController = new Talon(BadRobotMap.shooterArticulator);
        else 
            shooterArticulatorSpeedController = new Victor(BadRobotMap.shooterArticulator);
    }
    
    public static ShooterArticulator getInstance()
    {
        if (instance == null)
        {
            instance = new ShooterArticulator();
        }
        return instance;
    }
    
    DigitalInput input;

    protected void initialize() 
    {
        /*input = new DigitalInput(BadRobotMap.shooterArticulatorOpticalSensor);
        geartooth = new GearTooth(input);
        geartooth.enableDirectionSensing(true);
        geartooth.start();*/
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln) {
    }

    protected void addNetworkTableValues(ITable table) {
    }

    public String getConsoleIdentity() 
    {
        return "ShooterArticulator";
    }

    protected void initDefaultCommand() 
    {
        this.setDefaultCommand(new ArticulateShooter());
    }

    public void raiseShooter() 
    {
        raiseShooter(.3);
    }

    public void lowerShooter() 
    {
        raiseShooter(-1.0);
    }

    public void lockShooterArticulator() 
    {
        shooterArticulatorSpeedController.set(0.0);
    }

    public void registerPreferencesValues()
    {
    }

    public void raiseShooter(double speed)
    {
        if (speed < 0)
            goingUp = 1;
        else
            goingUp = -1;
        shooterArticulatorSpeedController.set(speed);
    }
    
    int lastGeartoothValue = 0;
    int position = 0;
    
    public double getAngle() 
    {
        /*position += (geartooth.get() - lastGeartoothValue) * goingUp;
        if (position < -1)
            position = -1;
        
        lastGeartoothValue = geartooth.get();
        return (position);*/
        
        return 0;
    }
}
