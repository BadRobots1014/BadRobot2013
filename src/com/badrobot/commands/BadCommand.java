/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.commands;

import com.badrobot.OI;
import com.badrobot.subsystems.interfaces.Logger;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author adrian
 */
public abstract class BadCommand extends CommandBase implements Logger {
    protected static final boolean CONSOLE_OUTPUT_ENABLED = true;

    public abstract String getConsoleIdentity();
    
    public void log(String str) {
        if(true) {
            System.out.println(getConsoleIdentity()+": "+str);
        }
    }
}
