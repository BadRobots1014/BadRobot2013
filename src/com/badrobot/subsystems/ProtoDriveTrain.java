/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems;

import com.badrobot.BadRobotMap;
import com.badrobot.commands.DriveWithJoysticks;
import com.badrobot.subsystems.interfaces.IDriveTrain;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

/**
 * v1.1 Working drivetrain on prototype chassis
 * @author Jon Buckley
 */
public class ProtoDriveTrain extends BadSubsystem implements IDriveTrain
{
    SpeedController frontLeft, frontRight, backLeft, backRight;
    RobotDrive train;
    private static double MAX_POWER = .8;
    
    private static ProtoDriveTrain instance;
    
    /**
     * Singleton accessor. Only one instance of ProtoDriveTrain allowed in program
     * @return the single instance of ProtoDriveTrain
     */
    public static ProtoDriveTrain getInstance() 
    {
        if(instance == null)
        {
            instance = new ProtoDriveTrain();
        }
        return instance;
    }
    
    /**
     * private constructor so that instances can't be constructed publicly
     */
    private ProtoDriveTrain()
    {
        initialize();
        SmartDashboard.putData("ProtoDriveTrain", this);
    }
    
    /**
     * sets up all of the hardware components needed, references BadRobotMap
     * for the appropriate ports
     */
    protected void initialize()
    {
        frontLeft = new Victor(BadRobotMap.frontLeftSpeedController);
        frontRight = new Victor(BadRobotMap.frontRightSpeedController);
        backLeft = new Victor(BadRobotMap.backLeftSpeedController);
        backRight = new Victor(BadRobotMap.backRightSpeedController);
        
        train = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
                
        train.setInvertedMotor(MotorType.kRearLeft, true);
        train.setInvertedMotor(MotorType.kFrontLeft, true);
        train.setInvertedMotor(MotorType.kFrontRight, true);
        train.setInvertedMotor(MotorType.kRearRight, true);
        
        train.setSafetyEnabled(false);
    }
    
    /**
     * runs the drivetrain in TankDrive fashion
     * @param left from -1 to 1, the speed of the left side of the drivetrain
     * @param right from -1 to 1, the speed of the right side of the drivetrain
     */
    public void tankDrive(double left, double right)
    {
        //frontLeft.set(left);
        //frontRight.set(right);
        train.tankDrive(left, right);  
       
        //SmartDashboard.putNumber("MAX_POWER", left);
    }
    
    /**
     * inherited by Sendable interface, overriding the default from Subsystem
     * @return the type of SmartDashboard data being sent
     */
    public String getSmartDashboardType()
    {
        return "Subsystem";
    }
    
    public void arcadeDrive(double Y, double X) 
    {
        train.arcadeDrive(Y, X);
    }

    /**
     * sets the command to occupy ProtoDriveTrain upon initialization 
     */
    protected void initDefaultCommand()
    {
        this.setDefaultCommand(new DriveWithJoysticks());
    }
    
    /**
     * called when a value that is put into SmartDashboard changes
     * @param table the table the value is a child of
     * @param key the name of the variable
     * @param value the value of the variable
     * @param b whether the value has changed since its previous value
     */
    public void valueChanged(ITable table, String key, Object value, boolean b)
    {
        if (key.compareTo("MAX_POWER") == 0)
            MAX_POWER = ((Double) value).doubleValue();
        
        log("Stuff changed, yo!"
                + key + " " + value.toString());
    }

    /**
     * Adds variables that are wished to be tracked to a NetworkTable which is 
     * put into SmartDashboard
     * @param table the table that the values will be put into
     */
    protected void addNetworkTableValues(ITable table)
    {
        table.putNumber("MAX_POWER", MAX_POWER);
        table.putNumber("min power", 0);
        table.putNumber("med power", MAX_POWER/2);
        log("adding net values.");
    }
    
    public String getConsoleIdentity()
    {
        return "ProtoDriveTrain";
    }

    public Gyro getGyro()
    {
        return null;
    }

    public RobotDrive getTrain() 
    {
        return null;
    }
}
