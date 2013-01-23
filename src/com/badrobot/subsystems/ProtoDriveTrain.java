/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems;

import com.badrobot.BadRobotMap;
import com.badrobot.commands.DriveWithJoysticks;
import com.badrobot.subsystems.interfaces.IDriveTrain;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

/**
 * v1.0 Working drivetrain on shelby
 * @author Jon Buckley
 */
public class ProtoDriveTrain extends BadSubsystem implements IDriveTrain
{
    SpeedController frontLeft, frontRight, backLeft, backRight;
    RobotDrive train;
    private static double MAX_POWER = .8;
    
    private static ProtoDriveTrain instance;
    
    public static ProtoDriveTrain getInstance() 
    {
        if(instance == null)
        {
            instance = new ProtoDriveTrain();
        }
        return instance;
    }
    
    private ProtoDriveTrain()
    {
        initialize();
        SmartDashboard.putData("ProtoDriveTrain", this);
    }
    
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
    
    public void tankDrive(double left, double right)
    {
        //frontLeft.set(left);
        //frontRight.set(right);
        train.tankDrive(left, right);        
    }
    
    /**
     * inherited by Sendable interface, overriding the default from Subsystem
     * @return the type of SmartDashboard data being sent
     */
    public String getSmartDashboardType()
    {
        return "Subsystem";
    }
    
    protected void initDefaultCommand()
    {
        this.setDefaultCommand(new DriveWithJoysticks());
    }
    
    public void valueChanged(ITable table, String key, Object value, boolean b)
    {
        if (key.compareTo("MAX_POWER") == 0)
            MAX_POWER = ((Double) value).doubleValue();
        
        log("Stuff changed, yo!"
                + key + " " + value.toString());
    }
    
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
