/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems;

import com.badrobot.BadRobotMap;
import com.badrobot.OI;
import com.badrobot.commands.ArticulateWithController;
import com.badrobot.subsystems.interfaces.IShooterArticulator;
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

    protected void initialize() 
    {
        
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
        this.setDefaultCommand(new ArticulateWithController());
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
}
