/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  //PWM motors
  public static int RightMotor = 1;//victor and talon
  public static int LeftMotor = 0;//talon and victor
  public static int intakeMotor = 2;//sparks
  //CAN motors
  public static int armMotor = 0;//spark max
  public static int clawMotor = 0;//talon srx
  public static int wristMotor = 0;//victor spx
  //joysticks
  public static int xboxController = 0;
  public static int logitech = 1;
}
