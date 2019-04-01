/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.opencv.core.Mat;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
public class Robot extends TimedRobot {
  
  private boolean cameraPressed = false;
  private boolean selectedCamera = false;

  private static boolean clawLimitPressed=false;
  private static boolean rampedPressed = false;

  private static boolean mikhailPressed = false;
  
  public static double clawConstant =.15; 
  public static double clawIdle=clawConstant;

  public static OI m_oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  public static driveSubsystem m_drive;
  public static armSubsystem m_arm;
  public static wristSubsystem m_wrist;
  public static intakeSubsystem m_intake;
  public static clawSubsystem m_claw;
  public static double liftHeight=-85;
  VideoCamera cam1;
  VideoCamera cam2;
  //VideoCamera cam2;
  //VideoSink server;
  //Mat image = new Mat();
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    SmartDashboard.putNumber("lift height", liftHeight);
    SmartDashboard.putNumber("claw constant", clawConstant);
    //server=CameraServer.getInstance().getServer();
    cam1 = CameraServer.getInstance().startAutomaticCapture(0);
    cam1.setResolution(200,320);
    cam1.setFPS(30);
    cam2 = CameraServer.getInstance().startAutomaticCapture(1);
    cam2.setResolution(200,320);
    cam2.setFPS(30);
    //server.setSource(cam1);
    //CameraServer.getInstance().startAutomaticCapture();
    m_oi = new OI();
    m_arm = new armSubsystem();
    m_drive = new driveSubsystem();
    m_wrist = new wristSubsystem();
    m_claw = new clawSubsystem();
    m_intake = new intakeSubsystem();
    liftHeight = SmartDashboard.getNumber("lift height", liftHeight);
    SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();
    teleopInit();
    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    teleopPeriodic();
  }

  @Override
  public void teleopInit() {
    m_arm.setpoint(0);
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    clawConstant = SmartDashboard.getNumber("claw constant", clawConstant);
    Scheduler.getInstance().run();
    m_oi.periodic();
    m_claw.set(m_oi.isXboxButtonPressed(xboxMap.y)?1:m_oi.isXboxButtonPressed(xboxMap.a)?-1:clawIdle);
    m_intake.set(m_oi.isXboxButtonPressed(xboxMap.lb)?1:m_oi.isXboxButtonPressed(xboxMap.rb)?-1:0);
    //drive percent .4
    if (!m_oi.isXboxRTPressed())
    {
      mikhailPressed = false;
    }
    else if(!mikhailPressed)
    {
      mikhailPressed=true;
      if(m_drive.percent == 1)
      {
        m_drive.percent = .4;
      }
      else{
        m_drive.percent = 1;
      }
    }
    if (m_oi.logitechController.getRawButton(1))
    {
      cameraPressed = true;
      if(!cameraPressed)
      {
        selectedCamera ^= true;
        if(selectedCamera)
        {
          //server.setSource(cam1);
        }
        else
        {
          //server.setSource(cam2);
        }
      }
    }
    else
    {
      cameraPressed = false;
    }

    if(m_oi.isXboxButtonPressed(xboxMap.lb))
    {
      m_arm.setpoint(liftHeight);
    }
    else if(m_oi.isXboxButtonPressed(xboxMap.rb))
    {
      m_arm.setpoint(0);
    }

    if (!m_oi.isXboxLTPressed())
    {
      clawLimitPressed = false;
    }
    else if(clawLimitPressed == false)
    {
      clawLimitPressed = true;
      if (clawIdle==0)
      {
        clawIdle = clawConstant;
      }
      else{
        clawIdle = 0;
      }
    }
    if(m_oi.xboxController.getRawButtonPressed(8))
    {
      rampedPressed = true;
      m_drive.ramped ^=true;
    }
    System.out.println(m_drive.ramped);
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
