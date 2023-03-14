// RobotBuilder Version: 5.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Command.

package frc.robot.commands;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;
import pabeles.concurrency.IntOperatorTask.Min;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class TurnDegrees extends CommandBase {
    private double target;
    private boolean atTarget;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
        private DriveTrain m_driveTrain;
    private double m_angle;
    private double m_speed;
    private double MAX_TURN_SPEED= .5;
    private double MIN_TURN_SPEED = 0.45;
    private Gyro m_gyro;
    double kP = 0.1;
    private double TURN_ERROR=.5;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS


    public TurnDegrees(double angle, double speed, DriveTrain subsystem, Gyro gyro) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        atTarget = false;
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        m_angle = angle;
        m_speed = speed;
        m_gyro = gyro;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        m_driveTrain = subsystem;
        addRequirements(m_driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_gyro.setYaw(0);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double l_speed = 0;
        double error = m_angle - m_gyro.getYaw();

        //Find our error (difference between the angle we want and the angle we have)
        // Multiply that by some value.  We want the turn to slow down as we get close to where we want to be
        //Examples:
        // Target=90 (90 degrees right)
        // Current=0 (we just started turning)
        // error = 90 (90-0)
        // Speed to turn = 90 * kP (or 90 * .01)
        // Result: .9 (or almost max turn speed)
        // As we get closer to the mark say we're at 80 degrees and want to get to 90 the example below plays out
        // Target=0
        // Current=80
        // error = 10 (90-80)
        // Speed to turn = 10 * kP (or 10 * .01)
        // Result: .1 (or almost stopped)
        // We will have to play with the kP value to determine if the stop is too sudden and never makes acutally to 90.
        // You'll also notice the MAX_TURN_SPEED lines below for speed.  
        // This is a constant above where we can change this to something slower than 1 if needed.
        // 
        MathUtil.clamp(error,-10,10);
        l_speed = error * kP * m_speed;
        //MathUtil.clamp(-1*l_speed, MIN_TURN_SPEED, MAX_TURN_SPEED);       
        m_driveTrain.turn(-1*l_speed);
        if(error>=-TURN_ERROR && error<=TURN_ERROR){
            //Applying a band of -.5 to .5 degrees of slush in the turn target.
            atTarget=true;
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return atTarget;
    }

    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
    }
}
