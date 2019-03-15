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
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.moveArm;


public class armSubsystem extends PIDSubsystem {
  CANSparkMax armMotor;
  CANPIDController m_pidController;
  public double setPoint = 7.67;
  private double P=1;
  private double I=0;
  private double D=0;
  //setpoints
  public double rocket1 = 10.8;
  public double rocket2 = 27.13;          
  public armSubsystem() {
    super("arm", 0,0,0);
    armMotor = new CANSparkMax(RobotMap.armMotor, MotorType.kBrushless);
    m_pidController = armMotor.getPIDController();
    armMotor.getEncoder().setPosition(0);
    m_pidController.setP(P);
    m_pidController.setI(I);
    m_pidController.setD(D);
    m_pidController.setOutputRange(-.25, .25);
    SmartDashboard.putNumber("ARM Set", setPoint);
    SmartDashboard.putNumber("ARM P",m_pidController.getP());
    SmartDashboard.putNumber("ARM P",m_pidController.getP());
    SmartDashboard.putNumber("ARM P",m_pidController.getP());
  }
  public void setpoint(double set)
  {
    setPoint = set;
  }
  public void set()
  {
    /*if(Math.abs(speed) <.1)
    {
      speed = -.1;
    }
    armMotor.set(speed);
    System.out.println(armMotor.getEncoder().getPosition());*/
    Double p=SmartDashboard.getNumber("ARM P",P);
    Double i=SmartDashboard.getNumber("ARM I",I);
    Double d=SmartDashboard.getNumber("ARM D",D);
    if(p!=P)
    {
      P=p;
      m_pidController.setP(p);
    }
    if(i!=I)
    {
      I=i;
      m_pidController.setP(p);
    }
    if(d!=D)
    {
      D=d;
      m_pidController.setP(p);
    }
    m_pidController.setReference(setPoint, ControlType.kPosition);
    //System.out.println(setPoint);
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
