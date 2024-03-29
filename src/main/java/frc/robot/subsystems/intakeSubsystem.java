/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class intakeSubsystem extends Subsystem {
  Spark intakeMotor = new Spark(RobotMap.intakeMotor);
  public double speed = .35;

  public intakeSubsystem()
  {
    SmartDashboard.putNumber("Intake Speed", speed);
  }

  public void set(double multiplier)
  {
    speed = SmartDashboard.getNumber("Intake Speed", speed);
    intakeMotor.set(-speed*multiplier);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
