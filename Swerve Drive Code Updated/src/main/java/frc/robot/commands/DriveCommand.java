package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.common.util.Utilities;

public class DriveCommand extends CommandBase {

    public DriveCommand() {
        addRequirements(Robot.drivetrain);
    }

    @Override
    public void execute() {
        double forward = -Robot.oi.getDriveController().getRawAxis(1);
        forward = Utilities.deadband(forward);
        // Square the forward stick
        forward = Math.copySign(Math.pow(forward, 2.0), forward);

        double strafe = -Robot.oi.getDriveController().getRawAxis(0);
        strafe = Utilities.deadband(strafe);
        // Square the strafe stick
        strafe = Math.copySign(Math.pow(strafe, 2.0), strafe);

        double rotation = -Robot.oi.getDriveController().getRawAxis(2);
        rotation = Utilities.deadband(rotation);
        // Square the rotation stick
        rotation = Math.copySign(Math.pow(rotation, 2.0), rotation);

        Robot.drivetrain.drive(new Translation2d(forward/5, strafe/5), rotation/5, true);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
