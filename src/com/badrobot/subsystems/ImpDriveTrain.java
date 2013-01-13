package com.badrobot.subsystems;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.badrobot.subsystems.BadSubsystem;
import com.badrobot.subsystems.interfaces.IDriveTrain;
import edu.wpi.first.wpilibj.tables.ITable;
import com.badrobot.BadRobotMap;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
/**
 *
 * @author Isaac
 */
public class ImpDriveTrain extends BadSubsystem implements IDriveTrain
{
    Jaguar frontLeft, frontRight, backLeft, backRight;
    RobotDrive train;
    private static double MAX_POWER = .8;
    
    public static ImpDriveTrain instance;
    
    public static ImpDriveTrain getInstance()
    {
        if (instance == null)
        {
            instance = new ImpDriveTrain();
        }
        return instance;
    }
    
    protected void initialize() 
    {
        frontLeft = new Jaguar(BadRobotMap.frontLeftSpeedController);
        frontRight = new Jaguar(BadRobotMap.frontRightSpeedController);
        backLeft = new Jaguar(BadRobotMap.backLeftSpeedController);
        backRight = new Jaguar(BadRobotMap.backRightSpeedController);
        
        //this is a flawed construction of RobotDrive. This would be very poor
        //for the robot if we ran it. (it would cause motors to drive against
        //their partners. Look at RobotDrive src or API for correct usage on
        //constructor
        train = new RobotDrive(frontLeft, frontRight, backLeft, backRight);
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln) 
    {
        
    }

    protected void addNetworkTableValues(ITable table) 
    {
        
    }

    public String getConsoleIdentity() 
    {
        return "ImpDriveTrain";
    }

    protected void initDefaultCommand() 
    {
        //setDefaultCommand(Command C);
    }

    public void tankDrive(double left, double right) 
    {
        train.tankDrive(left, right);
        //backLeft.set(left);
        //backRight.set(right);
    }
    
}
