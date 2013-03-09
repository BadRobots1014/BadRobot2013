/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot;

import com.badrobot.commands.CommandBase;
import com.badrobot.subsystems.BadSubsystem;
import com.badrobot.subsystems.interfaces.Logger;
import edu.wpi.first.wpilibj.Preferences;
import java.util.Vector;

/**
 *
 * @author Jon Buckley
 */
public class BadPreferences implements Logger
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
        // is the last value in there? We'll assume that this means its been initted
        if (size > 0 && prefs.getKeys().contains(valuePairs[size][0]))
        {
           IS_PREFERENCES_ALREADY_INITIALIZED = true;
        }
        else
        {
            publishPreferences();
            log ("publishing preferences");
        }
    }
    
    /**
     * Adds a key-value pair into a database so that it is registered if it needs
     * to be saved to the Preferences 
     * @param key the String to be associated with the value (its name)
     * @param val the actual value wished to be tracked
     */
    public static void registerValue(String key, String val)
    {
       String parsedKey = key;
       if (contains(key))
           return;
        
       if (key.indexOf(' ') >= 0)
           parsedKey = key.replace(' ', '_');
       
        valuePairs[size][0] = parsedKey;
        valuePairs[size][1] = val;
        
        size++;
    }
    
    public static boolean contains(String key)
    {
        if (size == 0)
            return false;
        
        for (int i = 0; i < size; i++)
        {
            if (valuePairs[size][0].compareTo(key)==0)
                return true;
        }
        
        return false;
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
        prefs.save();
        
        IS_PREFERENCES_ALREADY_INITIALIZED = true;
    }
    
    /**
     * Removes all keys and values from the Preferences. DANGEROUS
     */
    public static void flush()
    {
        Vector keys = prefs.getKeys();
        for (int i = 0; i < keys.size(); i ++)
        {
            prefs.remove((String) keys.elementAt(i));
        }
        
        prefs.save();
        IS_PREFERENCES_ALREADY_INITIALIZED = false;
    }
    
    /**
     * Gets the value at a certain String index. If you are trying to get a number,
     * which you more than likely are, simply create a Double or Integer from the
     * String ex. double value = new Double(getValue("key")).doubleValue();
     * @param key the key at which the value exists
     * @return the String form of the data stored at the key or null if no such
     * key exists
     */
    public static String getValue(String key, String backupValue)
    {
        if (prefs.getKeys().contains(key))
            return prefs.getString(key, "");
        
        //otherwise
        registerValue(key, backupValue);
        prefs.putString(key, backupValue);
        prefs.save();
        
        return backupValue;
    }

    public void log(String out)
    {
        if (OI.CONSOLE_OUTPUT_ENABLED)
        {
            System.out.println("BadPreferences: " + out);
        }
    }
}
