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
import edu.wpi.first.math.MathUtil;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

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
        drive.curvatureDrive(Speed, Rotation, !m_turbo);
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    class Constants {
        /**
         * Which PID slot to pull gains from. Starting 2018, you can choose from
         * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
         * configuration.
         */
        public static final int kSlotIdx = 0;
    
        /**
         * Talon FX supports multiple (cascaded) PID loops. For
         * now we just want the primary one.
         */
        public static final int kPIDLoopIdx = 0;
    
        /**
         * Set to zero to skip waiting for confirmation, set to nonzero to wait and
         * report to DS if action fails.
         */
        public static final int kTimeoutMs = 30;
    
        /**
         * PID Gains may have to be adjusted based on the responsiveness of control loop.
         * kF: 1023 represents output value to Talon at 100%, 20660 represents Velocity units at 100% output
         * 
         * 	                                    			  kP   	 kI    kD      kF          Iz    PeakOut */
       //public final Gains kGains_Velocit  = new Gains( 0.1, 0.001, 5, 1023.0/20660.0,  300,  1.00);
       public final Gains kGains_Velocit  = new Gains( 0.03, 0, 0,0.05,  300,  1.00);
        //unity gain is probably 0.06, and kF assumes 40% power gives us 50% max speed (a little better than rough guesses)
        //max speed is 5000 rpm is 17,066 counts per 100ms
    }

    public class Gains {
        public final double kP;
        public final double kI;
        public final double kD;
        public final double kF;
        public final int kIzone;
        public final double kPeakOutput;
        
        public Gains(double _kP, double _kI, double _kD, double _kF, int _kIzone, double _kPeakOutput){
            kP = _kP;
            kI = _kI;
            kD = _kD;
            kF = _kF;
            kIzone = _kIzone;
            kPeakOutput = _kPeakOutput;
        }
    }

     /**
   * Curvature drive method for differential drive platform.
   *
   * <p>The rotation argument controls the curvature of the robot's path rather than its rate of
   * heading change. This makes the robot more controllable at high speeds.
   *
   * @param xSpeed The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
   * @param zRotation The normalized curvature [-1.0..1.0]. Counterclockwise is positive.
   * @param allowTurnInPlace If set, overrides constant-curvature turning for turn-in-place
   *     maneuvers. zRotation will control turning rate instead of curvature.
   */ 
  public void curvatureDrive(double xSpeed, double zRotation, boolean allowTurnInPlace) {
    /*if (!m_reported) {
      HAL.report(
          tResourceType.kResourceType_RobotDrive, tInstances.kRobotDrive2_DifferentialCurvature, 2);
      m_reported = true;
    } */

    xSpeed = MathUtil.applyDeadband(xSpeed, 0.1);
    zRotation = MathUtil.applyDeadband(zRotation, 0.1);

    var speeds = curvatureDriveIK(xSpeed, zRotation, allowTurnInPlace);

    //m_leftMotor.set(speeds.left * m_maxOutput);
    //m_rightMotor.set(speeds.right * m_maxOutput);

    /* Velocity Closed Loop */

        /**
         * Convert 2000 RPM to units / 100ms.
         * 2048 Units/Rev * 2000 RPM / 600 100ms/min in either direction:
         * velocity setpoint is in units/100ms
         */
        double RighttargetVelocity_UnitsPer100ms = speeds.right * 2000.0 * 2048.0 / 600.0;
        /* 2000 RPM in either direction */
        frontRight.set(TalonFXControlMode.Velocity, RighttargetVelocity_UnitsPer100ms);

        double LefttargetVelocity_UnitsPer100ms = speeds.left * 2000.0 * 2048.0 / 600.0;
        /* 2000 RPM in either direction */
        frontLeft.set(TalonFXControlMode.Velocity, LefttargetVelocity_UnitsPer100ms);

    //feed();
  }

  public static class WheelSpeeds {
    public double left;
    public double right;

    /** Constructs a WheelSpeeds with zeroes for left and right speeds. */
    public WheelSpeeds() {}

    /**
     * Constructs a WheelSpeeds.
     *
     * @param left The left speed [-1.0..1.0].
     * @param right The right speed [-1.0..1.0].
     */
    public WheelSpeeds(double left, double right) {
      this.left = left;
      this.right = right;
    }
  }
    /**
   * Curvature drive inverse kinematics for differential drive platform.
   *
   * <p>The rotation argument controls the curvature of the robot's path rather than its rate of
   * heading change. This makes the robot more controllable at high speeds.
   *
   * @param xSpeed The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
   * @param zRotation The normalized curvature [-1.0..1.0]. Counterclockwise is positive.
   * @param allowTurnInPlace If set, overrides constant-curvature turning for turn-in-place
   *     maneuvers. zRotation will control rotation rate around the Z axis instead of curvature.
   * @return Wheel speeds [-1.0..1.0].
   */
  public static WheelSpeeds curvatureDriveIK(
      double xSpeed, double zRotation, boolean allowTurnInPlace) {
    xSpeed = MathUtil.clamp(xSpeed, -1.0, 1.0);
    zRotation = MathUtil.clamp(zRotation, -1.0, 1.0);

    double leftSpeed;
    double rightSpeed;

    if (allowTurnInPlace) {
      leftSpeed = xSpeed - zRotation;
      rightSpeed = xSpeed + zRotation;
    } else {
      leftSpeed = xSpeed - Math.abs(xSpeed) * zRotation;
      rightSpeed = xSpeed + Math.abs(xSpeed) * zRotation;
    }

    // Desaturate wheel speeds
    double maxMagnitude = Math.max(Math.abs(leftSpeed), Math.abs(rightSpeed));
    if (maxMagnitude > 1.0) {
      leftSpeed /= maxMagnitude;
      rightSpeed /= maxMagnitude;
    }

    return new WheelSpeeds(leftSpeed, rightSpeed);
  }

  public void teleopPeriodic() {

    /* Get gamepad axis */
    //double leftYstick = -1 * _joy.getY();

    /* Get Talon/Victor's current output percentage */
    //double motorOutput = _talon.getMotorOutputPercent();

    /* Prepare line to print */
    //_sb.append("\tout:");
    /* Cast to int to remove decimal places */
    //_sb.append((int) (motorOutput * 100));
    //_sb.append("%");	// Percent

    //_sb.append("\tspd:");
    //_sb.append(_talon.getSelectedSensorVelocity(Constants.kPIDLoopIdx));
    //_sb.append("u"); 	// Native units

    /** 
     * When button 1 is held, start and run Velocity Closed loop.
     * Velocity Closed Loop is controlled by joystick position x500 RPM, [-500, 500] RPM
     */
     //if (_joy.getRawButton(1)) {
        /* Velocity Closed Loop */

        /**
         * Convert 2000 RPM to units / 100ms.
         * 2048 Units/Rev * 2000 RPM / 600 100ms/min in either direction:
         * velocity setpoint is in units/100ms
         */
        //double targetVelocity_UnitsPer100ms = leftYstick * 2000.0 * 2048.0 / 600.0;
        /* 2000 RPM in either direction */
        //_talon.set(TalonFXControlMode.Velocity, targetVelocity_UnitsPer100ms);

        /* Append more signals to print when in speed mode. */
        //_sb.append("\terr:");
        //_sb.append(_talon.getClosedLoopError(Constants.kPIDLoopIdx));
        //_sb.append("\ttrg:");
       // _sb.append(targetVelocity_UnitsPer100ms);
    //} else {
        /* Percent Output */

       // _talon.set(TalonFXControlMode.PercentOutput, leftYstick);
   // }

    /* Print built string every 10 loops */
    //if (++_loops >= 10) {
    //    _loops = 0;
    //    System.out.println(_sb.toString());
    //}
    /* Reset built string */
    //_sb.setLength(0);
    }
}

