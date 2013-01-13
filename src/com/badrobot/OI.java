
package com.badrobot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI 
{
    public static boolean CONSOLE_OUTPUT_ENABLED = true;
    
    public static Joystick primaryXboxController,
                            secondaryXboxController;
    
    private static int LEFT_STICK_X = 1, LEFT_STICK_Y = 2, RIGHT_STICK_X = 3, RIGHT_STICK_Y = 5;
    private static int PRIMARY_JOY = 1, SECONDARY_JOY = 2;
    
    public void init()
    {
        primaryXboxController = new Joystick(PRIMARY_JOY);
        secondaryXboxController = new Joystick(SECONDARY_JOY);        
    }
    
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    
    public static final double DEADZONE_MAGIC_NUMBER = .2;
    
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
        return d / Math.abs(d) * ((Math.abs(d) - .10) / .90);
    }
    
    public static double getPrimaryControllerLeftStickY()
    {
       return primaryXboxController.getRawAxis(LEFT_STICK_Y);
    }
    
    public static double getPrimaryControllerLeftStickX()
    {
        return primaryXboxController.getRawAxis(LEFT_STICK_X);
    }
    
    public static double getPrimaryControllerRightStickX()
    {
        return primaryXboxController.getRawAxis(RIGHT_STICK_X);
    }
    
    public static double getPrimaryControllerRightStickY()
    {
        return primaryXboxController.getRawAxis(RIGHT_STICK_Y);
    }
   
}

