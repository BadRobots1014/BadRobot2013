/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot;

import com.badrobot.commands.CommandBase;
import com.badrobot.subsystems.BadSubsystem;
import edu.wpi.first.wpilibj.Preferences;

/**
 *
 * @author Jon Buckley
 */
public class BadPreferences
{
    private static boolean IS_PREFERENCES_ALREADY_INITIALIZED = false;
    
    private static String[][] valuePairs = new String[30][2];
    static int size = 0;
    
    private static BadPreferences instance;
    
    private static Preferences prefs = Preferences.getInstance();
    
    public static BadPreferences getInstance()
    {
        if (instance == null)
            instance = new BadPreferences();
        
        return instance;
    }
    
    private BadPreferences()
    {        
        // is the last value in there? We'll assume that this means its been inited
        if (prefs.containsKey(valuePairs[size-1][0]))
        {
           IS_PREFERENCES_ALREADY_INITIALIZED = true;
        }
        else
        {
            publishPreferences();
        }
    }
    
    /**
     * Adds a key-value pair into a database so that it is registered if it needs
     * to be saved to the Preferences 
     * @param key the String to be associated with the value (it's name)
     * @param val the actual value wished to be tracked
     */
    public static void registerValue(String key, String val)
    {
        valuePairs[size][0] = key;
        valuePairs[size][1] = val;
        
        size++;
    }
    
    /**
     * Saves the preferences to the flash memory on the cRIO
     */
    public static void publishPreferences()
    {
        for (int i = 0; i < size; i++)
        {
            prefs.putString(valuePairs[i][0], valuePairs[i][1]);
        }
        IS_PREFERENCES_ALREADY_INITIALIZED = true;
    }
}
