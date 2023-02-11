// RobotBuilder Version: 5.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: RobotContainer.

package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private static RobotContainer m_robotContainer = new RobotContainer();

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
// The robot's subsystems
    public final Gyro m_gyro = new Gyro();
    public final Arm m_arm = new Arm();
    public final Intake m_intake = new Intake();
    public final DriveTrain m_driveTrain = new DriveTrain();

// Joysticks
private final XboxController xbox_operator = new XboxController(1);
private final XboxController xbox_driver = new XboxController(0);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

  
  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
  * The container for the robot.  Contains subsystems, OI devices, and commands.
  */
  private RobotContainer() {
    SmartDashboard.putData("Manual Reset", new ResetArm(m_arm));
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Smartdashboard Subsystems
    SmartDashboard.putData(m_driveTrain);


    // SmartDashboard Buttons
    SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
    SmartDashboard.putData("IntakeControl: speed", new IntakeControl(0, m_intake));
    SmartDashboard.putData("ArmRetract", new ArmRetract( m_arm ));
    SmartDashboard.putData("ArmExtend", new ArmExtend( m_arm ));
    SmartDashboard.putData("ArmRaise", new ArmRaise( m_arm ));
    SmartDashboard.putData("ArmLower", new ArmLower( m_arm ));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND
    m_intake.setDefaultCommand(new IntakeControl(0, m_intake));
    m_driveTrain.setDefaultCommand(new ArcadeDrive(() -> getxbox_driver().getLeftY(), () -> getxbox_driver().getRightX(), m_driveTrain));


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND

    // Configure autonomous sendable chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    m_chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    SmartDashboard.putData("Auto Mode", m_chooser);
  }

  public static RobotContainer getInstance() {
    return m_robotContainer;
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
// Create some buttons
final JoystickButton btn_eject = new JoystickButton(xbox_operator, XboxController.Button.kRightBumper.value);        
btn_eject.whileTrue(new IntakeControl(-1, m_intake).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        SmartDashboard.putData("btn_eject",new IntakeControl(-1, m_intake));
                        
final JoystickButton btn_take = new JoystickButton(xbox_operator, XboxController.Button.kLeftBumper.value);        
btn_take.whileTrue(new IntakeControl(1, m_intake).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        SmartDashboard.putData("btn_take",new IntakeControl(1, m_intake));
                        


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
  }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
public XboxController getxbox_driver() {
      return xbox_driver;
    }

public XboxController getxbox_operator() {
      return xbox_operator;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
  */
  public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
    return m_chooser.getSelected();
  }
  public DriveTrain getDriveTrain() {
    return m_driveTrain;
  }
  public XboxController getXboxControllerDriver() {
    return xbox_driver;
  }
  public XboxController getXboxControllerOperator() {
    return xbox_operator;
  }
  public Intake getIntake() {
    return m_intake;
  }
  public Arm getArm(){
    return m_arm;
  }
}

