/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package badrobot.com.subsystems.interfaces;

/**
 *
 * @author Jon Buckley
 */
public interface IGatherer
{
    /**
     * runs the gatherer
     */
    public void gather();
    
    /**
     * runs the gatherer in reverse, ejecting discs
     */
    public void spit();
    
    /**
     * raises the gatherer to the height appropriate to load the discs in the
     * gatherer into the shooter
     */
    public void raiseToShooter();
    
    /**
     * raises the gatherer to the position that it is "stowed", the position
     * at which it is inside the starting perimeter 
     */
    public void raiseToStowed();
}
