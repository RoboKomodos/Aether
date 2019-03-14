/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.VideoCamera;
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

  public static OI m_oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  public static driveSubsystem m_drive;
  public static armSubsystem m_arm;
  public static wristSubsystem m_wrist;
  public static intakeSubsystem m_intake;
  public static clawSubsystem m_claw;
  VideoCamera cam1;
  VideoCamera cam2;
  CvSink cv1;
  CvSink cv2;
  CvSource outputStream;
  Mat image = new Mat();
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    CameraServer.getInstance();
    cam1 = CameraServer.getInstance().startAutomaticCapture(0);
    cam1.setResolution(640, 480);
    cam1.setFPS(30);
    cam2 = CameraServer.getInstance().startAutomaticCapture(1);
    cam2.setResolution(640, 480);
    cam2.setFPS(30);
    cv1 = CameraServer.getInstance().getVideo(cam1);
    cv2 = CameraServer.getInstance().getVideo(cam2);
    outputStream = CameraServer.getInstance().putVideo("Switcher", 320, 240);
    m_oi = new OI();
    m_arm = new armSubsystem();
    m_drive = new driveSubsystem();
    m_wrist = new wristSubsystem();
    m_claw = new clawSubsystem();
    m_intake = new intakeSubsystem();
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
    if(selectedCamera)
    {
      cv1.setEnabled(false);
      cv2.setEnabled(true);
      cv2.grabFrame(image);
    }else
    {
      cv2.setEnabled(false);
      cv1.setEnabled(true);
      cv1.grabFrame(image);
    }
    outputStream.putFrame(image);
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
    Scheduler.getInstance().run();
    m_oi.periodic();
    m_claw.set(m_oi.isXboxButtonPressed(xboxMap.y)?1:m_oi.isXboxButtonPressed(xboxMap.a)?-1:0);
    m_intake.set(m_oi.isXboxButtonPressed(xboxMap.lb)?1:m_oi.isXboxButtonPressed(xboxMap.rb)?-1:0);
    m_arm.setpoint(m_oi.isXboxButtonPressed(xboxMap.x)?m_arm.rocket1:m_oi.isXboxButtonPressed(xboxMap.b)?m_arm.rocket2:m_arm.setPoint);
    m_drive.percent = m_oi.isXboxRTPressed()?.6:1;
    if (m_oi.logitechController.getRawButton(1)&&!cameraPressed)
    {
      cameraPressed = true;
      selectedCamera ^= true;
    }
    else
    {
      cameraPressed = false;
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
