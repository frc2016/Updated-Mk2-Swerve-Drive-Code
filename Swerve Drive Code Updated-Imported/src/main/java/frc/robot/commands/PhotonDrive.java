// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import org.photonvision.PhotonUtils;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.Constants.Vision;

public class PhotonDrive extends CommandBase {

  

  /** Creates a new PhotonDrive. */
  public PhotonDrive() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double forwardSpeed;
    double rotationSpeed;

    // Vision-alignment mode
    // Query the latest result from PhotonVision
    var result = Vision.camera.getLatestResult();

    if (result.hasTargets()) {
        // First calculate range
        double range =
                PhotonUtils.calculateDistanceToTargetMeters(
                        Vision.CAMERA_HEIGHT_METERS,
                        Vision.TARGET_HEIGHT_METERS,
                        Vision.CAMERA_PITCH_RADIANS,
                        Units.degreesToRadians(result.getBestTarget().getPitch()));

        // Use this range as the measurement we give to the PID controller.
        // -1.0 required to ensure positive PID controller effort _increases_ range
        forwardSpeed = -Vision.forwardController.calculate(range, Vision.GOAL_RANGE_METERS);

        // Also calculate angular power
        // -1.0 required to ensure positive PID controller effort _increases_ yaw
        rotationSpeed = -Vision.turnController.calculate(result.getBestTarget().getYaw(), 0);
    } else {
        // If we have no targets, stay still.
        forwardSpeed = 0;
        rotationSpeed = 0;
    }
    Robot.drivetrain.drive(new Translation2d(forwardSpeed, rotationSpeed), 0, false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.drivetrain.drive(new Translation2d(0, 0), 0, false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
