package com.badrobot;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GearTooth;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class BadRobotMap implements Sendable
{
    private static BadRobotMap instance;
    private ITable table;
    //DriveTrain
    public static int frontLeftSpeedController; //index 0
    public static int frontRightSpeedController; //index 1
    
    public static int driveTrainGyro;
    public static int driveTrainUltrasonicPing;
    public static int driveTrainUltrasonicEcho;
    
    public static int leftSideEncoderIn;
    public static int leftSideEncoderOut;
    public static int rightSideEncoderIn;
    public static int rightSideEncoderOut;
    
    // The back speed controllers are assigned because there is an issue with 
    // The gearbox and so we only want to drive two of the motors for now. The
    // Back motors are being given non-occupied ports, 11 and 12 to do this
    public static int backLeftSpeedController; //index 2
    public static int backRightSpeedController; //index 3
    
    //Potentially throwing a curve ball into the mapping
    //If there's a better way bother me (ajtgarber)
    public static String visionTrackingCameraAddress = "10.10.14.11";
    public static String frontFacingCameraAddress = "10.10.14.12";
    
    public static int primaryShooterRelay,
            secondaryShooterRelay;
    public static int opticalShooterSensor;
    
    public final int[] prototypeMap = {3,1,4,2};//values entered
    public final int[] finalMap = {3,1,4,2};
    
    public static boolean isPrototype = true;
    
    public static int speedSensorPort;
    
    public static int frisbeePusher;
    public static int frisbeePusherSwitch;
    
    public static int shooterArticulator;
    
    //Lights
    public static int redChannel;
    public static int greenChannel;
    public static int blueChannel;
    
    public BadRobotMap()
    {
        if(isPrototype == true)
        {
            frontLeftSpeedController = prototypeMap[0];
            frontRightSpeedController = prototypeMap[1];
            backLeftSpeedController = prototypeMap[2];
            backRightSpeedController = prototypeMap[3];
            
            driveTrainGyro = 1;
            driveTrainUltrasonicPing = 3;
            driveTrainUltrasonicEcho = 2;
            
            leftSideEncoderIn = 13;
            leftSideEncoderOut = 14;
            rightSideEncoderIn = 11;
            rightSideEncoderOut = 12;
            
            frisbeePusherSwitch = 5; 
            opticalShooterSensor = 1;
            
            shooterArticulator = 7;
            
            primaryShooterRelay = 1;
            secondaryShooterRelay = 2;
            frisbeePusher = 3;
        }
        else
        {
            
            frontLeftSpeedController = finalMap[0];
            frontRightSpeedController = finalMap[1];
            backLeftSpeedController = finalMap[2];
            backRightSpeedController = finalMap[3];
            
            driveTrainGyro = 1;
            driveTrainUltrasonicPing = 3;
            driveTrainUltrasonicEcho = 2;
            
            leftSideEncoderIn = 13;
            leftSideEncoderOut = 14;
            rightSideEncoderIn = 11;
            rightSideEncoderOut = 12;
            
            frisbeePusherSwitch = 5; 
            opticalShooterSensor = 1;
            
            shooterArticulator = 7;
            
            primaryShooterRelay = 1;
            secondaryShooterRelay = 2;
            frisbeePusher = 3;
        }
        
        //More than likely the IP addresses of the cameras will remain the same
        visionTrackingCameraAddress = "10.10.14.11";
        frontFacingCameraAddress = "10.10.14.10";
        
        speedSensorPort = 5;
    }
    
    /**
     * singleton accessor. If no instance of BadRobotMap exists, then it creates 
     * one. 
     * @return the singelton instance of BadRobotMap in the program
     */
    public static BadRobotMap getInstance()
    {
        if (instance != null)
            return instance;
        
        instance = new BadRobotMap();
        SmartDashboard.putData("BadRobotMap", instance);
        return instance;
    }
    
    /**
     * run as prototype, pretty straightforward
     * @param a decide whether the robot will use the final or prototype map
     */
    public void runAsPrototype(boolean a)
    {
        isPrototype = a;
    }
    
    /**
     * @return the type of NetworkTable
     */
    public String getSmartDashboardType()
    {
        return "RobotMap";
    }


    /**
     * provides compliance with the SmartDashboardData interface. 
     * @return the NetworkTable with the appropriate values for this class
     */
    public ITable getTable()
    {
        return table;
    }
    
    /**
     * method that adds all of the variables that are wished to be shared with 
     * the driverstation laptop. 
     */
    private void populateTable(ITable t)
    {   
        t.putNumber("backLeftSpeedController Port", backLeftSpeedController);
        t.putNumber("backRightSpeedController Port", backRightSpeedController);
        t.putNumber("frontLeftSpeedController Port", frontLeftSpeedController);
        t.putNumber("frontRightSpeedController Port", frontRightSpeedController);
    }

    public void initTable(ITable itable)
    {
        table = itable;
        populateTable(table);
    }
}
