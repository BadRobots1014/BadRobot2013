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
    
    protected void initialize() 
    {
        frontLeft = new Jaguar(BadRobotMap.frontLeftSpeedController);
        frontRight = new Jaguar(BadRobotMap.frontRightSpeedController);
        backLeft = new Jaguar(BadRobotMap.backLeftSpeedController);
        backRight = new Jaguar(BadRobotMap.backRightSpeedController);
        
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
        
    }

    public void tankDrive(double left, double right) 
    {
        //train.tankDrive(left, right);
        backLeft.set(left);
        backRight.set(right);
    }
    
}
