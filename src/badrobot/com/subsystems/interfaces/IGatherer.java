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
}
