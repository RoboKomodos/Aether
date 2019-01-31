/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class drive extends Subsystem {
  public static Victor leftVictor = new Victor(RobotMap.LeftVictorMotor);
  public static Victor rightVictor = new Victor(RobotMap.RightVictorMotor);
  public void setRightMotor(double speed)
  {
    if (speed > 1)speed =1;
    else if (speed<1)speed =1;
    rightVictor.set((double)speed);
  }
  public void setLeftMotor(double speed)
  {
    if (speed > 1)speed =1;
    else if (speed<1)speed =1;
    leftVictor.set((double)speed);
  }
  public void arcadeDrive(double x, double y)
  {
    setLeftMotor(x+y);
    setRightMotor(y-x);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
