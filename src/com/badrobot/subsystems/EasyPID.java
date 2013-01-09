/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * @author Jon Buckley
 */
public class EasyPID
{
    protected PIDSource source;
    protected PIDController controller;
    protected SoftPID output;
    protected String name;
    
    protected static double 
            P = 0.0,
            I = 0.0, 
            D = 0.0;
    /*
     * Initializes a simplified PID object. This should be used to easily
     * implement a PID controlled value.
     */
    public EasyPID(String name, PIDSource s)
    {
        this(0.0, 0.0, 0.0, name, s);
    }
    
    public EasyPID(PIDSource s)
    {
        this("EasyPID", s);
    }
    
    public EasyPID(double p, double i, double d, String name, PIDSource s)
    {
        this.name = name;
        
        source = s;
        output = new SoftPID();
        
        P = p;
        I = i;
        D = d;
        
        controller = new PIDController(P, I, D, source, output);
        SmartDashboard.putData(name, controller);
    }
    
    /*
     * sets the target setpoint for the hardware to achieve. 
     * @param setpoint the target value
     */
    public void setSetpoint(double setpoint)
    {
        controller.setSetpoint(setpoint);
    }
    
    /*
     * @return the value that should be set to achieve the setpoint at maximum
     * efficiency
     */
    public double getValue()
    {
        return output.getValue();
    }
    
    /*
     * updates the PID tuning values of the controller
     */
    public void setPIDValues(double p, double i, double d)
    {
        P = p;
        I = i; 
        D = d;
        
        controller.setPID(p, i, d);
    }
}
