/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems;

import com.badrobot.BadPreferences;
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
public class ShooterArticulator extends BadSubsystem implements IShooterArticulator {

    SpeedController shooterArticulatorSpeedController;
    public GearTooth geartooth;
    public static ShooterArticulator instance;
    int goingUp = 0;

    private ShooterArticulator() {
        if (!BadRobotMap.isPrototype) {
            shooterArticulatorSpeedController = new Talon(BadRobotMap.shooterArticulator);
        } else {
            shooterArticulatorSpeedController = new Victor(BadRobotMap.shooterArticulator);
        }
    }

    public static ShooterArticulator getInstance() {
        if (instance == null) {
            instance = new ShooterArticulator();
        }
        return instance;
    }
    DigitalInput input;
    public boolean ENCODED = false;

    protected void initialize() {
        if (ENCODED) {
            input = new DigitalInput(BadRobotMap.shooterArticulatorEncoder);
            geartooth = new GearTooth(input);
            geartooth.start();
        }
        
        UP_VALUE = Integer.parseInt(BadPreferences.getValue("UP_VALUE", "0"));
        DOWN_VALUE = Integer.parseInt(BadPreferences.getValue("DOWN_VALUE", "0"));
        MIDDLE_VALUE = Integer.parseInt(BadPreferences.getValue("MIDDLE_VALUE", "0"));
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln) {
    }

    protected void addNetworkTableValues(ITable table) {
    }

    public String getConsoleIdentity() {
        return "ShooterArticulator";
    }

    protected void initDefaultCommand() {
        this.setDefaultCommand(new ArticulateShooter());
    }

    public void raiseShooter() {
        raiseShooter(.3);
    }

    public void lowerShooter() {
        raiseShooter(-1.0);
    }

    public void lockShooterArticulator() {
        shooterArticulatorSpeedController.set(0.0);
    }

    public void registerPreferencesValues() {
    }

    public void raiseShooter(double speed) {
        if (speed < 0) {
            goingUp = 1;
        } else {
            goingUp = -1;
        }
        shooterArticulatorSpeedController.set(speed);
    }
    int lastGeartoothValue = 0;
    int position = 0;

    public double getAngle() {
        if (geartooth != null) {
            return geartooth.get();
        }

        return -1;
    }

    public void zero() {
        if (geartooth != null) {
            geartooth.reset();
        }
    }
    static int UP_VALUE = 0,
            DOWN_VALUE = 0,
            MIDDLE_VALUE = 0;

    public void raiseToPosition(int pos) {
        if (ENCODED) {
            int position = geartooth.get();
            double delta = 0;

            switch (pos) {
                case IShooterArticulator.kDown:
                    delta = position - DOWN_VALUE;
                    break;

                case IShooterArticulator.kMiddle:
                    delta = position - MIDDLE_VALUE;
                    break;

                case IShooterArticulator.kUp:
                    delta = position - UP_VALUE;
                    break;
            }

            if (delta > 5) {
                raiseShooter(.5);
            } else if (delta < -5) {
                raiseShooter(.5);
            } else {
                lockShooterArticulator();
            }
        }
    }
}
