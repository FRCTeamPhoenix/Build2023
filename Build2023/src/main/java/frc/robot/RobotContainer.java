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
    SmartDashboard.putData("IntakeControl: speed", new IntakeControl(0, m_intake));
    SmartDashboard.putData("ArmRetract", new ArmRetract( m_arm ));
    SmartDashboard.putData("ArmExtend", new ArmExtend( m_arm ));
    SmartDashboard.putData("ArmRaise: b_raise", new ArmRaise(false, m_arm));
    SmartDashboard.putData("ArmLower", new ArmLower( m_arm ));
    SmartDashboard.putData("ResetArm", new ResetArm( m_arm ));
    SmartDashboard.putData("ArmStop", new ArmStop( m_arm ));
    SmartDashboard.putData("ExtendToPosition: position", new ExtendToPosition(0, m_arm));
    SmartDashboard.putData("LowerToPosition: position", new LowerToPosition(0, m_arm));
    SmartDashboard.putData("startPosition", new startPosition( m_arm ));
    SmartDashboard.putData("OverrideEncoder", new OverrideEncoder( m_arm ));
    SmartDashboard.putData("ScorePosition", new ScorePosition( m_arm ));
    SmartDashboard.putData("CubePosition", new CubePosition( m_arm ));
    SmartDashboard.putData("AutoScoreLow: position", new AutoScoreLow(-257, m_arm, m_intake, m_driveTrain));
    SmartDashboard.putData("driveDistance: initial", new DriveDistance(0, 0, m_driveTrain));
    SmartDashboard.putData("AutoCone", new AutoCone(-145, m_arm, m_intake, m_driveTrain));
    SmartDashboard.putData("AutoCube", new AutoCube(-145, m_arm, m_intake, m_driveTrain));
    SmartDashboard.putData("AutoCubeHigh", new AutoCubeHigh(-75, m_arm, m_intake, m_driveTrain));
    SmartDashboard.putData("AutoScoreLowCharge", new AutoScoreLowCharge(-257, m_arm, m_intake, m_driveTrain, m_gyro));
    SmartDashboard.putData("AutoConeCharge", new AutoConeCharge(-145, m_arm, m_intake, m_driveTrain, m_gyro));
    SmartDashboard.putData("AutoCubeCharge", new AutoCubeCharge(-145, m_arm, m_intake, m_driveTrain, m_gyro));
    SmartDashboard.putData("AutoCubeHighCharge", new AutoCubeHighCharge(-75, m_arm, m_intake, m_driveTrain, m_gyro));
    SmartDashboard.putData("CubeHigh", new CubeHigh( m_intake ));
    SmartDashboard.putData("Charge", new Charge(m_gyro, m_driveTrain));

    SmartDashboard.putData("OldScoreLowCharge", new OldScoreLowCharge(-257, m_arm, m_intake, m_driveTrain, m_gyro));
    SmartDashboard.putData("OldConeCharge", new OldConeCharge(-145, m_arm, m_intake, m_driveTrain, m_gyro));
    SmartDashboard.putData("OldCubeCharge", new OldCubeCharge(-145, m_arm, m_intake, m_driveTrain, m_gyro));
    SmartDashboard.putData("OldCubeHighCharge", new OldCubeHighCharge(-75, m_arm, m_intake, m_driveTrain, m_gyro));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND
    m_arm.setDefaultCommand(new ArmStop( m_arm ));
    m_intake.setDefaultCommand(new IntakeControl(0, m_intake));
    m_driveTrain.setDefaultCommand(new ArcadeDrive(() -> getxbox_driver().getLeftY(), () -> getxbox_driver().getRightX(), m_driveTrain));


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND
    
    // Configure autonomous sendable chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
    m_chooser.addOption("AutoScoreLow: position", new AutoScoreLow(-257, m_arm, m_intake, m_driveTrain));
    m_chooser.addOption("AutoCone", new AutoCone(-145, m_arm, m_intake, m_driveTrain));
    m_chooser.addOption("AutoCube", new AutoCube(-145, m_arm, m_intake, m_driveTrain));
    m_chooser.addOption("AutoCubeHigh", new AutoCubeHigh(-75, m_arm, m_intake, m_driveTrain));
    m_chooser.addOption("AutoScoreLowCharge", new AutoScoreLowCharge(-257, m_arm, m_intake, m_driveTrain, m_gyro));
    m_chooser.addOption("AutoConeCharge", new AutoConeCharge(-145, m_arm, m_intake, m_driveTrain, m_gyro));
    m_chooser.addOption("AutoCubeCharge", new AutoCubeCharge(-145, m_arm, m_intake, m_driveTrain, m_gyro));
    m_chooser.addOption("AutoCubeHighCharge", new AutoCubeHighCharge(-75, m_arm, m_intake, m_driveTrain, m_gyro));
    m_chooser.addOption("OldScoreLowCharge", new OldScoreLowCharge(-257, m_arm, m_intake, m_driveTrain, m_gyro));
    m_chooser.addOption("OldConeCharge", new OldConeCharge(-145, m_arm, m_intake, m_driveTrain, m_gyro));
    m_chooser.addOption("OldCubeCharge", new OldCubeCharge(-145, m_arm, m_intake, m_driveTrain, m_gyro));
    m_chooser.addOption("OldCubeHighCharge", new OldCubeHighCharge(-75, m_arm, m_intake, m_driveTrain, m_gyro));
    // m_chooser.setDefaultOption("$command.getName()", new ${name.replace(' ', '')}( m_${name.substring(0,1).toLowerCase()}${name.substring(1).replace(' ', '')} ));

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
final JoystickButton scoreCubeHigh = new JoystickButton(xbox_operator, XboxController.Button.kRightStick.value);        
scoreCubeHigh.whileTrue(new CubeHigh( m_intake ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        SmartDashboard.putData("ScoreCubeHigh",new CubeHigh( m_intake ));
                        
final JoystickButton btn_lower = new JoystickButton(xbox_operator, XboxController.Button.kA.value);        
btn_lower.whileTrue(new ArmLower( m_arm ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
final JoystickButton btn_lift = new JoystickButton(xbox_operator, XboxController.Button.kY.value);        
btn_lift.whileTrue(new ArmRaise(true, m_arm).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
final JoystickButton btn_retract = new JoystickButton(xbox_operator, XboxController.Button.kX.value);        
btn_retract.whileTrue(new ArmRetract( m_arm ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
final JoystickButton btn_extend = new JoystickButton(xbox_operator, XboxController.Button.kB.value);        
btn_extend.whileTrue(new ArmExtend( m_arm ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
final JoystickButton btn_eject = new JoystickButton(xbox_operator, XboxController.Button.kRightBumper.value);        
btn_eject.whileTrue(new IntakeControl(-1, m_intake).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        SmartDashboard.putData("btn_eject",new IntakeControl(-1, m_intake));
                        
final JoystickButton btn_take = new JoystickButton(xbox_operator, XboxController.Button.kLeftBumper.value);        
btn_take.whileTrue(new IntakeControl(0.5, m_intake).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        SmartDashboard.putData("btn_take",new IntakeControl(1, m_intake));
                        
final JoystickButton balance = new JoystickButton(xbox_driver, XboxController.Button.kB.value);        
balance.whileTrue(new Charge(m_gyro, m_driveTrain).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        SmartDashboard.putData("Balance",new Charge(m_gyro, m_driveTrain));
                        
final JoystickButton btn_turnLeft = new JoystickButton(xbox_driver, XboxController.Button.kLeftBumper.value);        
btn_turnLeft.onTrue(new TurnDegreesPID(90, m_driveTrain, m_gyro).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        SmartDashboard.putData("btn_turnLeft",new TurnDegreesPID(90, m_driveTrain, m_gyro));

final JoystickButton btn_turnRight = new JoystickButton(xbox_driver, XboxController.Button.kRightBumper.value);        
btn_turnRight.onTrue(new TurnDegreesPID(-90, m_driveTrain, m_gyro).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        SmartDashboard.putData("btn_turnRight",new TurnDegreesPID(-90, m_driveTrain, m_gyro));

final JoystickButton btn_midScore = new JoystickButton(xbox_operator, XboxController.Button.kLeftStick.value);        
btn_midScore.whileTrue(new ArmMove(-145, 50, m_arm).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        SmartDashboard.putData("btn_midScore",new ArmMove(-145, 50, m_arm));

final JoystickButton btn_highCube = new JoystickButton(xbox_operator, XboxController.Button.kRightStick.value);        
btn_highCube.whileTrue(new ArmMove(-75, 50, m_arm).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        SmartDashboard.putData("btn_highCube",new ArmMove(-75, 50, m_arm));

SmartDashboard.putData("TurnDegreesPIDRight",new TurnDegreesPID(90, m_driveTrain, m_gyro));
SmartDashboard.putData("TurnDegreesPIDLeft",new TurnDegreesPID(-90, m_driveTrain, m_gyro));
SmartDashboard.putData("TurnDegreesPIDSpinBehind",new TurnDegreesPID(180, m_driveTrain, m_gyro));

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
  public Intake getIntake() {
    return m_intake;
  }
  public Arm getArm(){
    return m_arm;
  }
}

