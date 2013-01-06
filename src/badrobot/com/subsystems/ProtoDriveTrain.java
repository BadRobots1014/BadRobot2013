/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package badrobot.com.subsystems;

import badrobot.com.BadRobotMap;
import badrobot.com.subsystems.interfaces.IDriveTrain;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Jon Buckley
 */
public class ProtoDriveTrain extends BadSubsystem implements IDriveTrain
{
    Jaguar frontLeft, frontRight, backLeft, backRight;
    RobotDrive train;
    private static double MAX_POWER = .8;
    
    public ProtoDriveTrain()
    {
        if (!CONSTRUCTED)
        {      
            initialize();
            SmartDashboard.putData("ProtoDriveTrain", this);
            CONSTRUCTED = true;
        }
    }
    
    protected void initialize()
    {
        frontLeft = new Jaguar(BadRobotMap.frontLeftSpeedController);
        frontRight = new Jaguar(BadRobotMap.frontRightSpeedController);
        backLeft = new Jaguar(BadRobotMap.backLeftSpeedController);
        backRight = new Jaguar(BadRobotMap.backRightSpeedController);
        
        train = new RobotDrive(frontLeft, frontRight, backLeft, backRight);
    }
    
    public void tankDrive(double left, double right)
    {
        train.tankDrive(left, right);
    }

    protected void initDefaultCommand()
    {
    }

    public void valueChanged(ITable table, String key, Object value, boolean b)
    {
        if (key.compareTo("MAX_POWER") == 0)
            MAX_POWER = ((Double) value).doubleValue();
        
        System.out.println("Stuff changed, yo!"
                + key + " " + value.toString());
        
    }

    protected void addNetworkTableValues(ITable table)
    {
        table.putNumber("MAX_POWER", MAX_POWER);
        table.putNumber("min power", 0);
        table.putNumber("med power", MAX_POWER/2);
        System.out.println("adding net values.");
    }

    public String getConsoleIdentity()
    {
        return "ProtoDriveTrain";
    }
}
