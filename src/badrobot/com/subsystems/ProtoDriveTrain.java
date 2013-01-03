/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package badrobot.com.subsystems;

import badrobot.com.BadRobotMap;
import badrobot.com.subsystems.interfaces.IDriveTrain;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
        initialize();
        SmartDashboard.putData(this);
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

    public void valueChanged(String key, Object value)
    {
        if (key.compareTo("max power") == 0)
            MAX_POWER = ((Double) value).doubleValue();
        
        System.out.println("Stuff changed, yo!"
                + key + " " + value.toString());
    }

    public void valueConfirmed(String key, Object value)
    {
    }

    protected void addNetworkTableValues(NetworkTable nTable)
    {
        nTable.putDouble("max power", MAX_POWER);
        nTable.putDouble("min power", 0);
        nTable.putDouble("med power", MAX_POWER/2);
    }

    protected void initDefaultCommand()
    {
    }
}
