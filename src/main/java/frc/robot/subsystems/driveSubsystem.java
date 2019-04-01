/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.MoveRobot;

/**
 * Add your docs here.
 */
public class driveSubsystem extends PIDSubsystem {
  
  Victor leftDrive;
  Victor rightDrive;
  public double percent=1;
  public double position = 0;
  public double increment = .05;
  public boolean ramped = false;
  public driveSubsystem() {
    super("drive", 0,0,0);
    leftDrive = new Victor(RobotMap.LeftMotor);
    rightDrive = new Victor(RobotMap.RightMotor);
    SmartDashboard.putNumber("Increment", increment);
  }

  public void set(double left,double right)
  {
      leftDrive.set(left);
      rightDrive.set(-right);
  }

  public void arcadeDrive(double x, double y)
  {
    increment = SmartDashboard.getNumber("Increment", increment);
    if(ramped)
    {
      percent =1;
    }
    if(y == 0||percent !=1||!ramped)
    {
      position = y;
    }
    else if (y<position)
    {
      position = y<position-increment?position-increment:y;
    }
    else
    {
      position = y>position+increment?position+increment:y;
    }
    set(percent*(position+x), percent*(position-x));
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new MoveRobot());
  }

  @Override
  protected double returnPIDInput() {
    return 0.0;
  }

  @Override
  protected void usePIDOutput(double output) {
    set(output, -output);
  }
}
