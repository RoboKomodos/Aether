/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.io.Console;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;
import frc.robot.commands.moveArm;


public class armSubsystem extends PIDSubsystem {
  CANSparkMax armMotor;
  public armSubsystem() {
    super("arm", 0,0,0);
    armMotor = new CANSparkMax(RobotMap.armMotor, MotorType.kBrushless);

  }

  public void set(double speed)
  {
    if(Math.abs(speed) <.1)
    {
      speed = -.1;
    }
    armMotor.set(speed);
    System.out.println(armMotor.getEncoder().getPosition());
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new moveArm());
  }

  @Override
  protected double returnPIDInput() {
    return armMotor.getEncoder().getPosition();
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
  }
}
