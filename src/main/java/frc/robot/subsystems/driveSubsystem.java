/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;
import frc.robot.commands.MoveRobot;

/**
 * Add your docs here.
 */
public class driveSubsystem extends PIDSubsystem {
  Victor left = new Victor(RobotMap.LeftMotor);
  Victor right = new Victor(RobotMap.RightMotor);
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

    // Default is to manually drive robot with sticks
    setDefaultCommand(new MoveRobot());
  }

  public boolean initMotors() {
    ErrorCode error = ErrorCode.OK;

    /**
     * Config the allowable closed-loop error, Closed-Loop output will be
     * neutral within this range. See Table here for units to use: 
     * https://github.com/CrossTheRoadElec/Phoenix-Documentation#what-are-the-units-of-my-sensor
     */
    
    error = left.configAllowableClosedloopError(0, 0, Constants.kTimeoutMs);
    if (error == ErrorCode.OK){
      error = right.configAllowableClosedloopError(0, 0, Constants.kTimeoutMs);
    }

    /* Config closed loop gains for Primary closed loop (Current) */
    // TODO: Check error codes
    leftMaster.config_kP(0, Constants.kP, Constants.kTimeoutMs);
    leftMaster.config_kI(0, Constants.kI, Constants.kTimeoutMs);
    leftMaster.config_kD(0, Constants.kD, Constants.kTimeoutMs);
    leftMaster.config_kF(0, Constants.kF, Constants.kTimeoutMs);
    
    rightMaster.config_kP(0, Constants.kP, Constants.kTimeoutMs);
    rightMaster.config_kI(0, Constants.kI, Constants.kTimeoutMs);
    rightMaster.config_kD(0, Constants.kD, Constants.kTimeoutMs);
    rightMaster.config_kF(0, Constants.kF, Constants.kTimeoutMs);

    // Initalizes encoders
    // TODO: Check error codes
    leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.kTimeoutMs);
    rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.kTimeoutMs);

    // Ensures motor output and encoder velocity are prorightional to each other
    // If they become inverted, set these to true
    leftMaster.setSensorPhase(false);
    rightMaster.setSensorPhase(false);

    // Zeroes encoders
    // TODO: Check error codes
    leftMaster.setSelectedSensorPosition(0, 0, Constants.kTimeoutMs);
    rightMaster.setSelectedSensorPosition(0, 0, Constants.kTimeoutMs);

    return (error == ErrorCode.OK);
  }

  /** Drive using two talons and a joystick **/
  public void stickDrive(Joystick driverStick){
    double thro = driverStick.getRawAxis(1); //Populate the double thro with the raw axis 1
    double yaw = driverStick.getRawAxis(2); //Populate the double yaw with the raw axis 2
    left.set((thro) - yaw); // Drive normally
    right.set(thro - yaw); // Drive normally
  }
}
