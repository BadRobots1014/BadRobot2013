package badrobot.com.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkListener;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboardData;

/**
 * @author Jon Buckley
 */
public abstract class BadSubsystem extends Subsystem implements SmartDashboardData, NetworkListener
{   
    //singleton instance of BadSubsystem
    protected BadSubsystem instance;
    
    /*
     * enforces singleton design. No public constructor, only this accessor. 
     * @return the singleton instance of BadSubsystem, or if it hasn't been 
     * created yet, creates it and returns it
     */
    public BadSubsystem getInstance()
    {
        if (instance == null)
        {
            initialize();
            SmartDashboard.putData(this);
        }
        return instance;
    }
    
    /*
     * Subclasses should implement their own implementations on initialize. This
     * method is meant to instantiate any hardware or variables that will be 
     * needed. This is specific to each class and can be left blank.
     */
    protected abstract void initialize();
    
    //provides automatic NetworkTable compliance
    protected NetworkTable table;
    
    /*
     * provides compliance with Sendable interface. This automatically adds the
     * subsystem to the SmartDashboard.
     */
    public NetworkTable getTable()
    {
        if (table != null)
            return table;
        
        table = new NetworkTable();
        addNetworkTableValues(table);
        table.addListenerToAll(getNetworkListener());
        
        return table;
    }
    
    /*
     * @return a NetworkListener that responds to changed values. This class
     * implements NetworkListener. Override and return null if you don't want 
     * any response.
     */
    protected NetworkListener getNetworkListener()
    {
        return this;
    }
    
    /*
     * Event handler for when a value of a variable put in the network has 
     * changed. This should have some sort of logic to figure out which variable
     * it was the key parameter and change the local value of the variable to 
     * reflect it.
     * @param key the name of the variable in the NetworkTable
     * @param value the value that the variable has been changed to
     */
    public abstract void valueChanged(String key, Object value);
    
    /*
     * Event handler for when a value has been confirmed. 
     */
    public abstract void valueConfirmed(String key, Object value); 
    
    /* puts all pertinent values into the table (speeds, frequencies, any value 
     * that would be tracked or modified)
     * @param nTable the table that is being initialized and needs values
     */
    protected abstract void addNetworkTableValues(NetworkTable nTable);
}
