// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.photonvision.PhotonCamera;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class Vision {
        
        // Constants such as camera and target height stored. Change per robot and goal!
        public static final double CAMERA_HEIGHT_METERS = Units.inchesToMeters(6);
        public static final double TARGET_HEIGHT_METERS = Units.feetToMeters(2);
        
        // Angle between horizontal and the camera.
        public static final double CAMERA_PITCH_RADIANS = Units.degreesToRadians(0);

        // How far from the target we want to be
        public static final double GOAL_RANGE_METERS = Units.feetToMeters(2);

        // Change this to match the name of your camera
        public static final PhotonCamera camera = new PhotonCamera("photonvision");
    
        // PID constants should be tuned per robot
        public static final double LINEAR_P = 0.1;
        public static final double LINEAR_D = 0.0;
        public static final PIDController forwardController = new PIDController(LINEAR_P, 0, LINEAR_D);

        public static final double ANGULAR_P = 0.1;
        public static final double ANGULAR_D = 0.0;
        public static final PIDController turnController = new PIDController(ANGULAR_P, 0, ANGULAR_D);

    }
}
