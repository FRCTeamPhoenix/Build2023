// RobotBuilder Version: 5.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Subsystem.

package frc.robot.subsystems;

import frc.robot.RobotContainer;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX; import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class DriveTrain extends SubsystemBase {
    private boolean m_turbo;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private WPI_TalonFX frontLeft;
private WPI_TalonFX backLeft;
private MotorControllerGroup mg_left;
private WPI_TalonFX frontRight;
private WPI_TalonFX backRight;
private MotorControllerGroup mg_right;
private DifferentialDrive drive;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**
    *
    */
    public DriveTrain() {
        m_turbo = false;
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
frontLeft = new WPI_TalonFX(1);
 
    /* Factory default hardware to prevent unexpected behavior */
frontLeft.configFactoryDefault();

        /* Invert Motor? and set Break Mode */
frontLeft.setInverted(false);
frontLeft.setNeutralMode(NeutralMode.Brake);

        /* Set the peak and nominal outputs */
frontLeft.configNominalOutputForward(0, 30);
frontLeft.configNominalOutputReverse(0, 30);
frontLeft.configPeakOutputForward(1, 30);
frontLeft.configPeakOutputReverse(-1, 30);
        


backLeft = new WPI_TalonFX(3);
 
    /* Factory default hardware to prevent unexpected behavior */
backLeft.configFactoryDefault();

        /* Invert Motor? and set Break Mode */
backLeft.setInverted(false);
backLeft.setNeutralMode(NeutralMode.Brake);

        /* Set the peak and nominal outputs */
backLeft.configNominalOutputForward(0, 30);
backLeft.configNominalOutputReverse(0, 30);
backLeft.configPeakOutputForward(1, 30);
backLeft.configPeakOutputReverse(-1, 30);
        


mg_left = new MotorControllerGroup(backLeft, frontLeft  );
 addChild("mg_left",mg_left);
 

frontRight = new WPI_TalonFX(2);
 
    /* Factory default hardware to prevent unexpected behavior */
frontRight.configFactoryDefault();

        /* Invert Motor? and set Break Mode */
frontRight.setInverted(true);
frontRight.setNeutralMode(NeutralMode.Brake);

        /* Set the peak and nominal outputs */
frontRight.configNominalOutputForward(0, 30);
frontRight.configNominalOutputReverse(0, 30);
frontRight.configPeakOutputForward(1, 30);
frontRight.configPeakOutputReverse(-1, 30);
        


backRight = new WPI_TalonFX(4);
 
    /* Factory default hardware to prevent unexpected behavior */
backRight.configFactoryDefault();

        /* Invert Motor? and set Break Mode */
backRight.setInverted(true);
backRight.setNeutralMode(NeutralMode.Brake);

        /* Set the peak and nominal outputs */
backRight.configNominalOutputForward(0, 30);
backRight.configNominalOutputReverse(0, 30);
backRight.configPeakOutputForward(1, 30);
backRight.configPeakOutputReverse(-1, 30);
        


mg_right = new MotorControllerGroup(frontRight, backRight  );
 addChild("mg_right",mg_right);
 

drive = new DifferentialDrive(mg_left, mg_right);
 addChild("Drive",drive);
 drive.setSafetyEnabled(true);
drive.setExpiration(0.1);
drive.setMaxOutput(1.0);



    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    backRight.follow(frontRight);
    backLeft.follow(frontLeft);
    frontLeft.configClosedloopRamp(.5);
    frontRight.configClosedloopRamp(.5);
    backLeft.configClosedloopRamp(.5);
    backRight.configClosedloopRamp(.5);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }
    public void setTurbo(boolean turbo) {
        m_turbo = turbo;
    }
    public void Drive(double Speed, double Rotation){
        if (!m_turbo) {
            if (Speed > 0.6) {
                Speed = 0.6;
            }
            if (Speed < -0.6) {
                Speed = -0.6;
            }
            if (Rotation > 0.5) {
                Rotation = 0.5;
            }
            if (Rotation < -0.5) {
                Rotation = -0.5;
            }
        }
        drive.arcadeDrive(Speed, Rotation);
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

