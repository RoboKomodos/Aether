/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.MoveWrist;

/**
 * Add your docs here.
 */
public class wristSubsystem extends Subsystem {
  VictorSPX wristMotor = new VictorSPX(RobotMap.wristMotor);
  public double speed = 1;

  public wristSubsystem()
  {
    SmartDashboard.putNumber("Wrist Speed", speed);
  }
  
  public void set(double multiplier)
  {
    speed = SmartDashboard.getNumber("Wrist Speed", speed);
    wristMotor.set(ControlMode.PercentOutput,speed*multiplier);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new MoveWrist());
  }
}
