/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems;

import com.badrobot.BadRobotMap;
import com.badrobot.commands.InjectFrisbeesWithController;
import com.badrobot.subsystems.interfaces.IFrisbeePusher;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Jon Buckley
 */
public class ProtoFrisbeePusher extends BadSubsystem implements IFrisbeePusher
{
    DigitalInput frisbeePusherLimitSwitch;
    Relay frisbeePusher;
    
    private static IFrisbeePusher instance;
    
   private boolean frisbeePusherDirectionIsForward = true;
    
    public static IFrisbeePusher getInstance()
    {
        if (instance == null)
            instance = new ProtoFrisbeePusher();
        
        return instance;
    }
    
    private ProtoFrisbeePusher()
    {
        frisbeePusherLimitSwitch = new DigitalInput(BadRobotMap.frisbeePusherSwitch);
        
        frisbeePusher = new Relay(BadRobotMap.frisbeePusher);
        frisbeePusher.setDirection(Relay.Direction.kForward);
        
    }
    
    protected void initialize()
    {
    }

    public void valueChanged(ITable itable, String key, Object value, boolean bln)
    {
    }

    protected void addNetworkTableValues(ITable table)
    {
    }

    public String getConsoleIdentity()
    {
        return "ProtoFrisbeePusher";
    }

    protected void initDefaultCommand()
    {
        this.setDefaultCommand(new InjectFrisbeesWithController());   
    }    
    
    /**
     * instructs the window lift motor to run forward or back, driving the 
     * frisbee into the motors, or retracting to allow a frisbee to drop down
     * @param forward should be forward to push frisbee into shooter, backwards
     * to allow frisbee to fall through
     */
    public void pushFrisbee(boolean forward)
    {
        /*if (forward && !frisbeePusherDirectionIsForward)
        {
            frisbeePusher.setDirection(Relay.Direction.kForward);
            frisbeePusherDirectionIsForward = true;
        }        
        else if (frisbeePusherDirectionIsForward)
        {
            frisbeePusher.setDirection(Relay.Direction.kReverse);
            frisbeePusherDirectionIsForward = false;
        }*/
        
        //if (frisbeePusher.get() != Relay.Value.kOn)
        {
            frisbeePusher.set(Relay.Value.kOn);
        }
    }
    
    /**
     * stops the window lift motor from running
     */
    public void stopFrisbeePusher()
    {
        //if (frisbeePusher.get() != Relay.Value.kOff)
        frisbeePusher.set(Relay.Value.kOff);
    }

    public boolean isFrisbeeRetracted()
    {
        SmartDashboard.putBoolean("frisbee pusher", frisbeePusherLimitSwitch.get());
        return frisbeePusherLimitSwitch.get();
    }
}
