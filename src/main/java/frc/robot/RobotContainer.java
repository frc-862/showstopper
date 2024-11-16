// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Swerve;
import frc.thunder.LightningContainer;
import frc.robot.Constants.TunerConstants;

public class RobotContainer extends LightningContainer {

    private final XboxController driver = new XboxController(0);

    private final Swerve drivetrain = getDrivetrain();

    private final Telemetry logger = new Telemetry(drivetrain.getMaxSpeed());

    @Override
    protected void initializeSubsystems() {

    }

    @Override
    protected void configureButtonBindings() {
        new Trigger(() -> driver.getLeftTriggerAxis() > 0.25d).whileTrue(
                drivetrain.applyPercentRequestRobot(
                        () -> -driver.getLeftY(),
                        () -> -driver.getLeftX(),
                        () -> -driver.getRightX()));

        new Trigger(() -> driver.getRightTriggerAxis() > 0.25d)
                .onTrue(drivetrain.enableSlowMode())
                .onFalse(drivetrain.disableSlowMode());

        new Trigger(() -> driver.getStartButton() && driver.getBackButton()).onTrue(drivetrain.resetForward());

        new Trigger(driver::getXButton).onTrue(drivetrain.setBrake());

        // if (Utils.isSimulation()) {
        //     drivetrain.seedFieldRelative(new Pose2d(new Translation2d(), Rotation2d.fromDegrees(90)));
        // }
    }

    @Override
    protected void configureDefaultCommands() {
        drivetrain.setDefaultCommand(
                drivetrain.applyPercentRequestField(() -> -(driver.getLeftY() * drivetrain.getSpeedMult()),
                        () -> -(driver.getLeftX() * drivetrain.getSpeedMult()),
                        () -> -(driver.getRightX() * drivetrain.getRotMult())));

        drivetrain.registerTelemetry(logger::telemeterize);
    }

    @Override
    protected void initializeNamedCommands() {
    }

    @Override
    protected void configureSystemTests() {
    }

    @Override
    protected void releaseDefaultCommands() {
    }

    @Override
    protected void initializeDashboardCommands() {
    }

    @Override
    protected void configureFaultCodes() {
    }

    @Override
    protected void configureFaultMonitors() {
    }

    @Override
    protected Command getAutonomousCommand() {
        return null;
    }

    public Swerve getDrivetrain() {
        return drivetrain == null ? TunerConstants.DriveTrain : drivetrain;
    }
}
