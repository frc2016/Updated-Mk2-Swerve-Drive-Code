package frc.robot;

import frc.robot.commands.ResetGyro;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class OI {
    /*
       Add your joysticks and buttons here
     */
    private XboxController driveController = new XboxController(0);
    private JoystickButton gyroResetButton;

    public OI() {
        // Back button zeroes the drivetrain
        
        gyroResetButton = new JoystickButton(driveController, XboxController.Button.kBack.value);
        gyroResetButton.whenPressed(new ResetGyro());

        // SmartDashboard Buttons
        SmartDashboard.putData("Reset Gyro", new ResetGyro());
    }

    public XboxController getDriveController() {
        return driveController;
    }
}
