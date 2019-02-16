/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * These are all constants defined for the robot.
 * All should be defined as static and final.
 * Please add a note to each constant to explain what it is for. 
 * Also add relevant info such as units.
 */
public class Constants {
    // Timeout in ms for drive motors
    public static final int kTimeoutMs = 30;

    // P constant for drive motors PID
    public static double kP = 0.05;
    // I constant for drive motors PID
    public static double kI = 0.0;
    // D constant for drive motors PID
    public static double kD = 0.0;
    // F constant for drive motors PID
    public static double kF = 0.03103;

    // Speed dampener for DriveTrain while driving
    public static final int speedDampener = 1;

    // Top position of lift for encoder
    // TODO define these values
    public static final int lift_default = 0;
    public static final int lift_top = 0;
    public static final int rocket_low = 0;
    public static final int rocket_middle = 0;
    public static final int rocket_high = 0;
    public static final int cargo_rocket_low = 0;
    public static final int cargo_rocket_high = 0;
    public static final int cargo_low = 0;
    public static final int cargo_high = 0;

    // P constant for lift motors PID
    public static double lP = 0.05;
    // I constant for lift motors PID
    public static double lI = 0.0;
    // D constant for lift motors PID
    public static double lD = 0.0;
    // F constant for lift motors PID
    public static double lF = 0.03103;


    public enum liftPosition
    {
        ROCKET_LOW, ROCKET_MIDDLE, ROCKET_HIGH, CARGO_ROCKET_LOW, CARGO_ROCKET_HIGH, CARGO_SHIP_LOW, CARGO_SHIP_HIGH, DEFAULT
    }

    public enum liftDirection
    {
        UP, DOWN, NONE
    }

    public enum intakeMode
    {
        BALL_IN, BALL_OUT, HATCH_IN, HATCH_OUT
    }

    // P constant for arm motors PID
    public static double aP = 0.05;
    // I constant for arm motors PID
    public static double aI = 0.0;
    // D constant for arm motors PID
    public static double aD = 0.0;
    // F constant for arm motors PID
    public static double aF = 0.03103;

    public enum armDirection
    {
        ARM_UP, ARM_DOWN, NONE
    }

    public enum armPosition
    {
        HOME, HATCH_OUT, BALL_OUT, BALL_IN, HATCH_IN
    }

    public static final int armHome = 0;
    public static final int armHatchOut = 0;
    public static final int armBallOut = 0;
    public static final int armBallIn = 0;
    public static final int armHatchIn = 0;

    public enum climberDirection
    {
        IN, OUT
    }
}