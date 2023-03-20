package frc.robot.commands;


import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import frc.robot.subsystems.Gyro;
/**
 *
 */
public class TurnDegreesProfiled extends ProfiledPIDCommand {

    public TurnDegreesProfiled(double targetAngleDegrees, DriveTrain drive, Gyro gyro){
        super(
            new ProfiledPIDController(0.15, 0.009, 0.025,
                new TrapezoidProfile.Constraints(100, 300)),
            // Close loop on heading
            gyro::getHeading,
            // Set reference to target
            targetAngleDegrees,
            // Pipe output to turn robot
            (output, setpoint) -> drive.turn(output),
            // Require the drive
            drive);

        getController().enableContinuousInput(-180, 180);
        getController().setTolerance(4, 100);
    }
    @Override
    public boolean isFinished(){
        return getController().atSetpoint();
    }
}
