package frc.robot.commands;


import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;
/**
 *
 */
public class TurnDegreesPID extends PIDCommand {

    public TurnDegreesPID(double targetAngleDegrees, DriveTrain drive, Gyro gyro){
       
        super(
            new edu.wpi.first.math.controller.PIDController(0.15, 0.009, 0.025),
            gyro::getHeading,
            targetAngleDegrees,
            output->drive.turn(output),
            drive);

        getController().enableContinuousInput(-180, 180);
        getController().setTolerance(5, 100);
    }
    @Override
    public boolean isFinished(){
        return getController().atSetpoint();
    }
}
