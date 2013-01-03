/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package badrobot.com.subsystems;

import edu.wpi.first.wpilibj.PIDOutput;

/**
 *
 * @author Jon Buckley
 */
public class SoftPID implements PIDOutput
    {
        double output = 0;

        public SoftPID()
        {
        }

        public double getValue()
        {
            return this.output;
        }

        public void pidWrite(double output)
        {
            this.output = output;
        }
    }