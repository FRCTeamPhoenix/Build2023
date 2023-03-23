package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ColorChange extends CommandBase {
    private String color;

    public ColorChange(String c) {
        color = c;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if (SmartDashboard.getString("color", "team") == color) {
            SmartDashboard.putString("color", "team");
        } else {
            SmartDashboard.putString("color", color);
        }
    }
}
