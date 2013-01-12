package com.badrobot;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

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
    public static int backLeftSpeedController; //index 2
    public static int backRightSpeedController; //index 3
    
    //Potentially throwing a curve ball into the mapping
    //If there's a better way bother me (ajtgarber)
    public static String visionTrackingCameraAddress;
    public static String frontFacingCameraAddress;
   
    public final int[] prototypeMap = {3,1,4,2};//values entered
    public final int[] finalMap = {2,4,3,1};
    
    private boolean isPrototype;
    
    public BadRobotMap()
    {
        if(isPrototype == true)
        {
            frontLeftSpeedController = prototypeMap[0];
            frontRightSpeedController = prototypeMap[1];
            backLeftSpeedController = prototypeMap[2];
            backRightSpeedController = prototypeMap[3];
        }
        else
        {
            frontLeftSpeedController = finalMap[0];
            frontRightSpeedController = finalMap[1];
            backLeftSpeedController = finalMap[2];
            backRightSpeedController = finalMap[3];
        }
        //More than likely the IP addresses of the cameras will remain the same
        visionTrackingCameraAddress = "10.10.14.9";
        frontFacingCameraAddress = "10.10.14.10";
    }
    
    /**
     * singleton accesor. If no instance of BadRobotMap exists, then it creates 
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
