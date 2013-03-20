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
    
    private ShooterArticulator()
    {
        shooterArticulatorSpeedController = new Talon(BadRobotMap.shooterArticulator);
    }
    
    public static ShooterArticulator getInstance()
    {
        if (instance == null)
        {
            instance = new ShooterArticulator();
        }
        return instance;
    }
    
    DigitalInput digiputi;

    protected void initialize() 
    {
        digiputi = new DigitalInput(BadRobotMap.shooterArticulatorOpticalSensor);
        geartooth = new GearTooth(digiputi);
        geartooth.start();
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
        shooterArticulatorSpeedController.set(.3);
    }

    public void lowerShooter() 
    {
        shooterArticulatorSpeedController.set(-1.0);
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
        shooterArticulatorSpeedController.set(speed);
    }

    public void lowerShooter(double speed)
    {
        shooterArticulatorSpeedController.set(speed);
    }

    public double getAngle() 
    {
        log("Digital Input: " + digiputi.get());
        return (geartooth.get() + 1.0);
    }
}
