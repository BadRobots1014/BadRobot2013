/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.BadPreferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Isaac
 */
public class ZeroShooterArticulator extends BadCommand{
    
    int range = 0;
    protected void initialize() {
        range = Integer.parseInt(BadPreferences.getValue("UP_VALUE", "0"));
        lastClick = (int) shooterArticulator.getAngle() - 1;
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln) {
    }

    protected void addNetworkTableValues(ITable table) {
    }

    public String getConsoleIdentity() {
        return "ZeroShooterArticualtor";
    }

    int lastClick = 0;
    double lastTime = 0;
    double rate = 1; 
    
    protected void execute() {
        rate = (shooterArticulator.getAngle() - lastClick)
                /(Timer.getFPGATimestamp() - lastTime);
        
        if (rate <= 0)
        {
            shooterArticulator.lockShooterArticulator();
            shooterArticulator.zero();
        }
        else 
        {
            shooterArticulator.raiseShooter(-.2);
            
            lastClick = (int)shooterArticulator.getAngle();
            lastTime = Timer.getFPGATimestamp();
        }
    }

    protected boolean isFinished() {
        return (rate <= 0);
    }

    protected void end() {
        shooterArticulator.lockShooterArticulator();
    }

    protected void interrupted() {
    }

    public void registerPreferencesValues() {
    }
    
}
