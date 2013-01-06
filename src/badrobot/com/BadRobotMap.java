package badrobot.com;

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
    public static final int frontLeftSpeedController = 1;
    public static final int frontRightSpeedController = 2;
    public static final int backLeftSpeedController = 3;
    public static final int backRightSpeedController = 4;
   
    /*
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
    
    /*
     * @return the type of NetworkTable
     */
    public String getSmartDashboardType()
    {
        return "RobotMap";
    }


    /*
     * provides compliance with the SmartDashboardData interface. 
     * @return the NetworkTable with the appropriate values for this class
     */
    public ITable getTable()
    {
        return table;
    }
    
    /*
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
