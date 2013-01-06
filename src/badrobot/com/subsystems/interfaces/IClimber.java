/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package badrobot.com.subsystems.interfaces;

/**
 * The Subsystem responsible for climbing the pyramid
 * @author Jon Buckley
 */
public interface IClimber
{ 
    /**
     * climbs the first tier of the pyramid, no big deal
     */
    public void climbFirstLevel();
    
    /**
     * climbs the middle tier of the pyramid, assumed to start already on 
     * the first tier. Things are gettin' tricky now
     */
    public void climbSecondLevel();
    
    /**
     * climbs the last tier of the pyramid... hard hats and steel toed boots are
     * recommended 
     */
    public void climbTheGodDamnThirdLevel();
}
