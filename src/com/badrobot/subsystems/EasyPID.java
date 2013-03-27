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
 * This class can be used to quickly make a control PID enabled. It constructs 
 * a PIDController and SoftPID object and ties them to the sensor defined in the
 * constructor. 
 * 
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
            D = 0.0,
            F = 0.0;
    /*
     * Initializes a simplified PID object. This should be used to easily
     * implement a PID controlled value.
     */
    public EasyPID(String name, PIDSource s)
    {
        this(0.0, 0.0, 0.0, 0.0, name, s);
    }
    
    /**
     * Constructs an EasyPID object with the given source
     * @param s the source that should be used for the PIDController input
     */
    public EasyPID(PIDSource s)
    {
        this("EasyPID", s);
    }
    
    /**
     * Constructs an EasyPID object with the given parameters
     * @param p the constant P value
     * @param i the constant I value
     * @param d the constant D value
     * @param f the constant F value
     * @param name the name to be given to the EasyPID object for SmartDashboard
     * @param s the source to be used for input in the PIDController object
     */
    public EasyPID(double p, double i, double d, double f, String name, PIDSource s)
    {
        this.name = name;
        System.out.println("constucting PIDEasy object");
        source = s;
        output = new SoftPID();
        
        P = p;
        I = i;
        D = d;
        F = f;
        
        controller = new PIDController(P, I, D, F, source, output);
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
