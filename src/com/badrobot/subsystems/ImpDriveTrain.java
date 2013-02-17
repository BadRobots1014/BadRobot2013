package com.badrobot.subsystems;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.badrobot.BadRobotMap;
import com.badrobot.commands.CoopDriveWithTriggers;
import com.badrobot.commands.DriveStraightForward;
import com.badrobot.commands.DriveWithJoysticks;
import com.badrobot.commands.Turn;
import com.badrobot.subsystems.interfaces.IDriveTrain;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 * @author Isaac
 */
public class ImpDriveTrain extends BadSubsystem implements IDriveTrain
{
    SpeedController frontLeft, frontRight, backLeft, backRight;
    RobotDrive train;
    Ultrasonic ultrasonic;
    Gyro gyro;
    
    PIDController leftFrontController, rightFrontController, leftBackController, rightBackController;
    
    Encoder leftEncoder;
    Encoder rightEncoder;
    
    private double MAX_SPEED = 1600;
    
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
        leftEncoder = new Encoder(BadRobotMap.leftSideEncoderIn, BadRobotMap.leftSideEncoderOut, true);
        leftEncoder.setDistancePerPulse(1);
        leftEncoder.start();
        
        rightEncoder = new Encoder(BadRobotMap.rightSideEncoderIn, BadRobotMap.rightSideEncoderOut, true);
        rightEncoder.setDistancePerPulse(1);
        rightEncoder.start();
        
        gyro = new Gyro(BadRobotMap.driveTrainGyro);
        gyro.reset();
        
        ultrasonic = new Ultrasonic(BadRobotMap.driveTrainUltrasonicPing,
                BadRobotMap.driveTrainUltrasonicEcho, Ultrasonic.Unit.kInches);
        ultrasonic.setEnabled(true);
        ultrasonic.setAutomaticMode(false);
        
        frontLeft = new Victor(BadRobotMap.frontLeftSpeedController);
        frontRight = new Talon(BadRobotMap.frontRightSpeedController);
        backLeft = new Victor(BadRobotMap.backLeftSpeedController);
        backRight = new Talon(BadRobotMap.backRightSpeedController);
        
        leftFrontController = new PIDController(.01, 0, 0, leftEncoder, frontLeft);
        rightFrontController = new PIDController(.01, 0, 0, rightEncoder, frontRight);
        leftBackController = new PIDController(.01, 0, 0, leftEncoder, backLeft);
        rightBackController = new PIDController(.01, 0, 0, rightEncoder, backRight);
        
        SmartDashboard.putData("left front PID",leftFrontController);
        
        leftFrontController.setInputRange(-MAX_SPEED, MAX_SPEED);
        rightFrontController.setInputRange(-MAX_SPEED, MAX_SPEED);
        leftBackController.setInputRange(-MAX_SPEED, MAX_SPEED);
        rightBackController.setInputRange(-MAX_SPEED, MAX_SPEED);
        
        leftFrontController.setOutputRange(-1,1);
        rightFrontController.setOutputRange(-1,1);
        leftBackController.setOutputRange(-1,1);
        rightBackController.setOutputRange(-1,1);
                
        /*leftFrontController.enable();
        rightFrontController.enable();
        leftBackController.enable();
        rightBackController.enable();*/
        
        train = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
        
        /*train.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        train.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        train.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        train.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        */
        
        //train.setSafetyEnabled(false);
        
        //dTrain_Gyro = new Gyro(BadRobotMap.driveTrainGyro);
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
        log("left speed: "+leftEncoder.getRate());
        log("right speed: "+rightEncoder.getRate());
        //log(left + " left " + right + " right");
        train.tankDrive(left, right);
        /*leftFrontController.setSetpoint(left*MAX_SPEED);
        leftBackController.setSetpoint(left*MAX_SPEED);
        rightFrontController.setSetpoint(right*MAX_SPEED);
        rightBackController.setSetpoint(right*MAX_SPEED);*/
    }

    public void arcadeDrive(double Y, double X) 
    {
        train.arcadeDrive(Y, X);
    }
    
    public Gyro getGyro() 
    {
        return gyro;
    }

    public RobotDrive getTrain() 
    {
        return train;
    }
    
    /** 
     * @return Returns distance to wall in inches.
     */
    public double getDistanceToWall()
    {
        ultrasonic.ping();
        try {
            Thread.sleep(30);
        } catch(Exception ex) {}
        return ultrasonic.getRangeInches();
    }
}
