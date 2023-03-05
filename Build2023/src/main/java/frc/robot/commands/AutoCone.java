// RobotBuilder Version: 5.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: SequentialCommandGroup.

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import frc.robot.subsystems.Arm;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.DriveTrain;
/**
 *
 */
public class AutoCone extends SequentialCommandGroup {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    public AutoCone(double position, Arm arm, Intake intake, DriveTrain drivetrain) {


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    addCommands(

        // Add Commands here:
        // Also add parallel commands using the
        //
        // addCommands(
        //      new command1(argsN, subsystem),
        //      parallel(
        //          new command2(argsN, subsystem),
        //          new command3(argsN, subsystem)
        //      )    
        //  );
        new DriveDistanceTest(7, -0.25, drivetrain).withTimeout(2.5),
        // Commands.parallel(
            new ExtendToPosition(50, arm),
            new LowerToPosition(position, arm),
        // ),
        new IntakeControl(-1, intake).withTimeout(0.5),
        new IntakeControl(0, intake).withTimeout(0.1),
        // Commands.parallel(
            new DriveDistanceTest(145, 0.75, drivetrain),
            new startPosition(arm).withTimeout(4)
        // )
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
    }
}
