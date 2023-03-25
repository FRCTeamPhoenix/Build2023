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


import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax.ControlType;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class Arm extends SubsystemBase {
    //private boolean doneResetting = false;
    private SparkMaxPIDController m_lift_pidController, m_extend_pidController;
    private RelativeEncoder m_lift_encoder, m_extend_encoder;
    public double kP_lift, kI_lift, kD_lift, kIz_lift, kFF_lift, kMaxOutput_lift, kMinOutput_lift, maxRPM_lift, kP_extend, kI_extend, kD_extend, kIz_extend, kFF_extend, kMaxOutput_extend, kMinOutput_extend, maxRPM_extend;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private CANSparkMax sm_motor_lift;
private CANSparkMax sm_motor_extend;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final double lowerMaximum = -145;
    private final double extendMaximum = 45;
    /**
    *
    */
    public Arm() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
sm_motor_lift = new CANSparkMax(24, MotorType.kBrushless);
 
 sm_motor_lift.restoreFactoryDefaults();  
sm_motor_lift.setInverted(true);
sm_motor_lift.setIdleMode(IdleMode.kBrake);
sm_motor_lift.burnFlash();
  

sm_motor_extend = new CANSparkMax(23, MotorType.kBrushless);
 
 sm_motor_extend.restoreFactoryDefaults();  
sm_motor_extend.setInverted(false);
sm_motor_extend.setIdleMode(IdleMode.kBrake);
sm_motor_extend.burnFlash();
  


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    m_lift_pidController = sm_motor_lift.getPIDController();

    // Encoder object created to display position values
    m_lift_encoder = sm_motor_lift.getEncoder();


    // PID coefficients
    kP_lift = 1.2e-4; 
    kI_lift = 0;
    kD_lift = 0; 
    kIz_lift = 0; 
    kFF_lift = 0.0003; 
    kMaxOutput_lift = 1; 
    kMinOutput_lift = -1;
    maxRPM_lift = 6500;

    // set PID coefficients
    m_lift_pidController.setP(kP_lift);
    m_lift_pidController.setI(kI_lift);
    m_lift_pidController.setD(kD_lift);
    m_lift_pidController.setIZone(kIz_lift);
    m_lift_pidController.setFF(kFF_lift);
    m_lift_pidController.setOutputRange(kMinOutput_lift, kMaxOutput_lift);

    //Extend
    m_extend_pidController = sm_motor_extend.getPIDController();

    // Encoder object created to display position values
    m_extend_encoder = sm_motor_extend.getEncoder();

    // PID coefficients
    kP_extend = 1.2e-4; 
    kI_extend = 0;
    kD_extend = 0; 
    kIz_extend = 0; 
    kFF_extend = 0.0003; 
    kMaxOutput_extend = 1; 
    kMinOutput_extend = -1;
    maxRPM_extend = 6500;

    // set PID coefficients
    m_extend_pidController.setP(kP_extend);
    m_extend_pidController.setI(kI_extend);
    m_extend_pidController.setD(kD_extend);
    m_extend_pidController.setIZone(kIz_extend);
    m_extend_pidController.setFF(kFF_extend);
    m_extend_pidController.setOutputRange(kMinOutput_extend, kMaxOutput_extend);
    }


    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("SpeedLift", m_lift_encoder.getVelocity());
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    /*public void extend(double intakeSpeed) {
        mg_.set(intakeSpeed);
    }
    public void retract(double intakeSpeed) {
        mg_intake.set(intakeSpeed);
    }
    public void (double intakeSpeed) {
        mg_intake.set(intakeSpeed);
    }*/

    public void upDown(double speed){
        double setPoint = speed*maxRPM_lift;
        if (speed < 0 && m_lift_encoder.getPosition() > lowerMaximum) {
            m_lift_pidController.setReference(setPoint, CANSparkMax.ControlType.kVelocity);
        } else if (speed > 0 && m_lift_encoder.getPosition() < 0) {
            m_lift_pidController.setReference(setPoint, CANSparkMax.ControlType.kVelocity);
        } else {
            sm_motor_lift.stopMotor();
        }
        // if (m_lift_encoder.getPosition() >= lowerMaximum && m_lift_encoder.getPosition() <= 0) {
        //     m_lift_pidController.setReference(setPoint, CANSparkMax.ControlType.kVelocity);
        // } else {
        //     sm_motor_lift.stopMotor();
        // }
    
        SmartDashboard.putNumber("SetPointLift", setPoint);
        SmartDashboard.putNumber("SpeedLift", m_lift_encoder.getVelocity());
        SmartDashboard.putNumber("LiftPosition", m_lift_encoder.getPosition());
        SmartDashboard.putNumber("LiftCurrent", sm_motor_lift.getOutputCurrent());
    }
    //public void reset() {
        //if(!doneResetting) {
            //upDown(-1);
        //}
    //}
    // public boolean atTop () {
    //     if (sm_motor_lift.getOutputCurrent() > liftLimitSwitch) {
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }
    // public boolean retracted () {
    //     if (sm_motor_extend.getOutputCurrent() > retractLimitSwitch) {
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }
    public void inOut(double speed){
        double setPoint = speed*maxRPM_extend;
        if (speed > 0 && m_extend_encoder.getPosition() < extendMaximum) {
            m_extend_pidController.setReference(setPoint, CANSparkMax.ControlType.kVelocity);
        } else if (speed < 0 && m_extend_encoder.getPosition() > 0) {
            m_extend_pidController.setReference(setPoint, CANSparkMax.ControlType.kVelocity);
        } else {
            sm_motor_extend.stopMotor();
        }
        // if (m_extend_encoder.getPosition() <= extendMaximum && m_extend_encoder.getPosition() >= 0) {
        //     m_extend_pidController.setReference(setPoint, CANSparkMax.ControlType.kVelocity);
        // } else {
        //     sm_motor_extend.stopMotor();
        // }
        SmartDashboard.putNumber("SetPointExtend", setPoint);
        SmartDashboard.putNumber("SpeedExtend", m_extend_encoder.getVelocity());
        SmartDashboard.putNumber("ExtendPosition", m_extend_encoder.getPosition());
        SmartDashboard.putNumber("Retract Current", sm_motor_extend.getOutputCurrent());
    }
    public void setLiftEncoder() {
        m_lift_encoder.setPosition(0);
    }
    public void setRetractEncoder() {
        m_extend_encoder.setPosition(0);
    }

    public boolean setStartPosition() {
        if (m_lift_encoder.getPosition() <= 0) {
            upDown(1);
        }
        if (m_extend_encoder.getPosition() >= 0) {
            inOut(-0.25);
        }
        if (m_lift_encoder.getPosition() >= -10 && m_extend_encoder.getPosition() <= 3) {
            return true;
        } else {
            return false;
        }
    }

    public void overrideEncoders() {
        m_lift_encoder.setPosition(-1000);
        m_extend_encoder.setPosition(1000);
    }

    public boolean liftToScorePosition() {
        // if (m_lift_encoder.getPosition() > -96) {
        //     upDown(-1);
        // } if (m_lift_encoder.getPosition() < -96) {
        //     upDown(1);
        if (m_extend_encoder.getPosition() < extendMaximum) {
            inOut(1);
        }
        if (m_extend_encoder.getPosition() >= extendMaximum) {
            return true;
        } else {
            return false;
        }
    }

    public boolean toCubePosition() {
        if (m_lift_encoder.getPosition() > lowerMaximum) {
            upDown(-1);
        } else if (m_extend_encoder.getPosition() > 0) {
            inOut(-1);
        }
        if (m_lift_encoder.getPosition() <= lowerMaximum && m_extend_encoder.getPosition() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean armToPosition (double d) {
        m_lift_pidController.setSmartMotionMaxVelocity(20000, 0);
        m_lift_pidController.setSmartMotionMinOutputVelocity(0, 0);
        m_lift_pidController.setSmartMotionMaxAccel(15000, 0);
        m_lift_pidController.setSmartMotionAllowedClosedLoopError(1.0, 0);
        m_lift_pidController.setReference(d, ControlType.kSmartMotion);
        double processVariable = m_lift_encoder.getPosition();
        
        if ((Math.abs(m_lift_encoder.getPosition() - d) <= 1)) { 
            return true;
        } else {
            return false;
        }
    }

    public boolean outToPosition(double d) {
        m_extend_pidController.setSmartMotionMaxVelocity(2000,0);
        m_extend_pidController.setSmartMotionMinOutputVelocity(0,0);
        m_extend_pidController.setSmartMotionMaxAccel(1500,0);
        m_extend_pidController.setSmartMotionAllowedClosedLoopError(1.0, 0);
        m_extend_pidController.setReference(d, ControlType.kSmartMotion);
        
        if (Math.abs(m_extend_encoder.getPosition() - d) <= 1) {
            return true;
        } else {
            return false;
        }
    }

    //Added this function to be called by parallel commands to make sure the
    //individual functions that require the same subsystem dont conflict.
    public boolean Move(double liftPosition, double extendPosition){
        boolean downPosition = false;
        boolean outPosition = false;
        boolean inPosition = false;

        if (armToPosition(liftPosition)) {
            downPosition = true;
        }

        if (outToPosition(extendPosition)) {
            outPosition = true;
        }

        if (downPosition && outPosition) {
            inPosition = true;
        }

        return inPosition;
    }
}

