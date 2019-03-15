/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  Joystick xboxController = new Joystick(RobotMap.xboxController);
  public Joystick logitechController = new Joystick(RobotMap.logitech);
  public double xboxDeadzone = .15;
  public double logitechDeadZone = .1;
  public OI()
  {
    SmartDashboard.putNumber("Xbox Dead Zone", xboxDeadzone);
    SmartDashboard.putNumber("Logitech Dead Zone", logitechDeadZone);
  }
  public void periodic()
  {
    xboxDeadzone = SmartDashboard.getNumber("Xbox Dead Zone", xboxDeadzone);
    logitechDeadZone = SmartDashboard.getNumber("Logitech Dead Zone", logitechDeadZone);
  }
  //funtions for getting xbox values
  public double getXboxLeftY()
  {
    double raw = -xboxController.getRawAxis(1);
    return Math.abs(raw)<xboxDeadzone ? 0:raw;
  }
  public double getXboxLeftX()
  {
    double raw = xboxController.getRawAxis(0);
    return Math.abs(raw)<xboxDeadzone ? 0:raw;
  }
  public boolean isXboxLTPressed()
  {
    return xboxController.getRawAxis(2)>.75;
  }
  public boolean isXboxRTPressed()
  {
    return xboxController.getRawAxis(3)>.75;
  }
  public boolean isXboxButtonPressed(xboxMap x)
  {
    return xboxController.getRawButton(x.value);
  }
  //funtions for getting logitech values
  public double getLogitechLeftY()
  {
    double raw = logitechController.getRawAxis(1);
    return Math.abs(raw)<logitechDeadZone ? 0:raw;
  }
}
