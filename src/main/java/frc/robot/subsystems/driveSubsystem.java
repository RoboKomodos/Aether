/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;
import frc.robot.constants;
import frc.robot.commands.MoveRobot;

/**
 * Add your docs here.
 */
public class driveSubsystem extends PIDSubsystem {
  
  Victor leftDrive;
  Victor rightDrive;
  public driveSubsystem() {
    super("drive", constants.KP, constants.KI, constants.KD);
    getPIDController().setAbsoluteTolerance(constants.KTolerance);
    leftDrive = new Victor(RobotMap.LeftMotor);
    rightDrive = new Victor(RobotMap.RightMotor);

  }

  public void set(double left,double right)
  {
      leftDrive.set(-left);
      rightDrive.set(right);
  }

  public void arcadeDrive(double x, double y,double percent)
  {
    set(percent*(y+x), percent*(y-x));
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
