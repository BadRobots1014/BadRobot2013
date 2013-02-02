/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.BadRobotMap;
import com.badrobot.OI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Jason
 */
public class DriveWithTrigger extends BadCommand {

    protected boolean TANK_DRIVE_MODE = true;

    public DriveWithTrigger() {
        requires((Subsystem) driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        String mode = (String) CommandBase.driveChooser.getSelected();

        if (mode == "tankDrive") {
            TANK_DRIVE_MODE = true;
        } else {
            TANK_DRIVE_MODE = false;
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (TANK_DRIVE_MODE) {
            driveTrain.tankDrive(OI.getPrimaryLeftTrigger(), OI.getPrimaryRightTrigger());
        } else {
            driveTrain.arcadeDrive(OI.getPrimaryLeftTrigger(), OI.getPrimaryRightTrigger());
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        log("Interrupted");
    }

    public String getConsoleIdentity() {
        return "DriveWithTrigger";
    }

    public void valueChanged(ITable itable, String string, Object o, boolean bln) {
    }

    protected void addNetworkTableValues(ITable table) {
    }
}
