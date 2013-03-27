/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems.interfaces;

/**
 *  This interface helps enforce a system-wide logging architecture.
 * 
 * @author Jon Buckley
 */
public interface Logger
{
    /**
     * Use instead of System.out.println(). This is so that instead of typing
     * System.out.println, you just type log(). Also, this enables console 
     * output to be enabled and disabled on a class by class basis. Implement
     * with a boolean switch to control behavior. 
     * @param out the string to be outputted to the console
     */
    public void log(String out);
}
