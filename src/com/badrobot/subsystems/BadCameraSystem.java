/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems;

import com.badrobot.BadRobotMap;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * The <code>BadCameraSystem</code> class provides methods to handle grabbing images
 * from a camera, and processing those input images to locate goals
 * @author adrian
 */
public class BadCameraSystem extends BadSubsystem {

    private static BadCameraSystem instance;
    
    private AxisCamera imageTrackingCamera;
    
    public static BadCameraSystem getInstance() 
    {
        if(instance == null)
        {
            instance = new BadCameraSystem();
        }
        return instance;
    }
    
    private BadCameraSystem() 
    {
        initialize();
        //May be needed later, but no real functionality now
        SmartDashboard.putData("BadCameraSystem", this);
    }
    
    protected void initialize() 
    {
        imageTrackingCamera = AxisCamera.getInstance(BadRobotMap.visionTrackingCameraAddress);
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln) 
    {
        
    }

    protected void addNetworkTableValues(ITable table) 
    {
        
    }

    public String getConsoleIdentity() 
    {
        return "BadCameraSystem";
    }

    protected void initDefaultCommand() 
    {
        
    }
    
}
