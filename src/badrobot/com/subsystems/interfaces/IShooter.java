/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package badrobot.com.subsystems.interfaces;

/**
 *
 * @author Jon Buckley
 */
public interface IShooter
{
    /**
     * runs the shooter at the designated speed
     * @param speed the speed at which to run the motor, from -1 to 1
     */
    public void runShooter(double speed);
}
