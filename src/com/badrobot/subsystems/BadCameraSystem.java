/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems;

import com.badrobot.BadRobotMap;
import com.badrobot.commands.BadDefaultTracker;
import com.badrobot.utils.DetectedPoint;
import com.badrobot.utils.TrackingCriteria;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.image.RGBImage;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * The <code>BadCameraSystem</code> class provides methods to handle grabbing images
 * from a camera, and processing those input images to locate goals.
 * The intent of this class is to provide generic methods that allow it to be
 * reusable so the coming years are able to use it.
 * @author Jacob Garber
 */
public class BadCameraSystem extends BadSubsystem {

    private static BadCameraSystem instance;
    private static final boolean USE_CAMERA = false; //if false, load from CRIO
    
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
        
        imageTrackingCamera.writeResolution(AxisCamera.ResolutionT.k160x120);
    }
    
    public DetectedPoint[] getTargetCoordinates(TrackingCriteria criteria) 
    {
        ColorImage colorImage = null;
        BinaryImage binaryImage = null;
        BinaryImage resultImage = null;
        DetectedPoint[] results = null;
        
        try 
        {
            if(!USE_CAMERA) {
                colorImage = new RGBImage("inputImage.jpg");
            } else {
                colorImage = imageTrackingCamera.getImage();
            }
                
            int hueLow = criteria.getMinimumHue();
            int hueHigh = criteria.getMaximumHue();
            int saturationLow = criteria.getMinimumSaturation();
            int saturationHigh = criteria.getMaximumSaturation();
            int valueLow = criteria.getMinimumValue();
            int valueHigh = criteria.getMaximumValue();
            
            //Attempt to isolate the colours of the LED ring
            binaryImage = colorImage.thresholdHSV(hueLow, hueHigh, saturationLow, saturationHigh,
                                valueLow, valueHigh);
            //Fill in any detected "particles" to make analysis easier
            //See: http://zone.ni.com/reference/en-XX/help/372916L-01/nivisionconcepts/advanced_morphology_operations/
            binaryImage.convexHull(true);
            resultImage = binaryImage.removeSmallObjects(true, 3);
            
            ParticleAnalysisReport[] reports = resultImage.getOrderedParticleAnalysisReports();
            results = new DetectedPoint[reports.length];
            int pointIndex = 0;
            for(int i = 0; i < reports.length; i++) 
            {
                ParticleAnalysisReport report = reports[i];
                
                int aspectRatio = report.boundingRectWidth/report.boundingRectHeight;
                double area = report.particleArea;
                double aspectError = (aspectRatio-criteria.getAspectRatio())/criteria.getAspectRatio();
                double areaError = (area-criteria.getParticleArea())/criteria.getParticleArea();
                aspectError = Math.abs(aspectError);
                areaError = Math.abs(areaError);
                if(aspectError < criteria.getAspectTolerance() &&
                        areaError < criteria.getAreaTolerance()) 
                {
                    results[pointIndex] = new DetectedPoint(report.center_mass_x, report.center_mass_y);
                    pointIndex++;
                }
            }
            
            //Remove the empty slots in the array
            if(pointIndex != results.length-1) 
            {
                DetectedPoint[] compressedResults = new DetectedPoint[pointIndex+1];
                int x = 0;
                for(int i = 0; i < results.length; i++) 
                {
                    if(results[i] != null) 
                    {
                        compressedResults[x] = results[i];
                        x++;
                    }
                }
                results = compressedResults;
            }
        }
        catch(AxisCameraException ex) //Comment when loading from CRIO rather than camera
        {
            log("Unable to grab images from the image tracking camera");
            ex.printStackTrace();
        }
        catch(NIVisionException ex) 
        {
            log("Encountered a NIVisionException while trying to acquire coordinates");
        } 
        finally 
        {
            try 
            {
                //For debugging purposes
                colorImage.write("colorImage.jpg");
                binaryImage.write("binaryImage.jpg");
                resultImage.write("resultImage.jpg");
                
                if(colorImage != null)
                    colorImage.free();
                if(binaryImage != null)
                    binaryImage.free();
                if(resultImage != null)
                    resultImage.free();
            } 
            catch(NIVisionException ex) 
            {
                //Really? Throw an exception while freeing memory?
                log("Encountered an exception while freeing memory... Really NI? Really?");
                ex.printStackTrace();
            }
        }
        return results;
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
        setDefaultCommand(new BadDefaultTracker());
    }
    
}
