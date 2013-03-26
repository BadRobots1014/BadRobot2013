/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package com.badrobot;

import com.badrobot.commands.autonomousCommands.AutoAimAndShoot;
import com.badrobot.commands.autonomousCommands.DriveForwardTurnShoot;
import com.badrobot.commands.*;
import com.badrobot.commands.autonomousCommands.AimWithCamera;
import com.badrobot.commands.autonomousCommands.DriveForwardAndShoot;
import com.badrobot.commands.autonomousCommands.DriveForwardToWallTurnAndShoot;
import com.badrobot.commands.autonomousCommands.DriveForwardTurnShootDriveBack;
import com.badrobot.commands.autonomousCommands.ShootAndDriveBack;
import com.badrobot.subsystems.DecorativeLights;
//import com.badrobot.commands.autonomousCommands.DriveForwardAutoAimShoot;
import com.badrobot.subsystems.interfaces.ILights;
import com.badrobot.subsystems.interfaces.Logger;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotMain extends IterativeRobot implements Logger {

    Command autonomousCommand; //Autonomous Command
    SendableChooser autoChooser; //adds a widget to the SmartDashboard

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        BadRobotMap.isPrototype = true;

        // Initialize all subsystems
        CommandBase.init();

        autoChooser = new SendableChooser();

        //autoChooser.addDefault("Drive Forward + Auto Fire", new DriveForwardAutoAimShoot());
        autoChooser.addObject("Drive Straight Forward", new DriveStraightForward(5));
        autoChooser.addObject("Auto Aim", new AimWithCamera());
        autoChooser.addObject("Auto Aim And Shoot", new AutoAimAndShoot());
        autoChooser.addDefault("Drive Forward And Shoot (variable time, 3 frisbees)", new DriveForwardAndShoot());
        autoChooser.addObject("Shoot three frisbees", new SafeShoot(3));
        autoChooser.addObject("Drive Forward to 109 inches, turn and Shoot", new DriveForwardToWallTurnAndShoot());
        autoChooser.addObject("Shoot and Drive Backwards", new ShootAndDriveBack());
        autoChooser.addObject("Shootanddoalotofotherstuff", new DriveForwardTurnShootDriveBack());

        SmartDashboard.putData("Autonomous Mode Chooser", autoChooser);

        if (CommandBase.lightSystem != null) {
            new RunLights(ILights.kYellow).start();
        }
    }

    public void autonomousInit() {
        autonomousCommand = (Command) autoChooser.getSelected();
        Scheduler.getInstance().add(autonomousCommand);

        //Scheduler.getInstance().add(autonomousCommand);

        Command turnOnCameraLight = new TurnOnCameraLight();
        Scheduler.getInstance().add(turnOnCameraLight);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        Watchdog.getInstance().feed();
    }

    public static int ALLIANCE_COLOR = 0;
    public void teleopInit() {
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }

        Watchdog.getInstance().setEnabled(false);

        Command driveTrainControls = new DriveWithController();
        driveTrainControls.start();
        //Scheduler.getInstance().add(driveTrainControls);

        Command shooterControls = new SafeShoot();
        shooterControls.start();
        //Scheduler.getInstance().add(shooterControls);

        if (CommandBase.shooterArticulator != null) 
        {
            Command articulate = new ArticulateShooter();
            articulate.start();
        }
        //Scheduler.getInstance().add(articulate);

        ALLIANCE_COLOR = (OI.ALLIANCE_COLOR == DriverStation.Alliance.kBlue_val) ? ILights.kBlue : ILights.kRed;
        if (CommandBase.lightSystem != null)
         Scheduler.getInstance().add(new RunLights(ALLIANCE_COLOR));
        
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        Watchdog.getInstance().feed();
        // Timer.delay(.1);

        if (((Subsystem) CommandBase.driveTrain).getCurrentCommand() == null) {
            Scheduler.getInstance().add(new DriveWithController());
        }
    }

    public void disabledInit() {
        log("Disabled Initialized");
        if (CommandBase.lightSystem != null) {
            DecorativeLights.getInstance().setColor(ILights.kYellow);
        }
    }

    /**
     * This function is called periodically during test mode this comment was
     * ammended by Joe Cssidy
     */
    public void testPeriodic() {
        LiveWindow.run();
    }

    public void log(String out) {
        if (OI.CONSOLE_OUTPUT_ENABLED) {
            System.out.println("RobotMain: " + out);
        }
    }
}
