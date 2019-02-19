/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class clawSubsystem extends Subsystem {
  TalonSRX clawMotor = new TalonSRX(RobotMap.wristMotor);
  public double speed = .25;

  public clawSubsystem()
  {
    SmartDashboard.putNumber("Claw Speed", speed);
  }
  
  public void set(double multiplier)
  {
    speed = SmartDashboard.getNumber("Claw Speed", speed);
    clawMotor.set(ControlMode.PercentOutput,speed*multiplier);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
