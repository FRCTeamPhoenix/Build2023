// RobotBuilder Version: 5.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Robot.

package frc.robot;

import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.event.EventLoop;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.ArmMove;
import frc.robot.commands.IntakeControl;
import frc.robot.commands.ResetArm;
import frc.robot.subsystems.Intake;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.VideoSource;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in 
 * the project.
 */
public class Robot extends TimedRobot {

    private Command m_autonomousCommand;

    private RobotContainer m_robotContainer;

    private Intake m_intake;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
        // autonomous chooser on the dashboard.
        m_robotContainer = RobotContainer.getInstance();
        HAL.report(tResourceType.kResourceType_Framework, tInstances.kFramework_RobotBuilder);
        m_intake = m_robotContainer.getIntake();
        CameraServer.startAutomaticCapture(0);
    }

    /**
    * This function is called every robot packet, no matter the mode. Use this for items like
    * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
    *
    * <p>This runs after the mode specific periodic functions, but before
    * LiveWindow and SmartDashboard integrated updating.
    */
    @Override
    public void robotPeriodic() {
        // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands,
        // and running subsystem periodic() methods.  This must be called from the robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
        m_robotContainer.getDriveTrain().getLeftEncoder();
        //Turbo
        if (m_robotContainer.getxbox_driver().getRightTriggerAxis() > 0.01) {
            m_robotContainer.getDriveTrain().setTurbo(true);
        }
        else {
            m_robotContainer.getDriveTrain().setTurbo(false);
        }
        if(m_robotContainer.getxbox_operator().getPOV() == 0) {
            m_robotContainer.getArm().setStartPosition();
        }
        if (m_robotContainer.getxbox_operator().getPOV() == 90) {
            //m_robotContainer.getArm().liftToScorePosition();
            new ArmMove(-58, 50, m_robotContainer.getArm());
            // m_robotContainer.getArm().armToPosition(-75);
            // m_robotContainer.getArm().outToPosition(50);
        }
        if (m_robotContainer.getxbox_operator().getPOV() == 270) {
            new ArmMove(-68, 50, m_robotContainer.getArm());
            // m_robotContainer.getArm().armToPosition(-145);
            // m_robotContainer.getArm().outToPosition(50);
        }
        if (m_robotContainer.getxbox_operator().getPOV() == 180) {
            m_robotContainer.getArm().toCubePosition();
        }
        if (m_robotContainer.getxbox_operator().getRightTriggerAxis() > 0.2) {
            new IntakeControl(-0.4, m_intake);
        }
        else {
            new IntakeControl(0, m_intake);
        }
    }


    /**
    * This function is called once each time the robot enters Disabled mode.
    */
    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    /**
    * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
    */
    @Override
    public void autonomousInit() {
        m_autonomousCommand = m_robotContainer.getAutonomousCommand();

        // schedule the autonomous command (example)
        if (m_autonomousCommand != null) {
            m_autonomousCommand.schedule();
        }
    }

    /**
    * This function is called periodically during autonomous.
    */
    @Override
    public void autonomousPeriodic() {
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
    }

    @Override
    public void testInit() {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();
    }

    /**
    * This function is called periodically during test mode.
    */
    @Override
    public void testPeriodic() {
    }

}
