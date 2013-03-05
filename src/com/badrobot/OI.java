
package com.badrobot;

import com.badrobot.commands.CommandBase;
import com.badrobot.commands.InjectFrisbee;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI 
{
    public static boolean CONSOLE_OUTPUT_ENABLED = true;
    
    public static Joystick primaryXboxController, secondaryXboxController;
    
    private static int LEFT_STICK_X = 1, LEFT_STICK_Y = 2, RIGHT_STICK_X = 3, RIGHT_STICK_Y = 5;
    private static int PRIMARY_JOY = 1, SECONDARY_JOY = 2;
    
    private static int A_BUTTON = 1, B_BUTTON = 2, X_BUTTON = 3, Y_BUTTON = 4, LB = 5, RB = 6, SELECT = 7, START = 8
            , LEFT_JOY_CLICK = 9, RIGHT_JOY_CLICK = 10;
    
    public static boolean IS_DEMO_MODE = true;
    
    public static int ALLIANCE_COLOR;
    
    
    public void init()
    {
        primaryXboxController = new Joystick(PRIMARY_JOY);
        secondaryXboxController = new Joystick(SECONDARY_JOY);    
        
        ALLIANCE_COLOR = DriverStation.getInstance().getAlliance().value;
        
        //button that senses seconadry Right bumper press for shooter injection
        /*if (CommandBase.frisbeePusher != null)
        {
            Button injectFrisbee = new Button() {
                public boolean get()
                {
                    return (secondaryXboxController.getRawButton(RB));
                }
            };
            injectFrisbee.whenPressed(new InjectFrisbee());   
        }*/
    }
    
    public static boolean isDemoMode()
    {
        return DriverStation.getInstance().getDigitalIn(1);
    }
    
    public static double DEADZONE_MAGIC_NUMBER = .15;
    
    /**
     * Creates a deadzone for joysticks, the controllers sticks are 
     * a little loose and so there is a margin of error for where they 
     * should be considered "neutral/not pushed"
     * @param d Double between -1 and 1 to be deadzoned
     * @return The deadzone value
     */
    private static double deadzone(double d)
    {
        //whenever the controller moves LESS than the magic number, the 
        //joystick is in the loose position so return zero - as if the 
        //joystick was not moved
        if (Math.abs(d) < DEADZONE_MAGIC_NUMBER)
        {
            return 0;
        }
        
        //When the joystick is used for a purpose (passes the if statements, 
        //hence not just being loose), do math
        return (d / Math.abs(d)) //gets the sign of d, negative or positive
                * ((Math.abs(d) - DEADZONE_MAGIC_NUMBER) / (1-DEADZONE_MAGIC_NUMBER)); //scales it
    }
    
    /**
     * Used with the primary xbox controller
     * @return The deadzone corrected left stick x value
     */
    public static double getPrimaryControllerLeftStickX()
    {
        return deadzone(-primaryXboxController.getRawAxis(LEFT_STICK_X));
    }
    
    /**
     * Used with the primary xbox controller
     * @return The deadzone corrected left stick y value
     */
    public static double getPrimaryControllerLeftStickY()
    {
        return deadzone(-primaryXboxController.getRawAxis(LEFT_STICK_Y));
    }

    /**
     * Used with the primary xbox controller
     * @return The deadzone corrected right stick x value
     */
    public static double getPrimaryControllerRightStickX()
    {
        return deadzone(-primaryXboxController.getRawAxis(RIGHT_STICK_X));
    }

    /**
     * Used with the primary xbox controller
     * @return The deadzone corrected right stick y value
     */
    public static double getPrimaryControllerRightStickY()
    {
        System.out.println("Raw: " + primaryXboxController.getRawAxis(RIGHT_STICK_Y) + 
                "  Deadzoned: " + deadzone(primaryXboxController.getRawAxis(RIGHT_STICK_Y)));
        
        return deadzone(-primaryXboxController.getRawAxis(RIGHT_STICK_Y));
    }

    /**
     * @return The primary xbox X button value
     */
    public static boolean isPrimaryXButtonPressed()
    {
        return primaryXboxController.getRawButton(X_BUTTON);
    }

    /**
     * @return The primary xbox Y button value
     */
    public static boolean isPrimaryYButtonPressed()
    {
        return primaryXboxController.getRawButton(Y_BUTTON);
    }

    /**
     * @return The primary xbox A button value
     */
    public static boolean isPrimaryAButtonPressed()
    {
        return primaryXboxController.getRawButton(A_BUTTON);
    }

    /**
     * @return The primary xbox B button value
     */
    public static boolean isPrimaryBButtonPressed()
    {
        return primaryXboxController.getRawButton(B_BUTTON);
    }

    /**
     * @return The primary xbox right bumper button value
     */
    public static boolean isPrimaryRBButtonPressed()
    {
        return primaryXboxController.getRawButton(RB);
    }

    /**
     * @return The primary xbox left bumper button value
     */
    public static boolean isPrimaryLBButtonPressed()
    {
        return primaryXboxController.getRawButton(LB);
    }

    /**
     * @return The primary xbox left joystick clicked in value
     */
    public static boolean isPrimaryLeftJoyClick()
    {
        return primaryXboxController.getRawButton(RIGHT_JOY_CLICK);
    }

    /**
     * @return The primary xbox right joystick clicked in value
     */
    public static boolean isPrimaryRightJoyClick()
    {
        return primaryXboxController.getRawButton(LEFT_JOY_CLICK);
    }
    
    /**
     * @return The secondary xbox select button value
     */
    public static boolean isPrimarySelectButtonPressed()
    {
        return primaryXboxController.getRawButton(SELECT);
    }

    /**
     * @return The secondary xbox start button value
     */
    public static boolean isPrimaryStartButtonPressed()
    {
        return primaryXboxController.getRawButton(START);
    }

    /**
     * Used with the secondary xbox controller
     * @return The deadzone corrected left stick x value
     */
    public static double getSecondaryControllerLeftStickX()
    {
        return deadzone(-secondaryXboxController.getRawAxis(LEFT_STICK_X));
    }
    
    /**
     * Used with the secondary xbox controller
     * @return The deadzone corrected left stick y value
     */
    public static double getSeondaryControllerLeftStickY()
    {
        return deadzone(-primaryXboxController.getRawAxis(LEFT_STICK_Y));
    }

    /**
     * Used with the secondary xbox controller
     * @return The deadzone corrected right stick x value
     */
    public static double getSecondaryControllerRightStickX()
    {
        return deadzone(-secondaryXboxController.getRawAxis(RIGHT_STICK_X));
    }

    /**
     * Used with the secondary xbox controller
     * @return The deadzone corrected right stick y value
     */
    public static double getSecondaryControllerRightStickY()
    {
        return deadzone(-secondaryXboxController.getRawAxis(RIGHT_STICK_Y));
    }
    
    /**
     * Used with the secondary xbox controller
     * @return The deadzone corrected left stick x value
     */
    public static double secondXboxLeftX()
    {
        return deadzone(secondaryXboxController.getRawAxis(X_BUTTON));
    }

    /**
     * Used with the secondary xbox controller
     * @return The deadzone corrected left stick y value
     */
    public static double secondXboxLeftY()
    {
        return deadzone(secondaryXboxController.getRawAxis(B_BUTTON));
    }

    /**
     * @return The secondary xbox X button value
     */
    public static boolean isSecondaryXButtonPressed()
    {
        return secondaryXboxController.getRawButton(X_BUTTON);
    }

    /**
     * @return The secondary xbox Y button value
     */
    public static boolean isSecondaryYButtonPressed()
    {
        return secondaryXboxController.getRawButton(Y_BUTTON);
    }

    /**
     * @return The secondary xbox A button value
     */
    public static boolean isSecondaryAButtonPressed()
    {
        return secondaryXboxController.getRawButton(A_BUTTON);
    }

    /**
     * @return The secondary xbox B button value
     */
    public static boolean isSecondaryBButtonPressed()
    {
        return secondaryXboxController.getRawButton(B_BUTTON);
    }

    /**
     * @return The secondary xbox right bumper value
     */
    public static boolean isSecondaryRBButtonPressed()//Right Bumper
    {
        return secondaryXboxController.getRawButton(RB);
    }

    /**
     * @return The secondary xbox left bumper value
     */
    public static boolean isSecondaryLBButtonPressed()
    {
        return secondaryXboxController.getRawButton(LB);
    }

    /**
     * @return The secondary xbox left joystick click in value
     */
    public static boolean isSecondaryLeftJoyClick()
    {
        return secondaryXboxController.getRawButton(RIGHT_JOY_CLICK);
    }

    /**
     * @return The secondary xbox right joystick click in value
     */
    public static boolean isSecondaryRightJoyClick()
    {
        return secondaryXboxController.getRawButton(LEFT_JOY_CLICK);
    }

    /**
     * @return The secondary xbox select button value
     */
    public static boolean isSecondarySelectButtonPressed()
    {
        return secondaryXboxController.getRawButton(SELECT);
    }

    /**
     * @return The secondary xbox start button value
     */
    public static boolean isSecondaryStartButtonPressed()
    {
        return secondaryXboxController.getRawButton(START);
    }
    
    /**
     * If the RawAxis(3) (the trigger) is negative, will return the value.
     * @return Returns the value of the RightTrigger scaled from 0 to 1.
     */
    public static double getPrimaryRightTrigger()
    {
        double triggerValue = primaryXboxController.getRawAxis(3);
        if (triggerValue < 0)
        {
            return Math.abs(deadzone(triggerValue));
        }
        else
        {
            return 0;
        }
    }
    
    /**
     * If the RawAxis(3) (the trigger) is positive, will return the value.
     * @return Returns the value of the RightTrigger scaled from 0 to 1.
     */
    public static double getPrimaryLeftTrigger()
    {
        double triggerValue = primaryXboxController.getRawAxis(3);
        if (triggerValue > 0)
        {
            return deadzone(triggerValue);
        }
        else
        {
            return 0;
        }
    }
    
    /**
     * If the RawAxis(3) (the trigger) is positive, will return the value.
     * @return Returns the value of the RightTrigger scaled from 0 to 1.
     */
    public static double getSecondaryRightTrigger()
    {
        if (secondaryXboxController.getRawAxis(3) > 0)
        {
            return deadzone(secondaryXboxController.getRawAxis(3));
        }
        else
        {
            return 0;
        }
    }
    
    /**
     * If the RawAxis(3) (the trigger) is negative, will return the value.
     * @return Returns the value of the RightTrigger scaled from 0 to 1.
     */
    public static double getSecondaryLeftTrigger()
    {
        if (secondaryXboxController.getRawAxis(3) < 0)
        {
            return deadzone(Math.abs(secondaryXboxController.getRawAxis(3)));
        }
        else
        {
            return 0;
        }
    }
}
