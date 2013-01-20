package com.badrobot.subsystems;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.badrobot.BadRobotMap;
import com.badrobot.commands.DriveWithJoysticks;
import com.badrobot.subsystems.interfaces.IDriveTrain;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 * @author Isaac
 */
public class ImpDriveTrain extends BadSubsystem implements IDriveTrain
{
    Jaguar frontLeft, frontRight, backLeft, backRight;
    RobotDrive train;
    
    public static Gyro dTrain_Gyro;
    public static double speedscale;
    
    public static ImpDriveTrain instance;
    
    public static ImpDriveTrain getInstance()
    {
        if (instance == null)
        {
            instance = new ImpDriveTrain();
        }
        return instance;
    }
    
    private ImpDriveTrain()
    {
        initialize();
        SmartDashboard.putData("ImpDriveTrain", this);
    }
    
    protected void initialize() 
    {
        frontLeft = new Jaguar(BadRobotMap.frontLeftSpeedController);
        frontRight = new Jaguar(BadRobotMap.frontRightSpeedController);
        backLeft = new Jaguar(BadRobotMap.backLeftSpeedController);
        backRight = new Jaguar(BadRobotMap.backRightSpeedController);
        
        train.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        train.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        train.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        train.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        
        train.setSafetyEnabled(false);
        
        train = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
        dTrain_Gyro = new Gyro(BadRobotMap.driveTrainGyro);
        speedscale = 1;
    }
    
    /**
     * Event handler for when the value of a variable put in 
     * the network has changed.
     * Changes the local value of the variable.
     * 
     * @param key   the name of the variable in the NetworkTable
     * @param value the value that the variable has been changed to
     */
    public void valueChanged(ITable itable, String key, Object value, boolean bln) 
    {        
        log("Things have changed:" + key + " " + value.toString());
    }
    
    protected void addNetworkTableValues(ITable table) 
    {
        table.putNumber("Speed Scale", speedscale);
    }

    public String getConsoleIdentity() 
    {
        return "ImpDriveTrain";
    }

    protected void initDefaultCommand() 
    {
        this.setDefaultCommand(new DriveWithJoysticks());
    }

    /**
     * Drives the robot in tank drive--two sticks represent the left and right
     * sides of the robot; pushing forward on the left stick moves the left side
     * forward, pushing backwards on the right stick moves the right side of the
     * robot backwards.
     * @param left the left side joystick value (-1 to 1)
     * @param right the right joystick value (-1 to 1)
     */
    public void tankDrive(double left, double right) 
    {
        train.tankDrive(left*speedscale, right*speedscale);
        //backLeft.set(left);
        //backRight.set(right);
    }    
    
    public Gyro getGyro() 
    {
        return dTrain_Gyro;
    }

    public RobotDrive getTrain() 
    {
        return train;
    }
}
